package py.com.prestosoftware.data.dtos;


public class CondicionPagoDTO {
	
	private Long id;
	
	private String nombre;
	
	private int cantDia;
	
	private int activo;
	
	public CondicionPagoDTO() {}
	
	public CondicionPagoDTO(Long id) {
		this.id = id;
	}
	
	public CondicionPagoDTO(String nombre, int cantDia, int activo) {
		this.nombre = nombre;
		this.cantDia = cantDia;
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
	
	public int getCantDia() {
		return cantDia;
	}

	public void setCantDia(int cantDia) {
		this.cantDia = cantDia;
	}

	@Override
	public String toString() {
		return this.cantDia + " ";
	}

}
