package py.com.prestosoftware.data.dtos;



public class MovimientoItemEgresoDTO {
	
	private Long id;
	
	private Integer mieNumero;

	private Integer mieEgreso;
	
	private Double mieMonto;
	
	private String mieDescripcion;
	
	public MovimientoItemEgresoDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMieNumero() {
		return mieNumero;
	}

	public void setMieNumero(Integer mieNumero) {
		this.mieNumero = mieNumero;
	}

	public Integer getMieEgreso() {
		return mieEgreso;
	}

	public void setMieEgreso(Integer mieEgreso) {
		this.mieEgreso = mieEgreso;
	}

	public Double getMieMonto() {
		return mieMonto;
	}

	public void setMieMonto(Double mieMonto) {
		this.mieMonto = mieMonto;
	}

	public String getMieDescripcion() {
		return mieDescripcion;
	}

	public void setMieDescripcion(String mieDescripcion) {
		this.mieDescripcion = mieDescripcion;
	}
}
