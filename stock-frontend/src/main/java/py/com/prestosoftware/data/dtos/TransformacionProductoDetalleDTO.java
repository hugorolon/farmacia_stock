package py.com.prestosoftware.data.dtos;

public class TransformacionProductoDetalleDTO {
	
	
	private ProductoDTO productoDestino;
	private Double cantidad;
	private Double precio;

	public TransformacionProductoDetalleDTO() {}


	public ProductoDTO getProductoDestino() {
		return productoDestino;
	}


	public void setProductoDestino(ProductoDTO productoDestino) {
		this.productoDestino = productoDestino;
	}


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


}
