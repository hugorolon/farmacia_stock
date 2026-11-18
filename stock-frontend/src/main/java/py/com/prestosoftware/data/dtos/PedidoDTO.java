package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoDTO {
	
	private Long id;
	
	private Date fecha;
	
	private ProveedorDTO proveedor;
	
	private MonedaDTO moneda;
	
	private String comprobante;
	private String condicion;
	private Double cotizacion;
	
	private Double totalGeneral;
	
	private Double totalItem;
	
	private String situacion;
	private String obs;
	
	private List<PedidoDetalleDTO> items = new ArrayList<>();
	
	
	public PedidoDTO() {}

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

	public ProveedorDTO getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
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

	public MonedaDTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaDTO moneda) {
		this.moneda = moneda;
	}

	public String getComprobante() {
		return comprobante;
	}

	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public Double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	public List<PedidoDetalleDTO> getItems() {
		return items;
	}

	public void setItems(List<PedidoDetalleDTO> items) {
		this.items = items;
	}

	public Double getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Double totalItem) {
		this.totalItem = totalItem;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
}
