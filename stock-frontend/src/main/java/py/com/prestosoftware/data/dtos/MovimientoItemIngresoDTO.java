package py.com.prestosoftware.data.dtos;


public class MovimientoItemIngresoDTO {
	
	private Long id;
	
	private Integer miiNumero;

	private Integer miiIngreso;
	
	private Double miiMonto;
	
	private String miiDescripcion;
	
	public MovimientoItemIngresoDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Integer getMiiNumero() {
		return miiNumero;
	}

	public void setMiiNumero(Integer miiNumero) {
		this.miiNumero = miiNumero;
	}

	public Integer getMiiIngreso() {
		return miiIngreso;
	}

	public void setMiiIngreso(Integer miiIngreso) {
		this.miiIngreso = miiIngreso;
	}

	public Double getMiiMonto() {
		return miiMonto;
	}

	public void setMiiMonto(Double miiMonto) {
		this.miiMonto = miiMonto;
	}

	public String getMiiDescripcion() {
		return miiDescripcion;
	}

	public void setMiiDescripcion(String miiDescripcion) {
		this.miiDescripcion = miiDescripcion;
	}
	
}
