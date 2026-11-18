package py.com.prestosoftware.data.dtos;

import java.util.Date;


public class LoteDTO {		
	

	private Long id;
	private Long idProducto;
	private Date fechaVencimiento;
	private Date fechaIngreso;
	private Double precioCompra;
	private String codigoLote;
	private Double stockActual;
	private Double cantidadInicial;
	
	public LoteDTO() {}
	
	
	public LoteDTO(Date fechaVencimiento, Date fechaIngreso, Double precioCompra, Double precioVenta, Double stockActual, Double cantidadInicial, String codigoLote) {
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