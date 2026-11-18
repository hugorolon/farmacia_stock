package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class CuentaAPagarDTO {
	
	private Integer capNumero;

	private Date fecha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private String nroBoleta;
	
	private Long idEntidad;
	
	private Integer tipoEntidad;

	private Double monto;
	
	private Integer capProceso;
	
	private Integer capSituacion;
	
	public CuentaAPagarDTO() { }
	
	public CuentaAPagarDTO(Integer capNumero,Date fecha, Date hora, String nroBoleta, Long idEntidad, Integer tipoEntidad, Double monto, Integer capProceso,
			Integer capSituacion) {
		super();
		this.capNumero=capNumero;
		this.fecha=fecha;
		this.hora=hora;
		this.nroBoleta=nroBoleta;
		this.idEntidad=idEntidad;
		this.tipoEntidad=tipoEntidad;
		this.monto=monto;
		this.capProceso=capProceso;
		this.capSituacion=capSituacion;
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
	

	public Integer getCapNumero() {
		return capNumero;
	}

	public void setCapNumero(Integer capNumero) {
		this.capNumero = capNumero;
	}

	public String getNroBoleta() {
		return nroBoleta;
	}

	public void setNroBoleta(String nroBoleta) {
		this.nroBoleta = nroBoleta;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(Long idEntidad) {
		this.idEntidad = idEntidad;
	}

	public Integer getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(Integer tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Integer getCapProceso() {
		return capProceso;
	}

	public void setCapProceso(Integer capProceso) {
		this.capProceso = capProceso;
	}

	public Integer getCapSituacion() {
		return capSituacion;
	}

	public void setCapSituacion(Integer capSituacion) {
		this.capSituacion = capSituacion;
	}

	@Override
	public String toString() {
		return "CuentaCorrienteCliente [id=" + capNumero + "]";
	}

}
