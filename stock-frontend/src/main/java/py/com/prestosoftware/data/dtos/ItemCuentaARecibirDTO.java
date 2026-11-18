package py.com.prestosoftware.data.dtos;

import java.util.Date;

public class ItemCuentaARecibirDTO {
	
	private Integer icaSecuencia;
	
	private Integer icaCuenta;

	private Integer icaDias;
	
	private Date icaVencimiento;
	
	private Double icaMonto;
	
	private Double icaIva;

	private String icaDocumento;

	private Integer icaSituacion;

	public ItemCuentaARecibirDTO() { }
	
	

	public Integer getIcaCuenta() {
		return icaCuenta;
	}


	public void setIcaCuenta(Integer icaCuenta) {
		this.icaCuenta = icaCuenta;
	}


	public Integer getIcaSecuencia() {
		return icaSecuencia;
	}


	public void setIcaSecuencia(Integer icaSecuencia) {
		this.icaSecuencia = icaSecuencia;
	}


	public Integer getIcaDias() {
		return icaDias;
	}


	public void setIcaDias(Integer icaDias) {
		this.icaDias = icaDias;
	}


	public Date getIcaVencimiento() {
		return icaVencimiento;
	}


	public void setIcaVencimiento(Date icaVencimiento) {
		this.icaVencimiento = icaVencimiento;
	}


	public Double getIcaMonto() {
		return icaMonto;
	}


	public void setIcaMonto(Double icaMonto) {
		this.icaMonto = icaMonto;
	}


	public Double getIcaIva() {
		return icaIva;
	}


	public void setIcaIva(Double icaIva) {
		this.icaIva = icaIva;
	}


	public String getIcaDocumento() {
		return icaDocumento;
	}


	public void setIcaDocumento(String icaDocumento) {
		this.icaDocumento = icaDocumento;
	}


	public Integer getIcaSituacion() {
		return icaSituacion;
	}


	public void setIcaSituacion(Integer icaSituacion) {
		this.icaSituacion = icaSituacion;
	}

}
