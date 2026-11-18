package py.com.prestosoftware.data.dtos;

import java.util.Date;

public class ItemCuentaAPagarDTO {
	
	private Integer id;
	
	private Integer icpSecuencia;
	
	private Integer icpCuenta;

	private Integer icpDias;
	
	private Date icpVencimiento;
	
	private Double icpMonto;
	
	private String icpDocumento;

	private Integer icpSituacion;


	public ItemCuentaAPagarDTO() { }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIcpSecuencia() {
		return icpSecuencia;
	}


	public void setIcpSecuencia(Integer icpSecuencia) {
		this.icpSecuencia = icpSecuencia;
	}


	public Integer getIcpCuenta() {
		return icpCuenta;
	}


	public void setIcpCuenta(Integer icpCuenta) {
		this.icpCuenta = icpCuenta;
	}


	public Integer getIcpDias() {
		return icpDias;
	}


	public void setIcpDias(Integer icpDias) {
		this.icpDias = icpDias;
	}


	public Date getIcpVencimiento() {
		return icpVencimiento;
	}


	public void setIcpVencimiento(Date icpVencimiento) {
		this.icpVencimiento = icpVencimiento;
	}


	public Double getIcpMonto() {
		return icpMonto;
	}


	public void setIcpMonto(Double icpMonto) {
		this.icpMonto = icpMonto;
	}


	public String getIcpDocumento() {
		return icpDocumento;
	}


	public void setIcpDocumento(String icpDocumento) {
		this.icpDocumento = icpDocumento;
	}


	public Integer getIcpSituacion() {
		return icpSituacion;
	}


	public void setIcpSituacion(Integer icpSituacion) {
		this.icpSituacion = icpSituacion;
	}
	
	

	
}
