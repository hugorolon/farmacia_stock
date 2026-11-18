package py.com.prestosoftware.data.dtos;

public class ListaPrecioDTO {
	
	private Long id;
	
	private String nombre;
	
	private int activo;
	
	public ListaPrecioDTO() {}
	
	public ListaPrecioDTO(Long id) {
		this.id = id;
	}
	
	public ListaPrecioDTO(String nombre, int activo) {
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
