package py.com.prestosoftware.data.dtos;


public class DepositoDTO {
	
	private Long id;
	
	private String nombre;
	
	private int activo;
	
	private EmpresaDTO empresa;
	
	public DepositoDTO() {}
	
	public DepositoDTO(Long id) {
		this.id = id;
	}
	
	public DepositoDTO(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public DepositoDTO(String nombre, int activo, EmpresaDTO empresa) {
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

	@Override
	public String toString() {
		return this.nombre;
	}
	
}
