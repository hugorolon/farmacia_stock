package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


public class CuentaARecibirDTO {
	
	private Integer carNumero;

	private Date fecha;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private String nroBoleta;
	
	private Long idEntidad;
	
	private Integer tipoEntidad;

	private Double monto;
	
	private Integer carProceso;
	
	private Integer carSituacion;
	
	public CuentaARecibirDTO() { }
	
	public CuentaARecibirDTO(Integer carNumero,Date fecha, Date hora, String nroBoleta, Long idEntidad, Integer tipoEntidad, Double monto, Integer carProceso,
			Integer carSituacion) {
		super();
		this.carNumero=carNumero;
		this.fecha=fecha;
		this.hora=hora;
		this.nroBoleta=nroBoleta;
		this.idEntidad=idEntidad;
		this.tipoEntidad=tipoEntidad;
		this.monto=monto;
		this.carProceso=carProceso;
		this.carSituacion=carSituacion;
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
	
	public Integer getCarNumero() {
		return carNumero;
	}

	public void setCarNumero(Integer carNumero) {
		this.carNumero = carNumero;
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

	public Integer getCarProceso() {
		return carProceso;
	}

	public void setCarProceso(Integer carProceso) {
		this.carProceso = carProceso;
	}

	public Integer getCarSituacion() {
		return carSituacion;
	}

	public void setCarSituacion(Integer carSituacion) {
		this.carSituacion = carSituacion;
	}

	@Override
	public String toString() {
		return "CuentaCorrienteCliente [id=" + carNumero + "]";
	}

}
