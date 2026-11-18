package py.com.prestosoftware.data.dtos;

public class ProcesoCobroClientesDTO {
	
	private Integer pccCobro;

	private Integer pccProceso;
	
	private Integer pccIngreso;
	
	private Integer pccTipoproceso;
	
	private Integer pccFlag;
	
	public ProcesoCobroClientesDTO() {}

	
	public Integer getPccCobro() {
		return pccCobro;
	}

	public void setPccCobro(Integer pccCobro) {
		this.pccCobro = pccCobro;
	}

	public Integer getPccIngreso() {
		return pccIngreso;
	}

	public void setPccIngreso(Integer pccIngreso) {
		this.pccIngreso = pccIngreso;
	}

	public Integer getPccTipoproceso() {
		return pccTipoproceso;
	}

	public void setPccTipoproceso(Integer pccTipoproceso) {
		this.pccTipoproceso = pccTipoproceso;
	}

	public Integer getPccProceso() {
		return pccProceso;
	}

	public void setPccProceso(Integer pccProceso) {
		this.pccProceso = pccProceso;
	}

	public Integer getPccFlag() {
		return pccFlag;
	}

	public void setPccFlag(Integer pccFlag) {
		this.pccFlag = pccFlag;
	}

}
