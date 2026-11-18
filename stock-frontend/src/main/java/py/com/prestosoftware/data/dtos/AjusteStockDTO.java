package py.com.prestosoftware.data.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class AjusteStockDTO {
	
	private Long id;
	
	private String tipoAjuste;
	
	private Date fecha;
	
	private DepositoDTO deposito;
	
	private UsuarioDTO usuario;
	
	private List<AjusteStockDetalleDTO> items = new ArrayList<>();
	
	private String obs;
	
	private String situacion;
	
	public AjusteStockDTO() {}

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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public DepositoDTO getDeposito() {
		return deposito;
	}

	public void setDeposito(DepositoDTO deposito) {
		this.deposito = deposito;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getSituacion() {
		return situacion;
	}
	
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	
	public List<AjusteStockDetalleDTO> getItems() {
		return items;
	}
	
	public void setItems(List<AjusteStockDetalleDTO> items) {
		this.items = items;
	}
	
	public String getTipoAjuste() {
		return tipoAjuste;
	}
	
	public void setTipoAjuste(String tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}

}
