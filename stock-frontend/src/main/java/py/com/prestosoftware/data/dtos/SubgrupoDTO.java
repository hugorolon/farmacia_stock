package py.com.prestosoftware.data.dtos;

public class SubgrupoDTO {
	
	private Long id;
	
	private String nombre;
	
	private GrupoDTO grupo;
	
	private Double porcentajePrecioA;
	
	private Double porcentajePrecioB;
	private Double porcentajePrecioC;
	private Double porcentajePrecioD;
	
	private Double porcentajePrecioE;
	
	private String tipo; //S = servicio / M = mercaderia
	
	private int activo;
	
	public SubgrupoDTO() {}
	
	public SubgrupoDTO(Long id) {
		this.id = id;
	}
	
	public SubgrupoDTO(String nombre, GrupoDTO grupo, String tipo, int activo) {
		this.nombre = nombre;
		this.grupo = grupo;
		this.tipo = tipo;
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

	public GrupoDTO getGrupo() {
		return grupo;
	}
	
	public void setGrupo(GrupoDTO grupo) {
		this.grupo = grupo;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public Double getPorcentajePrecioA() {
		return porcentajePrecioA;
	}

	public void setPorcentajePrecioA(Double porcentajePrecioA) {
		this.porcentajePrecioA = porcentajePrecioA;
	}

	public Double getPorcentajePrecioB() {
		return porcentajePrecioB;
	}

	public void setPorcentajePrecioB(Double porcentajePrecioB) {
		this.porcentajePrecioB = porcentajePrecioB;
	}

	public Double getPorcentajePrecioC() {
		return porcentajePrecioC;
	}

	public void setPorcentajePrecioC(Double porcentajePrecioC) {
		this.porcentajePrecioC = porcentajePrecioC;
	}

	public Double getPorcentajePrecioD() {
		return porcentajePrecioD;
	}

	public void setPorcentajePrecioD(Double porcentajePrecioD) {
		this.porcentajePrecioD = porcentajePrecioD;
	}

	public Double getPorcentajePrecioE() {
		return porcentajePrecioE;
	}

	public void setPorcentajePrecioE(Double porcentajePrecioE) {
		this.porcentajePrecioE = porcentajePrecioE;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
