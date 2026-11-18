package py.com.prestosoftware.data.dtos;

public class ProductoPrecioDTO {
	
	
	private String listaPrecio;
	private Double valor;
	
	public ProductoPrecioDTO(String lista, Double precio) {
		this.listaPrecio = lista;
		this.valor = precio;
	}

	
	public String getListaPrecio() {
		return listaPrecio;
	}
	
	public void setListaPrecio(String listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.valor + "";
	}

}
