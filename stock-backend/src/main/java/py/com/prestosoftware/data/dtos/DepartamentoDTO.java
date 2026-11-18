package py.com.prestosoftware.data.dtos;

public class DepartamentoDTO {
	
	private Long id;
	
	private String nombre;
	
	private PaisDTO pais;
	
	private int activo;
	
	public DepartamentoDTO() {}
	
	public DepartamentoDTO(Long id) {
		this.id = id;
	}
	
	public DepartamentoDTO(String nombre, PaisDTO pais, int activo) {
		this.nombre = nombre;
		this.pais = pais;
		this.activo = activo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public PaisDTO getPais() {
		return pais;
	}
	
	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

}
