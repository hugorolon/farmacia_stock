package py.com.prestosoftware.data.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * Kardex de lotes: cada entrada (compra) o salida (venta) de un lote queda registrada aqui,
 * lo que permite la trazabilidad (que lote se vendio a quien) y revertir anulaciones.
 * La cantidad es con signo: positiva = entrada, negativa = salida.
 */
@Entity
@Table(name = "lote_movimientos", indexes = {
		@Index(name = "idx_lote_mov_lote", columnList = "lote_id"),
		@Index(name = "idx_lote_mov_ref", columnList = "tipo, referencia_id") })
public class LoteMovimiento {

	public static final String COMPRA = "COMPRA";
	public static final String VENTA = "VENTA";
	public static final String ANULA_COMPRA = "ANULA_COMPRA";
	public static final String ANULA_VENTA = "ANULA_VENTA";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lote_id")
	private Long loteId;

	@Column(name = "producto_id")
	private Long productoId;

	@Column(name = "tipo", length = 20)
	private String tipo;

	@Column(name = "referencia_id")
	private Long referenciaId;

	@Column(name = "cantidad")
	private Double cantidad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "usuario_id")
	private Long usuarioId;

	@Column(name = "revertido")
	private Integer revertido = 0;

	public LoteMovimiento() {}

	public LoteMovimiento(Long loteId, Long productoId, String tipo, Long referenciaId, Double cantidad, Long usuarioId) {
		this.loteId = loteId;
		this.productoId = productoId;
		this.tipo = tipo;
		this.referenciaId = referenciaId;
		this.cantidad = cantidad;
		this.usuarioId = usuarioId;
		this.fecha = new Date();
		this.revertido = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLoteId() {
		return loteId;
	}

	public void setLoteId(Long loteId) {
		this.loteId = loteId;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getReferenciaId() {
		return referenciaId;
	}

	public void setReferenciaId(Long referenciaId) {
		this.referenciaId = referenciaId;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getRevertido() {
		return revertido;
	}

	public void setRevertido(Integer revertido) {
		this.revertido = revertido;
	}
}
