package py.com.prestosoftware.data.dtos;


public class ProcesoPagoComprasDTO {
	
	private Integer id;
	
	private Integer pcoCompra;

	private Integer pcoProceso;
	
	private Integer pcoIngresoEgreso;
	
	private Integer pcoTipoproceso;
	
	private Integer pcoFlag;
	
	public ProcesoPagoComprasDTO() {}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getPcoCompra() {
		return pcoCompra;
	}



	public void setPcoCompra(Integer pcoCompra) {
		this.pcoCompra = pcoCompra;
	}



	public Integer getPcoProceso() {
		return pcoProceso;
	}



	public void setPcoProceso(Integer pcoProceso) {
		this.pcoProceso = pcoProceso;
	}



	public Integer getPcoIngresoEgreso() {
		return pcoIngresoEgreso;
	}



	public void setPcoIngresoEgreso(Integer pcoIngresoEgreso) {
		this.pcoIngresoEgreso = pcoIngresoEgreso;
	}



	public Integer getPcoTipoproceso() {
		return pcoTipoproceso;
	}



	public void setPcoTipoproceso(Integer pcoTipoproceso) {
		this.pcoTipoproceso = pcoTipoproceso;
	}



	public Integer getPcoFlag() {
		return pcoFlag;
	}



	public void setPcoFlag(Integer pcoFlag) {
		this.pcoFlag = pcoFlag;
	}




}
