package py.com.prestosoftware.data.dtos;

public class IngresoDTO {
	
	private Long id;
	
	private String ingIngreso;

	private String ingDescripcion;
	
	private String ingAbreviatura;


	
	
	public IngresoDTO() {}
	
	public IngresoDTO(Long id) {
		this.id = id;
	}
	
	public IngresoDTO(String ingIngreso, String ingDescripcion, String ingAbreviatura) {
		this.ingIngreso = ingIngreso;
		this.ingDescripcion= ingDescripcion;
		this.ingAbreviatura= ingAbreviatura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIngIngreso() {
		return ingIngreso;
	}

	public void setIngIngreso(String ingIngreso) {
		this.ingIngreso = ingIngreso;
	}

	public String getIngDescripcion() {
		return ingDescripcion;
	}

	public void setIngDescripcion(String ingDescripcion) {
		this.ingDescripcion = ingDescripcion;
	}

	public String getIngAbreviatura() {
		return ingAbreviatura;
	}

	public void setIngAbreviatura(String ingAbreviatura) {
		this.ingAbreviatura = ingAbreviatura;
	}


	
}
