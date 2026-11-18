package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransformacionProductoDTO {
	
	private Long id;
	
	private Date fecha;
	private Integer cantidad;
	private Integer situacion;
	private String usuario;
	private String obs;	
	private ProductoDTO productoOrigen;
	
	private DepositoDTO depositoOrigen;
	
	private DepositoDTO depositoDestino;
	
	
	private List<TransformacionProductoDetalleDTO> items = new ArrayList<>();
	
	
	public TransformacionProductoDTO() {}

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
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public ProductoDTO getProductoOrigen() {
		return productoOrigen;
	}

	public void setProductoOrigen(ProductoDTO productoOrigen) {
		this.productoOrigen = productoOrigen;
	}

	public DepositoDTO getDepositoOrigen() {
		return depositoOrigen;
	}

	public void setDepositoOrigen(DepositoDTO depositoOrigen) {
		this.depositoOrigen = depositoOrigen;
	}

	public DepositoDTO getDepositoDestino() {
		return depositoDestino;
	}

	public void setDepositoDestino(DepositoDTO depositoDestino) {
		this.depositoDestino = depositoDestino;
	}

	public Integer getSituacion() {
		return situacion;
	}

	public void setSituacion(Integer situacion) {
		this.situacion = situacion;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public List<TransformacionProductoDetalleDTO> getItems() {
		return items;
	}

	public void setItems(List<TransformacionProductoDetalleDTO> items) {
		this.items = items;
	}

}
