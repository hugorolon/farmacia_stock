package py.com.prestosoftware.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "proceso_pago_proveedores")
public class ProcesoPagoProveedores {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ppp_pago")
	private Integer pppPago;

	@Column(name = "ppp_proceso")
	private Integer pppProceso;
	
	@Column(name = "ppp_ingresoegreso")
	private Integer pppIngresoEgreso;
	
	@Column(name = "ppp_tipoproceso")
	private Integer pppTipoproceso;
	
	@Column(name = "ppp_flag")
	private Integer pppFlag;
	
	public ProcesoPagoProveedores() {}

	

	
	public Integer getPppPago() {
		return pppPago;
	}



	public void setPppPago(Integer pppPago) {
		this.pppPago = pppPago;
	}



	public Integer getPppProceso() {
		return pppProceso;
	}



	public void setPppProceso(Integer pppProceso) {
		this.pppProceso = pppProceso;
	}



	public Integer getPppIngresoEgreso() {
		return pppIngresoEgreso;
	}



	public void setPppIngresoEgreso(Integer pppIngresoEgreso) {
		this.pppIngresoEgreso = pppIngresoEgreso;
	}



	public Integer getPppTipoproceso() {
		return pppTipoproceso;
	}



	public void setPppTipoproceso(Integer pppTipoproceso) {
		this.pppTipoproceso = pppTipoproceso;
	}



	public Integer getPppFlag() {
		return pppFlag;
	}



	public void setPppFlag(Integer pppFlag) {
		this.pppFlag = pppFlag;
	}



	

}
