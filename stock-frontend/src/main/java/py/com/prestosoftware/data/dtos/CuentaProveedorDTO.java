package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;


public class CuentaProveedorDTO {
	
	private Long id;
	
	private ProveedorDTO proveedor;
	
	private String proveedorNombre;
	
	private String documento;
	
	private String tipo;
	
	private Date fecha;
	
	private Date vencimiento;
	
	private Double debito;
	
	private Double credito;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private MonedaDTO moneda;
	
	private String obs;
	
	private String situacion;
	
	private Long usuarioId;
	private Double valorTotal;
	private Double valorPagado;
	private List<CuentaProveedorDTO> cuentas;
	
	public CuentaProveedorDTO() { }

	public CuentaProveedorDTO(ProveedorDTO proveedor, String proveedorNombre, String documento, String tipo,
			Date fecha, Date vencimiento, Double debito, Double credito, Date hora,
			MonedaDTO moneda, String obs, String situacion, Long usuarioId, Double valorTotal,
			Double valorPagado) {
		super();
		this.proveedor = proveedor;
		this.proveedorNombre = proveedorNombre;
		this.documento = documento;
		this.tipo = tipo;
		this.fecha = fecha;
		this.vencimiento = vencimiento;
		this.debito = debito;
		this.credito = credito;
		this.hora = hora;
		this.moneda = moneda;
		this.obs = obs;
		this.situacion = situacion;
		this.usuarioId = usuarioId;
		this.valorTotal = valorTotal;
		this.valorPagado = valorPagado;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getDebito() {
		return debito;
	}

	public void setDebito(Double debito) {
		this.debito = debito;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

	public Date getHora() {
		return hora;
	}
	
	public void setHora(Date hora) {
		this.hora = hora;
	}

	public MonedaDTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaDTO moneda) {
		this.moneda = moneda;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getProveedorNombre() {
		return proveedorNombre;
	}

	public void setProveedorNombre(String proveedorNombre) {
		this.proveedorNombre = proveedorNombre;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorPagado() {
		return valorPagado;
	}

	public void setValorPagado(Double valorPagado) {
		this.valorPagado = valorPagado;
	}
	
	public List<CuentaProveedorDTO> getCuentas() {
		return cuentas;
	}
	
	public void setCuentas(List<CuentaProveedorDTO> cuentas) {
		this.cuentas = cuentas;
	}

}
