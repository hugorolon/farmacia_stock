package py.com.prestosoftware.data.dtos;


public class ImpuestoDTO {
	
	private Long id;
	
	private String nombre;
	
	private Double porcentaje;
	private int activo;
	
	public ImpuestoDTO() {}
	
	public ImpuestoDTO(Long id) {
		this.id = id;
	}
	
	public ImpuestoDTO(String nombre, Double porcentaje, int activo) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
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

	public Double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
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
