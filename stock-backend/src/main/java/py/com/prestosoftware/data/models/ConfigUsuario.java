package py.com.prestosoftware.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "config_usuarios")
public class ConfigUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "es_vendedor")
	private int esVendedor;
	
	public ConfigUsuario() {}
	
	public ConfigUsuario(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getEsVendedor() {
		return esVendedor;
	}
	
	public void setEsVendedor(int esVendedor) {
		this.esVendedor = esVendedor;
	}
	
}
