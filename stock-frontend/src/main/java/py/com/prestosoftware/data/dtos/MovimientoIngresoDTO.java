package py.com.prestosoftware.data.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MovimientoIngresoDTO {
	

	private Integer minNumero;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Asuncion")
	private Date fecha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private String minDocumento;
	
	private Integer minTipoProceso;

	private Integer minProceso;

	private Integer minCaja;

	private String minEntidad;
	
	private Integer minTipoEntidad;
	
	private Integer minSituacion;
	
	
	public MovimientoIngresoDTO() {}

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

	public Integer getMinNumero() {
		return minNumero;
	}

	public void setMinNumero(Integer minNumero) {
		this.minNumero = minNumero;
	}

	public String getMinDocumento() {
		return minDocumento;
	}

	public void setMinDocumento(String minDocumento) {
		this.minDocumento = minDocumento;
	}

	public Integer getMinTipoProceso() {
		return minTipoProceso;
	}

	public void setMinTipoProceso(Integer minTipoProceso) {
		this.minTipoProceso = minTipoProceso;
	}

	public Integer getMinProceso() {
		return minProceso;
	}

	public void setMinProceso(Integer minProceso) {
		this.minProceso = minProceso;
	}

	public Integer getMinCaja() {
		return minCaja;
	}

	public void setMinCaja(Integer minCaja) {
		this.minCaja = minCaja;
	}

	public String getMinEntidad() {
		return minEntidad;
	}

	public void setMinEntidad(String minEntidad) {
		this.minEntidad = minEntidad;
	}

	public Integer getMinTipoEntidad() {
		return minTipoEntidad;
	}

	public void setMinTipoEntidad(Integer minTipoEntidad) {
		this.minTipoEntidad = minTipoEntidad;
	}

	public Integer getMinSituacion() {
		return minSituacion;
	}

	public void setMinSituacion(Integer minSituacion) {
		this.minSituacion = minSituacion;
	}
	
	
}
