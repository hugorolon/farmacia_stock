package py.com.prestosoftware.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.prestosoftware.data.models.LoteMovimiento;
import py.com.prestosoftware.data.models.Lotes;
import py.com.prestosoftware.domain.services.LoteService;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService service;

    public LoteController(LoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lotes> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Lotes findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote no encontrado: " + id));
    }

    @GetMapping("/producto/{productoId}")
    public List<Lotes> findByProductoId(@PathVariable Long productoId) {
        return service.findLotesByProductoId(productoId);
    }

    /** Lotes vigentes con stock, en orden FEFO (el primero es el que se vende primero). */
    @GetMapping("/producto/{productoId}/disponibles")
    public List<Lotes> findDisponibles(@PathVariable Long productoId) {
        return service.findDisponiblesFefo(productoId);
    }

    /** Stock vendible: suma de lotes no vencidos. */
    @GetMapping("/producto/{productoId}/stock-disponible")
    public Double getStockDisponible(@PathVariable Long productoId) {
        return service.getStockDisponible(productoId);
    }

    @GetMapping("/producto/{productoId}/stock-vencido")
    public Double getStockVencido(@PathVariable Long productoId) {
        return service.getStockVencido(productoId);
    }

    @GetMapping("/{id}/movimientos")
    public List<LoteMovimiento> findMovimientos(@PathVariable Long id) {
        return service.findMovimientosByLote(id);
    }

    @GetMapping("/producto/{productoId}/movimientos")
    public List<LoteMovimiento> findMovimientosByProducto(@PathVariable Long productoId) {
        return service.findMovimientosByProducto(productoId);
    }

    /**
     * Revierte el stock de lotes de una compra o venta anulada.
     * tipo = COMPRA | VENTA, referencia = id de la compra o venta.
     */
    @PostMapping("/revertir")
    public int revertir(@RequestParam("tipo") String tipo, @RequestParam("referencia") Long referencia,
            @RequestParam(value = "usuario", required = false) Long usuario) {
        try {
            return service.revertir(tipo, referencia, usuario);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/max")
    public long getMaxId() {
        return service.getRowCount();
    }

    @PostMapping
    public ResponseEntity<Lotes> save(@RequestBody Lotes entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Lotes entity = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote no encontrado: " + id));
        service.remove(entity);
    }
}
