package py.com.prestosoftware.data.dtos;

public class ConfigUsuarioDTO {
	
	private Long id;
	
	private int esVendedor;
	
	public ConfigUsuarioDTO() {}
	
	public ConfigUsuarioDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getEsVendedor() {
		return esVendedor;
	}
	
	public void setEsVendedor(int esVendedor) {
		this.esVendedor = esVendedor;
	}
	
}
