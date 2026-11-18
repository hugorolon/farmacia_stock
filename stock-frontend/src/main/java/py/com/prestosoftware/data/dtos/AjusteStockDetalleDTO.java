package py.com.prestosoftware.data.dtos;



public class AjusteStockDetalleDTO {

	private Long productoId;
    
    private String producto;
	
	private Double cantidadActual;
	
	private Double cantidadNueva;
	
	private Double cantidadDiferencia;
	
	private Double costo;
	
	private Double subtotalCosto;
	
	public AjusteStockDetalleDTO() {}

	public Double getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(Double cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public Double getCantidadNueva() {
		return cantidadNueva;
	}

	public void setCantidadNueva(Double cantidadNueva) {
		this.cantidadNueva = cantidadNueva;
	}

	public Double getCantidadDiferencia() {
		return cantidadDiferencia;
	}

	public void setCantidadDiferencia(Double cantidadDiferencia) {
		this.cantidadDiferencia = cantidadDiferencia;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getSubtotalCosto() {
		return subtotalCosto;
	}

	public void setSubtotalCosto(Double subtotalCosto) {
		this.subtotalCosto = subtotalCosto;
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

}
