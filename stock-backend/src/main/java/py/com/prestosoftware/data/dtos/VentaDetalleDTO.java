package py.com.prestosoftware.data.dtos;

public class VentaDetalleDTO {
	
	private Long productoId;
	private String producto;
	private Double cantidad;
	private Double precio;
	private Double subtotal;
	private Double descuento;
	private Double exenta;
	private Double iva5;
	private Double iva10;
	private Double stock;
	private Double precioCosto;
	private String descripcionFiscal;
	
	private Double precioFob;
	
	private Double precioCif;
	
	private Double costoFob;
	
	private Double costoCif;
	
	private Double medioFob;
	
	private Double medioCif;
	private Integer iva;
	
	public VentaDetalleDTO() {}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	
	public String getProducto() {
		return producto;
	}
	
	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Double getPrecioFob() {
		return precioFob;
	}

	public void setPrecioFob(Double precioFob) {
		this.precioFob = precioFob;
	}

	public Double getPrecioCif() {
		return precioCif;
	}

	public void setPrecioCif(Double precioCif) {
		this.precioCif = precioCif;
	}

	public Double getCostoFob() {
		return costoFob;
	}

	public void setCostoFob(Double costoFob) {
		this.costoFob = costoFob;
	}
	
	public Double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(Double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public Double getCostoCif() {
		return costoCif;
	}

	public void setCostoCif(Double costoCif) {
		this.costoCif = costoCif;
	}

	public Double getMedioFob() {
		return medioFob;
	}

	public void setMedioFob(Double medioFob) {
		this.medioFob = medioFob;
	}

	public Double getMedioCif() {
		return medioCif;
	}

	public void setMedioCif(Double medioCif) {
		this.medioCif = medioCif;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Integer getIva() {
		return iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}
	
	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public Double getExenta() {
		return exenta;
	}

	public void setExenta(Double exenta) {
		this.exenta = exenta;
	}

	public Double getIva5() {
		return iva5;
	}

	public void setIva5(Double iva5) {
		this.iva5 = iva5;
	}

	public Double getIva10() {
		return iva10;
	}

	public void setIva10(Double iva10) {
		this.iva10 = iva10;
	}

	public String getDescripcionFiscal() {
		return descripcionFiscal;
	}

	public void setDescripcionFiscal(String descripcionFiscal) {
		this.descripcionFiscal = descripcionFiscal;
	}
	
}
