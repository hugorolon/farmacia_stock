package py.com.prestosoftware.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "config_compras")
public class ConfigCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "pide_deposito")
	private int pideDeposito;
	
	@Column(name = "pide_descuento")
	private int pideDescuento;
	
	@Column(name = "pide_gasto")
	private int pideGasto;

	public ConfigCompra() {}
	
	public ConfigCompra(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPideDeposito() {
		return pideDeposito;
	}

	public void setPideDeposito(int pideDeposito) {
		this.pideDeposito = pideDeposito;
	}

	public int getPideDescuento() {
		return pideDescuento;
	}

	public void setPideDescuento(int pideDescuento) {
		this.pideDescuento = pideDescuento;
	}

	public int getPideGasto() {
		return pideGasto;
	}
	
	public void setPideGasto(int pideGasto) {
		this.pideGasto = pideGasto;
	}
	
}
