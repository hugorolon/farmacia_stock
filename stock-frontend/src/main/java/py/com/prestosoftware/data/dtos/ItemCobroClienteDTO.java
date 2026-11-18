package py.com.prestosoftware.data.dtos;

public class ItemCobroClienteDTO {
	private Integer id;
	
	private Integer iclNumero;
	
	private Integer iclSecuenciaCuenta;
	
	private Double iclMonto;
		
	public ItemCobroClienteDTO() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getIclNumero() {
		return iclNumero;
	}



	public void setIclNumero(Integer iclNumero) {
		this.iclNumero = iclNumero;
	}



	public Integer getIclSecuenciaCuenta() {
		return iclSecuenciaCuenta;
	}


	public void setIclSecuenciaCuenta(Integer iclSecuenciaCuenta) {
		this.iclSecuenciaCuenta = iclSecuenciaCuenta;
	}


	public Double getIclMonto() {
		return iclMonto;
	}

	public void setIclMonto(Double iclMonto) {
		this.iclMonto = iclMonto;
	}

	
	
}
