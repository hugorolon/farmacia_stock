package py.com.prestosoftware.data.dtos;


public class ConfigCompraDTO {
	
	private Long id;
	
	private int pideDeposito;
	
	private int pideDescuento;
	
	private int pideGasto;

	public ConfigCompraDTO() {}
	
	public ConfigCompraDTO(Long id) {
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
