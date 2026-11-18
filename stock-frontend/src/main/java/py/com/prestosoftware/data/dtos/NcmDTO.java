package py.com.prestosoftware.data.dtos;


public class NcmDTO {
	
	private Long id;
	
	private String nombre;
	private String descripcion;
	private int activo;
	
	public NcmDTO() {}
	
	public NcmDTO(Long id) {
		this.id = id;
	}
	
	public NcmDTO(String nombre, String descripcion, int activo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
