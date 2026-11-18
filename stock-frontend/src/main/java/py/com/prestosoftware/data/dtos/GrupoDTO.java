package py.com.prestosoftware.data.dtos;

public class GrupoDTO {
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private Double porcIncrementoPrecioA;
	
	private Double porcIncrementoPrecioB;
	
	private Double porcIncrementoPrecioC;
	
	private Double porcIncrementoPrecioD;
	
	private Double porcIncrementoPrecioE; 
	
	private TamanhoDTO tamanho;
	
	private int activo;
	
	public GrupoDTO() {}
	
	public GrupoDTO(Long id) {
		this.id = id;
	}
	
	public GrupoDTO(String nombre, String descripcion, TamanhoDTO tamanho, int activo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tamanho = tamanho;
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

	public TamanhoDTO getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoDTO tamanho) {
		this.tamanho = tamanho;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public Double getPorcIncrementoPrecioA() {
		return porcIncrementoPrecioA;
	}

	public void setPorcIncrementoPrecioA(Double porcIncrementoPrecioA) {
		this.porcIncrementoPrecioA = porcIncrementoPrecioA;
	}

	public Double getPorcIncrementoPrecioB() {
		return porcIncrementoPrecioB;
	}

	public void setPorcIncrementoPrecioB(Double porcIncrementoPrecioB) {
		this.porcIncrementoPrecioB = porcIncrementoPrecioB;
	}

	public Double getPorcIncrementoPrecioC() {
		return porcIncrementoPrecioC;
	}

	public void setPorcIncrementoPrecioC(Double porcIncrementoPrecioC) {
		this.porcIncrementoPrecioC = porcIncrementoPrecioC;
	}

	public Double getPorcIncrementoPrecioD() {
		return porcIncrementoPrecioD;
	}

	public void setPorcIncrementoPrecioD(Double porcIncrementoPrecioD) {
		this.porcIncrementoPrecioD = porcIncrementoPrecioD;
	}

	public Double getPorcIncrementoPrecioE() {
		return porcIncrementoPrecioE;
	}

	public void setPorcIncrementoPrecioE(Double porcIncrementoPrecioE) {
		this.porcIncrementoPrecioE = porcIncrementoPrecioE;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
