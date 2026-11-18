package py.com.prestosoftware.data.dtos;


public class MonedaDTO {
	
	private Long id;
	
	private String nombre;
	private String sigla;
	
	private int esBase;
   
    private String mascara;
    
    private String operacion;
    
	private int activo;
	
	public MonedaDTO() {}
	
	public MonedaDTO(Long id) {
		this.id = id;
	}
	
	public MonedaDTO(String nombre, String sigla, String operacion, int esBase, int activo) {
		this.nombre = nombre;
		this.sigla = sigla;
		this.operacion = operacion;
		this.esBase = esBase;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public int getEsBase() {
		return esBase;
	}

	public void setEsBase(int esBase) {
		this.esBase = esBase;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public String getMascara() {
		return mascara;
	}
	
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
