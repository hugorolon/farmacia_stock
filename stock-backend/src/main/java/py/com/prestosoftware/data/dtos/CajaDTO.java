package py.com.prestosoftware.data.dtos;


public class CajaDTO {
	
	private Long id;
	
	private String nombre;
	
	private EmpresaDTO empresa;
	
	private int activo;
	
	public CajaDTO() {}
	
	public CajaDTO(Long id) {
		this.id = id;
	}
	
	public CajaDTO(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public CajaDTO(String nombre, int activo, EmpresaDTO empresa) {
		this.nombre = nombre;
		this.activo = activo;
		this.empresa = empresa;
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
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}
	
	public String toString() {
		return this.nombre;
	}

}
