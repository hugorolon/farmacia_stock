package py.com.prestosoftware.data.dtos;



public class ColorDTO {
	
	private Long id;
	
	
	private String nombre;
	
	private int activo;
	
	public ColorDTO() {}
	
	public ColorDTO(Long id) {
		this.id = id;
	}
	
	public ColorDTO(String nombre, int activo) {
		this.nombre = nombre;
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
	
	@Override
	public String toString() {
		return this.nombre;
	}
	
}
