package py.com.prestosoftware.data.dtos;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




public class CobroClienteDTO {
	

	private Integer cclNumero;
	
	private String cclDocumento;

	private Integer cclEntidad;
	
	private Integer cclTipoEntidad;
	
	private Integer cclCuota;
	
	private Date fecha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private Integer cclSituacion;

	private Double cclValor;

	private Double cclDescuento;

	private Double cclRecargo;

	private Double cclMonto;

	private Double cclCobrado;

	private Integer cclFormaPago;

	private Integer cclMoneda;
	
	public CobroClienteDTO() { }
	
	
	public Integer getCclNumero() {
		return cclNumero;
	}


	public void setCclNumero(Integer cclNumero) {
		this.cclNumero = cclNumero;
	}


	public String getCclDocumento() {
		return cclDocumento;
	}


	public void setCclDocumento(String cclDocumento) {
		this.cclDocumento = cclDocumento;
	}


	public Integer getCclEntidad() {
		return cclEntidad;
	}


	public void setCclEntidad(Integer cclEntidad) {
		this.cclEntidad = cclEntidad;
	}


	public Integer getCclTipoEntidad() {
		return cclTipoEntidad;
	}


	public void setCclTipoEntidad(Integer cclTipoEntidad) {
		this.cclTipoEntidad = cclTipoEntidad;
	}


	public Integer getCclCuota() {
		return cclCuota;
	}


	public void setCclCuota(Integer cclCuota) {
		this.cclCuota = cclCuota;
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


	public Integer getCclSituacion() {
		return cclSituacion;
	}


	public void setCclSituacion(Integer cclSituacion) {
		this.cclSituacion = cclSituacion;
	}


	public Double getCclValor() {
		return cclValor;
	}


	public void setCclValor(Double cclValor) {
		this.cclValor = cclValor;
	}


	public Double getCclDescuento() {
		return cclDescuento;
	}


	public void setCclDescuento(Double cclDescuento) {
		this.cclDescuento = cclDescuento;
	}


	public Double getCclRecargo() {
		return cclRecargo;
	}


	public void setCclRecargo(Double cclRecargo) {
		this.cclRecargo = cclRecargo;
	}


	public Double getCclMonto() {
		return cclMonto;
	}


	public void setCclMonto(Double cclMonto) {
		this.cclMonto = cclMonto;
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
		return "CuentaCorrienteCliente [id=" + cclNumero+ "]";
	}

}
