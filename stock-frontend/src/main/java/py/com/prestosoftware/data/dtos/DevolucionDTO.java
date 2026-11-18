package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DevolucionDTO {
	
	private Long id;
	
	private Date fecha;
	
	private Date vencimiento;
	
	private String comprobante;
	private String credito;
	
	private String tipo;
	
	private Double cantItem;
	
	private Long refId;
	
	private String referencia;
	
	private MonedaDTO moneda;
	
	private DepositoDTO deposito;
	
	private UsuarioDTO vendedor;
	
	private Double cotizacion;
	
	private Double totalExenta;
	
	private Double totalGravada5;
	
	private Double totalGravada10;
	
	private Double totalIva5;
	
	private Double totalIva10;
	
	private Double totalGeneral;
	
	private String obs;
	
	private String situacion;
	
	private List<DevolucionDetalleDTO> items = new ArrayList<>();
	
	public DevolucionDTO() {}

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

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getCredito() {
		return credito;
	}

	public void setCredito(String credito) {
		this.credito = credito;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getRefId() {
		return refId;
	}
	
	public void setRefId(Long refId) {
		this.refId = refId;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public Double getTotalExenta() {
		return totalExenta;
	}

	public void setTotalExenta(Double totalExenta) {
		this.totalExenta = totalExenta;
	}

	public Double getTotalGravada5() {
		return totalGravada5;
	}

	public void setTotalGravada5(Double totalGravada5) {
		this.totalGravada5 = totalGravada5;
	}

	public Double getTotalGravada10() {
		return totalGravada10;
	}

	public void setTotalGravada10(Double totalGravada10) {
		this.totalGravada10 = totalGravada10;
	}

	public Double getTotalIva5() {
		return totalIva5;
	}

	public void setTotalIva5(Double totalIva5) {
		this.totalIva5 = totalIva5;
	}

	public Double getTotalIva10() {
		return totalIva10;
	}

	public void setTotalIva10(Double totalIva10) {
		this.totalIva10 = totalIva10;
	}

	public Double getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(Double totalGeneral) {
		this.totalGeneral = totalGeneral;
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
	
	public DepositoDTO getDeposito() {
		return deposito;
	}
	
	public Double getCantItem() {
		return cantItem;
	}
	
	public void setCantItem(Double cantItem) {
		this.cantItem = cantItem;
	}
	
	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}

	public List<DevolucionDetalleDTO> getItems() {
		return items;
	}

	public void setItems(List<DevolucionDetalleDTO> items) {
		this.items = items;
	}

	public UsuarioDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(UsuarioDTO vendedor) {
		this.vendedor = vendedor;
	}

}
