package py.com.prestosoftware.data.dtos;

import java.util.Date;

public class ProveedorDTO {
	
	private Long id;
	
	private String tipo;
	private String nombre;
	
	private String razonSocial;
	
	private CiudadDTO ciudad;
	
	private DepartamentoDTO departmento;
	
	private PaisDTO pais;
	
	private EmpresaDTO empresa;
	
	private String ruc;
	private String dvruc;
	private String direccion;
	private String celular;
	private String telefono;
	private String email;
	private String fax;
	private String web;
	private String skype;
	private String contacto;
	private String clase;
	
	private int plazo;
	private int activo;
	
	private Double creditoSaldo;
	
	private Double creditoDisponible;
	
	private Integer diaCredito;
	
	private Date fechaRegistro;
	
	private Double totalDebito;
	
	private Double totalCredito;
	
	public ProveedorDTO() {}
	
	public ProveedorDTO(Long id) {
		this.id = id;
	}
	
	public ProveedorDTO(String nombre, String razonSocial, String tipo, String ruc, String dvruc,
			String direccion, String clase, PaisDTO pais, DepartamentoDTO dep, CiudadDTO ciudad, 
			EmpresaDTO empresa, String celular, String email) {
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.tipo = tipo;
		this.ruc = ruc;
		this.dvruc = dvruc;
		this.direccion = direccion;
		this.clase = clase;
		this.pais = pais;
		this.departmento = dep;
		this.ciudad = ciudad;
		this.empresa = empresa;
		this.celular = celular;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getDvruc() {
		return dvruc;
	}

	public void setDvruc(String dvruc) {
		this.dvruc = dvruc;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public CiudadDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}

	public DepartamentoDTO getDepartmento() {
		return departmento;
	}

	public void setDepartmento(DepartamentoDTO departmento) {
		this.departmento = departmento;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public Double getCreditoSaldo() {
		return creditoSaldo;
	}

	public void setCreditoSaldo(Double creditoSaldo) {
		this.creditoSaldo = creditoSaldo;
	}

	public Double getCreditoDisponible() {
		return creditoDisponible;
	}

	public void setCreditoDisponible(Double creditoDisponible) {
		this.creditoDisponible = creditoDisponible;
	}

	public Integer getDiaCredito() {
		return diaCredito;
	}

	public void setDiaCredito(Integer diaCredito) {
		this.diaCredito = diaCredito;
	}

	public Double getTotalDebito() {
		return totalDebito;
	}

	public void setTotalDebito(Double totalDebito) {
		this.totalDebito = totalDebito;
	}

	public Double getTotalCredito() {
		return totalCredito;
	}

	public void setTotalCredito(Double totalCredito) {
		this.totalCredito = totalCredito;
	}

	@Override
	public String toString() {
		return this.razonSocial;
	}
	
}
