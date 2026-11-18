package py.com.prestosoftware.data.dtos;


public class EgresoDTO {
	
	private Long id;
	
	private String egrEgreso;

	private String egrDescripcion;
	
	private String egrAbreviatura;

	
	public EgresoDTO() {}
	
	public EgresoDTO(Long id) {
		this.id = id;
	}
	
	public EgresoDTO(String egrEgreso, String egrDescripcion, String egrAbreviatura) {
		this.egrEgreso = egrEgreso;
		this.egrDescripcion= egrDescripcion;
		this.egrAbreviatura= egrAbreviatura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEgrEgreso() {
		return egrEgreso;
	}

	public void setEgrEgreso(String egrEgreso) {
		this.egrEgreso = egrEgreso;
	}

	public String getEgrDescripcion() {
		return egrDescripcion;
	}

	public void setEgrDescripcion(String egrDescripcion) {
		this.egrDescripcion = egrDescripcion;
	}

	public String getEgrAbreviatura() {
		return egrAbreviatura;
	}

	public void setEgrAbreviatura(String egrAbreviatura) {
		this.egrAbreviatura = egrAbreviatura;
	}

	
}
