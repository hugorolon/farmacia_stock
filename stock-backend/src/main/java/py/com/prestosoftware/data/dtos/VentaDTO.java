package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDTO {
	
	private Long id;
	
	private int operacion;
	
	private Date fecha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private String comprobante;
	private int condicion;
	
	private ClienteDTO cliente;
	
	private EmpresaDTO empresa;
	
	private String clienteNombre;
	
	private String clienteRuc;
	
	private String clienteDireccion;
	
	private UsuarioDTO vendedor;
	
	private CajaDTO caja;
	
	private DepositoDTO deposito;
	
	private Double totalExentas;
	private Double totalGravada5;
	private Double totalGravada10;
	private Double totalGeneral;
	private Double totalPagado;
	private Double totalIva5;
	private Double totalIva10;
	private Double totalDescuento;
	private Double totalFlete;
	private Double cantItem;
	private Long timbrado;

	private Date vencimiento;
	private String obs;
	private String situacion;
	
	private List<VentaDetalleDTO> items = new ArrayList<>();
	
	public VentaDTO() {}

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

	public int getCondicion() {
		return condicion;
	}

	public void setCondicion(int condicion) {
		this.condicion = condicion;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public DepositoDTO getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}

	public Double getTotalExentas() {
		return totalExentas;
	}

	public void setTotalExentas(Double totalExentas) {
		this.totalExentas = totalExentas;
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

	public Date getHora() {
		return hora;
	}
	
	public void setHora(Date hora) {
		this.hora = hora;
	}

	public UsuarioDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(UsuarioDTO vendedor) {
		this.vendedor = vendedor;
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
	
	public String getClienteNombre() {
		return clienteNombre;
	}
	
	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public List<VentaDetalleDTO> getItems() {
		return items;
	}

	public void setItems(List<VentaDetalleDTO> items) {
		this.items = items;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getTotalFlete() {
		return totalFlete;
	}

	public void setTotalFlete(Double totalFlete) {
		this.totalFlete = totalFlete;
	}

	public Double getCantItem() {
		return cantItem;
	}

	public void setCantItem(Double cantItem) {
		this.cantItem = cantItem;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public String getClienteRuc() {
		return clienteRuc;
	}

	public void setClienteRuc(String clienteRuc) {
		this.clienteRuc = clienteRuc;
	}

	public String getClienteDireccion() {
		return clienteDireccion;
	}

	public void setClienteDireccion(String clienteDireccion) {
		this.clienteDireccion = clienteDireccion;
	}
	
	public void setCaja(CajaDTO caja) {
		this.caja = caja;
	}
	
	public CajaDTO getCaja() {
		return caja;
	}
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
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

	public Long getTimbrado() {
		return timbrado;
	}

	public void setTimbrado(Long timbrado) {
		this.timbrado = timbrado;
	}
	
	
}
