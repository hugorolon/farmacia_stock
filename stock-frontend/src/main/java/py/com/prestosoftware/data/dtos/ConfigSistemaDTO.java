package py.com.prestosoftware.data.dtos;


public class ConfigSistemaDTO {
	
	private Long id;
	
	private String formatoNumero;
	
	private String formatoFecha;
	
	
	public ConfigSistemaDTO() {}
	
	public ConfigSistemaDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormatoFecha() {
		return formatoFecha;
	}
	
	public void setFormatoFecha(String formatoFecha) {
		this.formatoFecha = formatoFecha;
	}
	
	public String getFormatoNumero() {
		return formatoNumero;
	}
	
	public void setFormatoNumero(String formatoNumero) {
		this.formatoNumero = formatoNumero;
	}
	
}
