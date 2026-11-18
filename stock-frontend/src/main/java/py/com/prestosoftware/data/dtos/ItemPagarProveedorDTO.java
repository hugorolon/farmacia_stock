package py.com.prestosoftware.data.dtos;

public class ItemPagarProveedorDTO {
	private Integer id;
	
	private Integer ippNumero;
	
	private Integer ippSecuenciaCuenta;
	
	private Double ippMonto;
		
	public ItemPagarProveedorDTO() {}
	
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
