package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CompraDTO {
	
	private Long id;
	
	private int operacion;
	
	private Date fecha;
	
	private Date fechaCompra;
	
	private Date vencimiento;
	
	private String tipoCompra;
	
	private String nroDespacho;
	
	private String factura;
	private String tc;
	private String comprobante;
	private int condicion;
	
	private ProveedorDTO proveedor;
	
	private String proveedorNombre;
	
	private MonedaDTO moneda;
	
	private UsuarioDTO usuario;
	
	private CajaDTO caja;
	
	private DepositoDTO deposito;
	
	private Double cotizacion;
	
	
	private Double totalExenta;
	
	private Double totalGravada5;
	
	private Double totalGravada10;
	
	private Double totalIva5;
	
	private Double totalIva10;
	
	private Double totalGeneral;
	
	private Double totalPagado;
	
	private Double totalFob;
	
	private Double totalCif;
	
	private Double totalItem;
	
	private Double descuento;
	
	private Integer cuotaCant;
	
	private Double gastos;
	
	private String obs;
	
	private String situacion;
	
	private List<CompraDetalleDTO> items = new ArrayList<>();
	
	public CompraDTO() {}

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

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public MonedaDTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaDTO moneda) {
		this.moneda = moneda;
	}

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getCondicion() {
		return condicion;
	}

	public void setCondicion(int condicion) {
		this.condicion = condicion;
	}

	public DepositoDTO getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
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
	
	public String getProveedorNombre() {
		return proveedorNombre;
	}
	
	public void setProveedorNombre(String proveedorNombre) {
		this.proveedorNombre = proveedorNombre;
	}
	
	public List<CompraDetalleDTO> getItems() {
		return items;
	}
	
	public void setItems(List<CompraDetalleDTO> items) {
		this.items = items;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public String getNroDespacho() {
		return nroDespacho;
	}

	public void setNroDespacho(String nroDespacho) {
		this.nroDespacho = nroDespacho;
	}

	public String getFactura() {
		return factura;
	}

	public void setFactura(String factura) {
		this.factura = factura;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}
	
	public Double getTotalItem() {
		return totalItem;
	}
	
	public void setTotalItem(Double totalItem) {
		this.totalItem = totalItem;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getGastos() {
		return gastos;
	}

	public void setGastos(Double gastos) {
		this.gastos = gastos;
	}

	public Double getTotalFob() {
		return totalFob;
	}

	public void setTotalFob(Double totalFob) {
		this.totalFob = totalFob;
	}

	public Double getTotalCif() {
		return totalCif;
	}

	public void setTotalCif(Double totalCif) {
		this.totalCif = totalCif;
	}
	
	public CajaDTO getCaja() {
		return caja;
	}
	
	public void setCaja(CajaDTO caja) {
		this.caja = caja;
	}
	
	public Date getVencimiento() {
		return vencimiento;
	}
	
	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	public int getOperacion() {
		return operacion;
	}
	
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}
	
	public Double getTotalPagado() {
		return totalPagado;
	}
	
	public void setTotalPagado(Double totalPagado) {
		this.totalPagado = totalPagado;
	}

	public Integer getCuotaCant() {
		return cuotaCant;
	}

	public void setCuotaCant(Integer cuotaCant) {
		this.cuotaCant = cuotaCant;
	}

	
}
