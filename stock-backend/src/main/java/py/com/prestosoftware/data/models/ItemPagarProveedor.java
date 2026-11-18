package py.com.prestosoftware.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "item_pago_proveedores")
public class ItemPagarProveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ipp_numero")
	private Integer ippNumero;
	
	@Column(name = "ipp_secuencia_cuenta")
	private Integer ippSecuenciaCuenta;
	
	@Column(name = "ipp_monto")
	private Double ippMonto;
		
	public ItemPagarProveedor() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIppNumero() {
		return ippNumero;
	}

	public void setIppNumero(Integer ippNumero) {
		this.ippNumero = ippNumero;
	}

	public Integer getIppSecuenciaCuenta() {
		return ippSecuenciaCuenta;
	}

	public void setIppSecuenciaCuenta(Integer ippSecuenciaCuenta) {
		this.ippSecuenciaCuenta = ippSecuenciaCuenta;
	}

	public Double getIppMonto() {
		return ippMonto;
	}

	public void setIppMonto(Double ippMonto) {
		this.ippMonto = ippMonto;
	}

	
	
}
