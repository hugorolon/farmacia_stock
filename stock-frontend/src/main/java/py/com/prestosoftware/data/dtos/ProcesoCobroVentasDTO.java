package py.com.prestosoftware.data.dtos;

public class ProcesoCobroVentasDTO {
	
	private Long id;
	
	private Integer pveVenta;

	private Integer pveIngresoegreso;
	
	private Integer pveTipoproceso;
	
	private Integer pveProceso;
	
	private Integer pveFlag;
	
	public ProcesoCobroVentasDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPveVenta() {
		return pveVenta;
	}

	public void setPveVenta(Integer pveVenta) {
		this.pveVenta = pveVenta;
	}

	public Integer getPveIngresoegreso() {
		return pveIngresoegreso;
	}

	public void setPveIngresoegreso(Integer pveIngresoegreso) {
		this.pveIngresoegreso = pveIngresoegreso;
	}

	public Integer getPveTipoproceso() {
		return pveTipoproceso;
	}

	public void setPveTipoproceso(Integer pveTipoproceso) {
		this.pveTipoproceso = pveTipoproceso;
	}

	public Integer getPveProceso() {
		return pveProceso;
	}

	public void setPveProceso(Integer pveProceso) {
		this.pveProceso = pveProceso;
	}

	public Integer getPveFlag() {
		return pveFlag;
	}

	public void setPveFlag(Integer pveFlag) {
		this.pveFlag = pveFlag;
	}

}
