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



@Entity
@Table(name = "lotes", indexes = @Index(name = "idx_lotes_id_producto", columnList = "id_producto"))
public class Lotes {		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_producto")
	private Long idProducto;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_vencimiento")
	private Date fechaVencimiento;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;
	
	@Column(name = "precio_compra")
	private Double precioCompra;

	@Column(name = "codigo_lote")
	private String codigoLote;
	
	@Column(name = "stock_actual")
	private Double stockActual;

	@Column(name = "cantidad_inicial")
	private Double cantidadInicial;
	
	public Lotes() {}
	
	
	public Lotes(Date fechaVencimiento, Date fechaIngreso, Double precioCompra, Double precioVenta, Double stockActual, Double cantidadInicial, String codigoLote) {
		this.fechaVencimiento = fechaVencimiento;
		this.fechaIngreso= fechaIngreso;
		this.precioCompra = precioCompra;
		this.codigoLote = codigoLote;
		this.stockActual =stockActual;		
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public Double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Double getStockActual() {
		return stockActual;
	}
	public void setStockActual(Double stockActual) {
		this.stockActual = stockActual;
	}


	public Date getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public String getCodigoLote() {
		return codigoLote;
	}


	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}


	public Double getCantidadInicial() {
		return cantidadInicial;
	}


	public void setCantidadInicial(Double cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}


}