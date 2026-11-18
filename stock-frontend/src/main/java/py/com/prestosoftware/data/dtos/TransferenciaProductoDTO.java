package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class TransferenciaProductoDTO {
	
	private Long id;
	
	private Date fecha;
	private String comprobante;
	
	private DepositoDTO depositoOrigen;
	
	private DepositoDTO depositoDestino;
	
	private String obs;
	private String situacion;
	
	private List<TransferenciaProductoDetalleDTO> items = new ArrayList<>();
	
	
	public TransferenciaProductoDTO() {}

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

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public List<TransferenciaProductoDetalleDTO> getItems() {
		return items;
	}

	public void setItems(List<TransferenciaProductoDetalleDTO> items) {
		this.items = items;
	}

}
