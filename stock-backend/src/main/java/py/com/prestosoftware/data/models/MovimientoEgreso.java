package py.com.prestosoftware.data.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "movimiento_egresos")
public class MovimientoEgreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "meg_numero")
	private Integer megNumero;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Asuncion")
	@Column(name = "meg_fecha")
	private Date fecha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	@Temporal(TemporalType.TIME)
	@Column(name = "meg_hora")
	private Date hora;
	
	@Column(name = "meg_documento")
	private String megDocumento;
	
	@Column(name = "meg_tipoproceso")
	private Integer megTipoProceso;

	@Column(name = "meg_proceso")
	private Integer megProceso;

	@Column(name = "meg_caja")
	private Integer megCaja;

	@Column(name = "meg_entidad")
	private String megEntidad;
	
	@Column(name = "meg_tipoentidad")
	private Integer megTipoEntidad;
	
	@Column(name = "meg_situacion")
	private Integer megSituacion;
	
	
	public MovimientoEgreso() {}

	
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
