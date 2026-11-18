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
@Table(name = "item_movimientoingresos")
public class MovimientoItemIngreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "mii_numero")
	private Integer miiNumero;

	@Column(name = "mii_ingreso")
	private Integer miiIngreso;
	
	@Column(name = "mii_monto")
	private Double miiMonto;
	
	@Column(name = "mii_descripcion")
	private String miiDescripcion;
	
	public MovimientoItemIngreso() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Integer getMiiNumero() {
		return miiNumero;
	}

	public void setMiiNumero(Integer miiNumero) {
		this.miiNumero = miiNumero;
	}

	public Integer getMiiIngreso() {
		return miiIngreso;
	}

	public void setMiiIngreso(Integer miiIngreso) {
		this.miiIngreso = miiIngreso;
	}

	public Double getMiiMonto() {
		return miiMonto;
	}

	public void setMiiMonto(Double miiMonto) {
		this.miiMonto = miiMonto;
	}

	public String getMiiDescripcion() {
		return miiDescripcion;
	}

	public void setMiiDescripcion(String miiDescripcion) {
		this.miiDescripcion = miiDescripcion;
	}
	
}
