package py.com.prestosoftware.data.dtos;

import java.util.Date;


public class CotizacionDTO {
	
	private Long id;
	
	private Date fecha;
	
	private MonedaDTO moneda;
	
	private Double valorCompra;
	
	private Double valorVenta;
	
	private int activo;
	
	public CotizacionDTO() {}
	
	public CotizacionDTO(Date fecha, MonedaDTO moneda, Double compra, Double venta) {
		this.fecha = fecha;
		this.moneda = moneda;
		this.valorCompra = compra;
		this.valorVenta = venta;
	}

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

	public MonedaDTO getMoneda() {
		return moneda;
	}

	public void setMoneda(MonedaDTO moneda) {
		this.moneda = moneda;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(Double valorVenta) {
		this.valorVenta = valorVenta;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}
