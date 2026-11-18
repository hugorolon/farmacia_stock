package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PagarProveedorDTO {
	
	private Integer pprNumero;
	
	private String pprDocumento;

	private Integer pprEntidad;
	
	private Integer pprTipoEntidad;
	
	private Integer pprCuota;
	
	private Date fecha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private Integer pprSituacion;
	private Double pprValor;
	private Double pprDescuento;

	private Double pprRecargo;

	private Double pprMonto;

	private Double cclCobrado;

	private Integer cclFormaPago;

	private Integer cclMoneda;
	
	public PagarProveedorDTO() { }
	


	public Integer getPprNumero() {
		return pprNumero;
	}




	public void setPprNumero(Integer pprNumero) {
		this.pprNumero = pprNumero;
	}




	public String getPprDocumento() {
		return pprDocumento;
	}




	public void setPprDocumento(String pprDocumento) {
		this.pprDocumento = pprDocumento;
	}




	public Integer getPprEntidad() {
		return pprEntidad;
	}




	public void setPprEntidad(Integer pprEntidad) {
		this.pprEntidad = pprEntidad;
	}




	public Integer getPprTipoEntidad() {
		return pprTipoEntidad;
	}




	public void setPprTipoEntidad(Integer pprTipoEntidad) {
		this.pprTipoEntidad = pprTipoEntidad;
	}




	public Integer getPprCuota() {
		return pprCuota;
	}




	public void setPprCuota(Integer pprCuota) {
		this.pprCuota = pprCuota;
	}




	public Date getFecha() {
		return fecha;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	public Date getHora() {
		return hora;
	}




	public void setHora(Date hora) {
		this.hora = hora;
	}




	public Integer getPprSituacion() {
		return pprSituacion;
	}




	public void setPprSituacion(Integer pprSituacion) {
		this.pprSituacion = pprSituacion;
	}




	public Double getPprValor() {
		return pprValor;
	}




	public void setPprValor(Double pprValor) {
		this.pprValor = pprValor;
	}




	public Double getPprDescuento() {
		return pprDescuento;
	}




	public void setPprDescuento(Double pprDescuento) {
		this.pprDescuento = pprDescuento;
	}




	public Double getPprRecargo() {
		return pprRecargo;
	}




	public void setPprRecargo(Double pprRecargo) {
		this.pprRecargo = pprRecargo;
	}




	public Double getPprMonto() {
		return pprMonto;
	}




	public void setPprMonto(Double pprMonto) {
		this.pprMonto = pprMonto;
	}




	public Double getCclCobrado() {
		return cclCobrado;
	}




	public void setCclCobrado(Double cclCobrado) {
		this.cclCobrado = cclCobrado;
	}




	public Integer getCclFormaPago() {
		return cclFormaPago;
	}




	public void setCclFormaPago(Integer cclFormaPago) {
		this.cclFormaPago = cclFormaPago;
	}




	public Integer getCclMoneda() {
		return cclMoneda;
	}




	public void setCclMoneda(Integer cclMoneda) {
		this.cclMoneda = cclMoneda;
	}




	@Override
	public String toString() {
		return "CuentaCorrienteProveedores[id=" + pprNumero+ "]";
	}

}
