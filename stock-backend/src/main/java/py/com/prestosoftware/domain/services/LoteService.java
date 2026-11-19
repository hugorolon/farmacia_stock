package py.com.prestosoftware.domain.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.prestosoftware.data.models.Compra;
import py.com.prestosoftware.data.models.CompraDetalle;
import py.com.prestosoftware.data.models.LoteMovimiento;
import py.com.prestosoftware.data.models.Lotes;
import py.com.prestosoftware.data.models.Producto;
import py.com.prestosoftware.data.models.Venta;
import py.com.prestosoftware.data.models.VentaDetalle;
import py.com.prestosoftware.data.repository.LoteMovimientoRepository;
import py.com.prestosoftware.data.repository.LoteRepository;
import py.com.prestosoftware.data.repository.ProductoRepository;

@Service
public class LoteService {

    private LoteRepository repository;
    private LoteMovimientoRepository movimientoRepository;
    private ProductoRepository productoRepository;

    @Autowired
    public LoteService(LoteRepository repository, LoteMovimientoRepository movimientoRepository,
            ProductoRepository productoRepository) {
        this.repository = repository;
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    public List<Lotes> findAll() {
        return repository.findAll();
    }

    public Optional<Lotes> findById(Long id) {
    	return repository.findById(id);
    }

    public List<Lotes> findLotesByProductoId(Long productoId) {
		return repository.findLotesByProductoId(productoId);
	}

    /** Lotes vigentes (no vencidos) con stock, en el orden en que se van a vender (FEFO). */
    public List<Lotes> findDisponiblesFefo(Long productoId) {
        return repository.findDisponiblesFefo(productoId, new Date());
    }

    /** Stock vendible del producto: suma de lotes con stock que no estan vencidos. */
    public Double getStockDisponible(Long productoId) {
        Double stock = repository.getStockDisponible(productoId, new Date());
        return stock == null ? 0d : stock;
    }

    public Double getStockVencido(Long productoId) {
        Double stock = repository.getStockVencido(productoId, new Date());
        return stock == null ? 0d : stock;
    }

    public List<LoteMovimiento> findMovimientosByLote(Long loteId) {
        return movimientoRepository.findByLoteIdOrderByFechaAsc(loteId);
    }

    public List<LoteMovimiento> findMovimientosByProducto(Long productoId) {
        return movimientoRepository.findByProductoIdOrderByFechaAsc(productoId);
    }

    /**
     * Alta/edicion manual de lotes (pantalla de Producto).
     * El stock que se informa aqui es el stock INICIAL del lote: una vez que el lote
     * tiene movimientos (compras/ventas) su stock solo cambia por esos movimientos,
     * asi una edicion del producto con datos viejos no pisa lo vendido.
     */
    @Transactional
    public Lotes save(Lotes lote) {
        if (lote.getCodigoLote() != null)
            lote.setCodigoLote(lote.getCodigoLote().trim().toUpperCase());
        if (lote.getId() != null) {
            Optional<Lotes> actual = repository.findById(lote.getId());
            if (actual.isPresent() && !movimientoRepository.findByLoteIdOrderByFechaAsc(lote.getId()).isEmpty()) {
                lote.setStockActual(actual.get().getStockActual());
                lote.setCantidadInicial(actual.get().getCantidadInicial());
                lote.setPrecioCompra(actual.get().getPrecioCompra());
            } else if (actual.isPresent()) {
                lote.setCantidadInicial(lote.getStockActual());
            }
        } else {
            if (lote.getFechaIngreso() == null)
                lote.setFechaIngreso(new Date());
            lote.setCantidadInicial(lote.getStockActual());
        }
        return repository.save(lote);
    }

    public void remove(Lotes lote) {
        repository.delete(lote);
    }

	public long getRowCount() {
		return repository.getMaxId();
	}

	private boolean esProductoConLote(Long productoId) {
		if (productoId == null)
			return false;
		Optional<Producto> p = productoRepository.findById(productoId);
		return p.isPresent() && p.get().getEsLote() == 1;
	}

	/**
	 * Ingreso de lotes por compra: por cada item de un producto con control de lote
	 * suma la cantidad al lote (lo crea si no existe) y registra el movimiento.
	 * Deja el id del lote en el item (compra_detalles.lote_id).
	 */
	@Transactional
	public void registrarEntradaCompra(Compra compra, Long usuarioId) {
		if (compra == null || compra.getId() == null || compra.getItems() == null)
			return;

		for (CompraDetalle item : compra.getItems()) {
			if (!esProductoConLote(item.getProductoId()))
				continue;
			String codigo = item.getCodigoLote() == null ? "" : item.getCodigoLote().trim().toUpperCase();
			if (codigo.isEmpty() || item.getFechaVencimiento() == null) {
				throw new IllegalArgumentException("El producto " + item.getProductoId() + " - " + item.getProducto()
						+ " requiere numero de lote y fecha de vencimiento");
			}
			double cantidad = item.getCantidad() == null ? 0d : item.getCantidad();
			if (cantidad <= 0)
				continue;

			Lotes lote = repository.findByProductoAndCodigo(item.getProductoId(), codigo).orElse(null);
			if (lote == null) {
				lote = new Lotes();
				lote.setIdProducto(item.getProductoId());
				lote.setCodigoLote(codigo);
				lote.setFechaIngreso(new Date());
				lote.setStockActual(0d);
				lote.setCantidadInicial(0d);
			}
			lote.setFechaVencimiento(item.getFechaVencimiento());
			lote.setPrecioCompra(item.getPrecio());
			lote.setStockActual(valor(lote.getStockActual()) + cantidad);
			lote.setCantidadInicial(valor(lote.getCantidadInicial()) + cantidad);
			lote = repository.save(lote);

			item.setCodigoLote(codigo);
			item.setLoteId(lote.getId());
			movimientoRepository.save(new LoteMovimiento(lote.getId(), item.getProductoId(), LoteMovimiento.COMPRA,
					compra.getId(), cantidad, usuarioId));
		}
		repository.flush();
		movimientoRepository.flush();
	}

	/**
	 * Salida de lotes por venta con criterio FEFO (primero vence, primero sale).
	 * Nunca descuenta de lotes vencidos. Devuelve los mensajes de los items que no
	 * tenian stock suficiente en lotes vigentes (la venta no se bloquea aqui; el
	 * control previo se hace en la pantalla de ventas).
	 */
	@Transactional
	public List<String> registrarSalidaVenta(Venta venta, Long usuarioId) {
		List<String> faltantes = new ArrayList<>();
		if (venta == null || venta.getId() == null || venta.getItems() == null)
			return faltantes;

		Date hoy = new Date();
		for (VentaDetalle item : venta.getItems()) {
			if (!esProductoConLote(item.getProductoId()))
				continue;
			double pendiente = item.getCantidad() == null ? 0d : item.getCantidad();

			for (Lotes lote : repository.findDisponiblesFefo(item.getProductoId(), hoy)) {
				if (pendiente <= 0)
					break;
				double disponible = valor(lote.getStockActual());
				double consumo = Math.min(disponible, pendiente);
				if (consumo <= 0)
					continue;
				lote.setStockActual(disponible - consumo);
				repository.save(lote);
				movimientoRepository.save(new LoteMovimiento(lote.getId(), item.getProductoId(), LoteMovimiento.VENTA,
						venta.getId(), -consumo, usuarioId));
				pendiente -= consumo;
			}
			if (pendiente > 0.0001) {
				faltantes.add("Venta " + venta.getId() + ": producto " + item.getProductoId()
						+ " sin stock suficiente en lotes vigentes (faltan " + pendiente + ")");
			}
		}
		repository.flush();
		movimientoRepository.flush();
		return faltantes;
	}

	/**
	 * Revierte los movimientos de lote de una compra o venta anulada. Es idempotente:
	 * los movimientos ya revertidos no se vuelven a procesar.
	 *
	 * @param tipo LoteMovimiento.COMPRA o LoteMovimiento.VENTA
	 * @return cantidad de movimientos revertidos
	 */
	@Transactional
	public int revertir(String tipo, Long referenciaId, Long usuarioId) {
		String tipoOrigen = tipo == null ? "" : tipo.trim().toUpperCase();
		if (!LoteMovimiento.COMPRA.equals(tipoOrigen) && !LoteMovimiento.VENTA.equals(tipoOrigen))
			throw new IllegalArgumentException("Tipo de movimiento invalido: " + tipo);

		String tipoAnulacion = LoteMovimiento.COMPRA.equals(tipoOrigen) ? LoteMovimiento.ANULA_COMPRA
				: LoteMovimiento.ANULA_VENTA;
		List<LoteMovimiento> movimientos = movimientoRepository.findByTipoAndReferenciaIdAndRevertido(tipoOrigen,
				referenciaId, 0);

		for (LoteMovimiento mov : movimientos) {
			Optional<Lotes> lote = repository.findById(mov.getLoteId());
			if (lote.isPresent()) {
				Lotes l = lote.get();
				// la cantidad del movimiento tiene signo: se aplica la inversa
				l.setStockActual(valor(l.getStockActual()) - valor(mov.getCantidad()));
				if (LoteMovimiento.COMPRA.equals(tipoOrigen))
					l.setCantidadInicial(valor(l.getCantidadInicial()) - valor(mov.getCantidad()));
				repository.save(l);
			}
			mov.setRevertido(1);
			movimientoRepository.save(mov);
			LoteMovimiento inversa = new LoteMovimiento(mov.getLoteId(), mov.getProductoId(), tipoAnulacion,
					referenciaId, -valor(mov.getCantidad()), usuarioId);
			inversa.setRevertido(1);
			movimientoRepository.save(inversa);
		}
		repository.flush();
		movimientoRepository.flush();
		return movimientos.size();
	}

	private static double valor(Double d) {
		return d == null ? 0d : d;
	}

}
