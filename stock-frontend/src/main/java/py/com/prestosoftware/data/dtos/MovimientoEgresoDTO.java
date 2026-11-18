package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class MovimientoEgresoDTO {
	
	private Integer megNumero;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Asuncion")
	private Date fecha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private String megDocumento;
	
	private Integer megTipoProceso;

	private Integer megProceso;

	private Integer megCaja;

	private String megEntidad;
	
	private Integer megTipoEntidad;
	
	private Integer megSituacion;
	
	
	public MovimientoEgresoDTO() {}

	
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

	public Integer getMegNumero() {
		return megNumero;
	}

	public void setMegNumero(Integer megNumero) {
		this.megNumero = megNumero;
	}

	public String getMegDocumento() {
		return megDocumento;
	}

	public void setMegDocumento(String megDocumento) {
		this.megDocumento = megDocumento;
	}

	public Integer getMegTipoProceso() {
		return megTipoProceso;
	}

	public void setMegTipoProceso(Integer megTipoProceso) {
		this.megTipoProceso = megTipoProceso;
	}

	public Integer getMegProceso() {
		return megProceso;
	}

	public void setMegProceso(Integer megProceso) {
		this.megProceso = megProceso;
	}

	public Integer getMegCaja() {
		return megCaja;
	}

	public void setMegCaja(Integer megCaja) {
		this.megCaja = megCaja;
	}

	public String getMegEntidad() {
		return megEntidad;
	}

	public void setMegEntidad(String megEntidad) {
		this.megEntidad = megEntidad;
	}


	public Integer getMegTipoEntidad() {
		return megTipoEntidad;
	}


	public void setMegTipoEntidad(Integer megTipoEntidad) {
		this.megTipoEntidad = megTipoEntidad;
	}


	public Integer getMegSituacion() {
		return megSituacion;
	}


	public void setMegSituacion(Integer megSituacion) {
		this.megSituacion = megSituacion;
	}

	
}
