package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresupuestoDTO {
	
	private Long id;

	private Date fecha;
	private String comprobante;
	private String situacion;
	
	private int cantItem;
	
	private ClienteDTO cliente;
	
	private UsuarioDTO vendedor;
	
	private Double totalGeneral;
	
	private List<PresupuestoDetalleDTO> items = new ArrayList<>();
	
	
	public PresupuestoDTO() {}

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

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
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

	public List<PresupuestoDetalleDTO> getItems() {
		return items;
	}

	public void setItems(List<PresupuestoDetalleDTO> items) {
		this.items = items;
	}

	public int getCantItem() {
		return cantItem;
	}

	public void setCantItem(int cantItem) {
		this.cantItem = cantItem;
	}

	public UsuarioDTO getVendedor() {
		return vendedor;
	}

	public void setVendedor(UsuarioDTO vendedor) {
		this.vendedor = vendedor;
	}
	
}
