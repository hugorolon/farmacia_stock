package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MovimientoCajaDTO {
	
	private Long id;
	
	private Date fecha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private Long usuario;
	
	private CajaDTO caja;
	
	private int planCuentaId;
	
	private MonedaDTO moneda;

	private Double cotizacion;
	
	private String tipoOperacion;
	
	private String notaNro;
	
	private Long idReferencia;
	
	private String notaReferencia;
	
	private Double notaValor;
	
	private Double consolidado;
	
	private String obs;
	
	private String situacion;
	
	private Double valorM01;
	
	private Double valorVuelto01;
	
	private Double valorM02;
	
	private Double valorVuelto02;
	
	private Double valorM03;
	
	private Double valorVuelto03;
	
	private Double valorM04;
	
	private Double valorVuelto04;
	
	private Double valorM05;
	
	private Double valorVuelto05;
	
	private int usuarioIdMod;
	
	private Date usuarioModFecha;
	
	public MovimientoCajaDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public CajaDTO getCaja() {
		return caja;
	}
	
	public void setCaja(CajaDTO caja) {
		this.caja = caja;
	}

	public int getPlanCuentaId() {
		return planCuentaId;
	}

	public void setPlanCuentaId(int planCuentaId) {
		this.planCuentaId = planCuentaId;
	}

	public String getNotaNro() {
		return notaNro;
	}

	public void setNotaNro(String notaNro) {
		this.notaNro = notaNro;
	}

	public Double getNotaValor() {
		return notaValor;
	}

	public void setNotaValor(Double notaValor) {
		this.notaValor = notaValor;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	
	public String getNotaReferencia() {
		return notaReferencia;
	}
	
	public void setNotaReferencia(String notaReferencia) {
		this.notaReferencia = notaReferencia;
	}
	
	public String getTipoOperacion() {
		return tipoOperacion;
	}
	
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}
	
	public MonedaDTO getMoneda() {
		return moneda;
	}
	
	public void setMoneda(MonedaDTO moneda) {
		this.moneda = moneda;
	}
	
	public Double getCotizacion() {
		return cotizacion;
	}
	
	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}
	
	public Double getConsolidado() {
		return consolidado;
	}
	
	public void setConsolidado(Double consolidado) {
		this.consolidado = consolidado;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public int getUsuarioIdMod() {
		return usuarioIdMod;
	}

	public void setUsuarioIdMod(int usuarioIdMod) {
		this.usuarioIdMod = usuarioIdMod;
	}

	public Date getUsuarioModFecha() {
		return usuarioModFecha;
	}

	public void setUsuarioModFecha(Date usuarioModFecha) {
		this.usuarioModFecha = usuarioModFecha;
	}

	public Double getValorM01() {
		return valorM01;
	}

	public void setValorM01(Double valorM01) {
		this.valorM01 = valorM01;
	}

	public Double getValorM02() {
		return valorM02;
	}

	public void setValorM02(Double valorM02) {
		this.valorM02 = valorM02;
	}

	public Double getValorM03() {
		return valorM03;
	}

	public void setValorM03(Double valorM03) {
		this.valorM03 = valorM03;
	}

	public Double getValorM04() {
		return valorM04;
	}

	public void setValorM04(Double valorM04) {
		this.valorM04 = valorM04;
	}

	public Double getValorM05() {
		return valorM05;
	}

	public void setValorM05(Double valorM05) {
		this.valorM05 = valorM05;
	}
	
	public Date getHora() {
		return hora;
	}
	
	public void setHora(Date hora) {
		this.hora = hora;
	}
	
	public Long getIdReferencia() {
		return idReferencia;
	}
	
	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}

	public Double getValorVuelto01() {
		return valorVuelto01;
	}

	public void setValorVuelto01(Double valorVuelto01) {
		this.valorVuelto01 = valorVuelto01;
	}

	public Double getValorVuelto02() {
		return valorVuelto02;
	}

	public void setValorVuelto02(Double valorVuelto02) {
		this.valorVuelto02 = valorVuelto02;
	}

	public Double getValorVuelto03() {
		return valorVuelto03;
	}

	public void setValorVuelto03(Double valorVuelto03) {
		this.valorVuelto03 = valorVuelto03;
	}

	public Double getValorVuelto04() {
		return valorVuelto04;
	}

	public void setValorVuelto04(Double valorVuelto04) {
		this.valorVuelto04 = valorVuelto04;
	}

	public Double getValorVuelto05() {
		return valorVuelto05;
	}

	public void setValorVuelto05(Double valorVuelto05) {
		this.valorVuelto05 = valorVuelto05;
	}
	
}
