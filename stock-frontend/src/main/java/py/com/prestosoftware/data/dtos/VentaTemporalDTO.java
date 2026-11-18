package py.com.prestosoftware.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaTemporalDTO {
	
	private Long id;
	
	private int operacion;
	
	private Date fecha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "America/Asuncion")
	private Date hora;
	
	private String comprobante;
	private int condicion;
	
	private ClienteDTO cliente;
		
	private String clienteNombre;
	
	private String clienteRuc;
	
	private String clienteDireccion;
	
	private Double totalGeneral;
	
	private Double totalPagado;
	
	private Double cantItem;
	
	private Long timbrado;
	
	private String situacion;
	private List<VentaDetalleTemporalDTO> items = new ArrayList<>();
	
	public VentaTemporalDTO() {}

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

	
	public Double getTotalGeneral() {
		return totalGeneral;
	}

	public void setTotalGeneral(Double totalGeneral) {
		this.totalGeneral = totalGeneral;
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

	public String getClienteNombre() {
		return clienteNombre;
	}
	
	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public List<VentaDetalleTemporalDTO> getItems() {
		return items;
	}

	public void setItems(List<VentaDetalleTemporalDTO> items) {
		this.items = items;
	}

	public Double getCantItem() {
		return cantItem;
	}

	public void setCantItem(Double cantItem) {
		this.cantItem = cantItem;
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
