package py.com.prestosoftware.data.dtos;


public class CiudadDTO {

	private Long id;
	
	private String nombre;
	
	private DepartamentoDTO departamento;
	
	private int activo;
	
	public CiudadDTO() {}
	
	public CiudadDTO(Long id) {
		this.id = id;
	}
	
	public CiudadDTO(String nombre, DepartamentoDTO dep, int activo) {
		this.nombre = nombre;
		this.departamento = dep;
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

	public DepartamentoDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
}
