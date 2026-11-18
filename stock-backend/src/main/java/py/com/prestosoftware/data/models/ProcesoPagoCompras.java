package py.com.prestosoftware.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "proceso_pago_compras")
public class ProcesoPagoCompras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "pco_compra")
	private Integer pcoCompra;

	@Column(name = "pco_proceso")
	private Integer pcoProceso;
	
	@Column(name = "pco_ingresoegreso")
	private Integer pcoIngresoEgreso;
	
	@Column(name = "pco_tipoproceso")
	private Integer pcoTipoproceso;
	
	@Column(name = "pco_flag")
	private Integer pcoFlag;
	
	public ProcesoPagoCompras() {}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getPcoCompra() {
		return pcoCompra;
	}



	public void setPcoCompra(Integer pcoCompra) {
		this.pcoCompra = pcoCompra;
	}



	public Integer getPcoProceso() {
		return pcoProceso;
	}



	public void setPcoProceso(Integer pcoProceso) {
		this.pcoProceso = pcoProceso;
	}



	public Integer getPcoIngresoEgreso() {
		return pcoIngresoEgreso;
	}



	public void setPcoIngresoEgreso(Integer pcoIngresoEgreso) {
		this.pcoIngresoEgreso = pcoIngresoEgreso;
	}



	public Integer getPcoTipoproceso() {
		return pcoTipoproceso;
	}



	public void setPcoTipoproceso(Integer pcoTipoproceso) {
		this.pcoTipoproceso = pcoTipoproceso;
	}



	public Integer getPcoFlag() {
		return pcoFlag;
	}



	public void setPcoFlag(Integer pcoFlag) {
		this.pcoFlag = pcoFlag;
	}




}
