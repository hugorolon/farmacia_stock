package py.com.prestosoftware.data.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "item_movimientoegresos")
public class MovimientoItemEgreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mie_numero")
	private Integer mieNumero;

	@Column(name = "mie_egreso")
	private Integer mieEgreso;
	
	@Column(name = "mie_monto")
	private Double mieMonto;
	
	@Column(name = "mie_descripcion")
	private String mieDescripcion;
	
	public MovimientoItemEgreso() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMieNumero() {
		return mieNumero;
	}

	public void setMieNumero(Integer mieNumero) {
		this.mieNumero = mieNumero;
	}

	public Integer getMieEgreso() {
		return mieEgreso;
	}

	public void setMieEgreso(Integer mieEgreso) {
		this.mieEgreso = mieEgreso;
	}

	public Double getMieMonto() {
		return mieMonto;
	}

	public void setMieMonto(Double mieMonto) {
		this.mieMonto = mieMonto;
	}

	public String getMieDescripcion() {
		return mieDescripcion;
	}

	public void setMieDescripcion(String mieDescripcion) {
		this.mieDescripcion = mieDescripcion;
	}
}
