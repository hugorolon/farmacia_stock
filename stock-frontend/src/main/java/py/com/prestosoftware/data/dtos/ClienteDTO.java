package py.com.prestosoftware.data.dtos;

import java.util.Date;



public class ClienteDTO {
	
	private Long id;
	private String tipo;
	private String nombre;
	private String razonSocial;
	private String ciruc;
	private String dvruc;
	private String direccion;
	private String email;
	private String telefono;
	private String celular;
	private String fax;
	private String skype;
	private String web;
	private String contacto;
	private String obs;
	private String clase;
	private int plazo;
	private CiudadDTO ciudad;
	private DepartamentoDTO departamento;
	private PaisDTO pais;
	private ListaPrecioDTO listaPrecio;
	private EmpresaDTO empresa;
	private int activo;
	private Date fechaRegistro;
	private Double totalDebito;
	private Double totalCredito;
	private Integer tipoEntidad;
	private String celular2;
	private int tieneCredito;
	private Double limiteCredito;
	private Integer diaCredito;
	private Double creditoSaldo;
	private Double creditoDisponible;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Long id) {
		this.id = id;
	}
	
	public ClienteDTO(String nombre, String razonSocial, String ruc, String dvruc, String direccion, PaisDTO pais, 
			DepartamentoDTO dep, CiudadDTO ciudad, EmpresaDTO empresa, Date fecha, String tipo, int activo, ListaPrecioDTO lista) {
		this.nombre = nombre;
		this.razonSocial = razonSocial;
		this.ciruc = ruc;
		this.dvruc = dvruc;
		this.direccion = direccion;
		this.pais = pais;
		this.departamento = dep;
		this.ciudad = ciudad;
		this.empresa = empresa;
		this.fechaRegistro = fecha;
		this.tipo = tipo;
		this.activo = activo;
		this.listaPrecio = lista;
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCiruc() {
		return ciruc;
	}

	public void setCiruc(String ciruc) {
		this.ciruc = ciruc;
	}

	public String getDvruc() {
		return dvruc;
	}

	public void setDvruc(String dvruc) {
		this.dvruc = dvruc;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public CiudadDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}

	public DepartamentoDTO getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoDTO departamento) {
		this.departamento = departamento;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}

	public ListaPrecioDTO getListaPrecio() {
		return listaPrecio;
	}

	public void setListaPrecio(ListaPrecioDTO listaPrecio) {
		this.listaPrecio = listaPrecio;
	}

	public int getTieneCredito() {
		return tieneCredito;
	}

	public void setTieneCredito(int tieneCredito) {
		this.tieneCredito = tieneCredito;
	}

	public Double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(Double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public Integer getDiaCredito() {
		return diaCredito;
	}

	public void setDiaCredito(Integer diaCredito) {
		this.diaCredito = diaCredito;
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

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
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
	
	public String getCelular2() {
		return celular2;
	}

	public void setCelular2(String celular2) {
		this.celular2 = celular2;
	}

	public Integer getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(Integer tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	@Override
	public String toString() {
		return this.razonSocial;
	}
	
}
