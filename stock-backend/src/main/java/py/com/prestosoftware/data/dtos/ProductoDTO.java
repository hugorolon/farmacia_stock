package py.com.prestosoftware.data.dtos;

import java.util.Date;


public class ProductoDTO {
	private Long id;
	
	private int esServicio;
	private int esFraccionado;
	private String descripcion;
	private String descripcionFiscal;
	private String referencia;
	private String subreferencia;
	private Double peso;
	private Double cantidadPorCaja;
	private String seccion;
	private String regimen;
	private Double precioCosto;
	private Double costoFob;
	private Double costoCif;
	private Double precioCostoPromedio;
	private Double stock;
	private String esPromo;
	private String imagenUrl;
	private GrupoDTO grupo;
	private SubgrupoDTO subgrupo;
	
	
	private ImpuestoDTO impuesto;
	private MarcaDTO marca;
	
	private TamanhoDTO tamanho;
	private Double precioVentaA;
	private Double precioVentaB;
	private Double precioVentaC;
	private Double precioVentaD;
	private Double precioVentaE;
	private Double depO1;
	private Double depO2;
	private Double depO3;
	private Double depO4;
	private Double depO5;
	
	private Double depO1Bloq;
	private Double depO2Bloq;
	private Double depO3Bloq;
	private Double depO4Bloq;
	private Double depO5Bloq;
	private Double entPendiente;
	private Double salidaPend;
	private Double totalCif;
	private Double totalFob;
	private int activo; 
	private Date fechaPromoInicio;
	
	private Date fechaPromoFin;
	
	private UsuarioDTO userCreate; 
	
	private Date createdDate;
	
	private UsuarioDTO userUpdate; 
	
	private Date updatedDate;
	
	public ProductoDTO() {}
	
	public ProductoDTO(Long id) {
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEsServicio() {
		return esServicio;
	}

	public void setEsServicio(int esServicio) {
		this.esServicio = esServicio;
	}

//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionFiscal() {
		return descripcionFiscal;
	}

	public void setDescripcionFiscal(String descripcionFiscal) {
		this.descripcionFiscal = descripcionFiscal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getSubreferencia() {
		return subreferencia;
	}

	public void setSubreferencia(String subreferencia) {
		this.subreferencia = subreferencia;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getCantidadPorCaja() {
		return cantidadPorCaja;
	}

	public void setCantidadPorCaja(Double cantidadPorCaja) {
		this.cantidadPorCaja = cantidadPorCaja;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getRegimen() {
		return regimen;
	}

	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public String getEsPromo() {
		return esPromo;
	}

	public void setEsPromo(String esPromo) {
		this.esPromo = esPromo;
	}

	public GrupoDTO getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoDTO grupo) {
		this.grupo = grupo;
	}

	
	public ImpuestoDTO getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(ImpuestoDTO impuesto) {
		this.impuesto = impuesto;
	}

	public MarcaDTO getMarca() {
		return marca;
	}

	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}

	

	public TamanhoDTO getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoDTO tamanho) {
		this.tamanho = tamanho;
	}

	

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public String getImagenUrl() {
		return imagenUrl;
	}

	public void setImagenUrl(String imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public SubgrupoDTO getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(SubgrupoDTO subgrupo) {
		this.subgrupo = subgrupo;
	}

	public Double getPrecioCosto() {
		return precioCosto;
	}

	public void setPrecioCosto(Double precioCosto) {
		this.precioCosto = precioCosto;
	}

	public Double getPrecioCostoPromedio() {
		return precioCostoPromedio;
	}

	public void setPrecioCostoPromedio(Double precioCostoPromedio) {
		this.precioCostoPromedio = precioCostoPromedio;
	}
	
	public UsuarioDTO getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(UsuarioDTO userCreate) {
		this.userCreate = userCreate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public UsuarioDTO getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(UsuarioDTO userUpdate) {
		this.userUpdate = userUpdate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public int getEsFraccionado() {
		return esFraccionado;
	}
	
	public void setEsFraccionado(int esFraccionado) {
		this.esFraccionado = esFraccionado;
	}
	
	public Double getDepO1() {
		return depO1;
	}

	public void setDepO1(Double depO1) {
		this.depO1 = depO1;
	}

	public Double getDepO2() {
		return depO2;
	}

	public void setDepO2(Double depO2) {
		this.depO2 = depO2;
	}

	public Double getDepO3() {
		return depO3;
	}

	public void setDepO3(Double depO3) {
		this.depO3 = depO3;
	}

	public Double getDepO4() {
		return depO4;
	}

	public void setDepO4(Double depO4) {
		this.depO4 = depO4;
	}

	public Double getDepO5() {
		return depO5;
	}

	public void setDepO5(Double depO5) {
		this.depO5 = depO5;
	}

	public Double getDepO1Bloq() {
		return depO1Bloq;
	}

	public void setDepO1Bloq(Double depO1Bloq) {
		this.depO1Bloq = depO1Bloq;
	}

	public Double getDepO2Bloq() {
		return depO2Bloq;
	}

	public void setDepO2Bloq(Double depO2Bloq) {
		this.depO2Bloq = depO2Bloq;
	}

	public Double getDepO3Bloq() {
		return depO3Bloq;
	}

	public void setDepO3Bloq(Double depO3Bloq) {
		this.depO3Bloq = depO3Bloq;
	}

	public Double getDepO4Bloq() {
		return depO4Bloq;
	}

	public void setDepO4Bloq(Double depO4Bloq) {
		this.depO4Bloq = depO4Bloq;
	}

	public Double getDepO5Bloq() {
		return depO5Bloq;
	}

	public void setDepO5Bloq(Double depO5Bloq) {
		this.depO5Bloq = depO5Bloq;
	}
	
	public Double getEntPendiente() {
		return entPendiente;
	}
	
	public void setEntPendiente(Double entPendiente) {
		this.entPendiente = entPendiente;
	}
	
	public Double getSalidaPend() {
		return salidaPend;
	}
	
	public void setSalidaPend(Double salidaPend) {
		this.salidaPend = salidaPend;
	}

	public Double getTotalCif() {
		return totalCif;
	}

	public void setTotalCif(Double totalCif) {
		this.totalCif = totalCif;
	}

	public Double getTotalFob() {
		return totalFob;
	}

	public void setTotalFob(Double totalFob) {
		this.totalFob = totalFob;
	}
	
	public Double getCostoCif() {
		return costoCif;
	}
	
	public void setCostoCif(Double costoCif) {
		this.costoCif = costoCif;
	}
	
	public Double getCostoFob() {
		return costoFob;
	}
	
	public void setCostoFob(Double costoFob) {
		this.costoFob = costoFob;
	}

	public Double getPrecioVentaA() {
		return precioVentaA;
	}

	public void setPrecioVentaA(Double precioVentaA) {
		this.precioVentaA = precioVentaA;
	}

	public Double getPrecioVentaB() {
		return precioVentaB;
	}

	public void setPrecioVentaB(Double precioVentaB) {
		this.precioVentaB = precioVentaB;
	}

	public Double getPrecioVentaC() {
		return precioVentaC;
	}

	public void setPrecioVentaC(Double precioVentaC) {
		this.precioVentaC = precioVentaC;
	}

	public Double getPrecioVentaD() {
		return precioVentaD;
	}

	public void setPrecioVentaD(Double precioVentaD) {
		this.precioVentaD = precioVentaD;
	}

	public Double getPrecioVentaE() {
		return precioVentaE;
	}

	public void setPrecioVentaE(Double precioVentaE) {
		this.precioVentaE = precioVentaE;
	}

	public Date getFechaPromoInicio() {
		return fechaPromoInicio;
	}

	public void setFechaPromoInicio(Date fechaPromoInicio) {
		this.fechaPromoInicio = fechaPromoInicio;
	}

	public Date getFechaPromoFin() {
		return fechaPromoFin;
	}

	public void setFechaPromoFin(Date fechaPromoFin) {
		this.fechaPromoFin = fechaPromoFin;
	}

	@Override
	public String toString() {
		return this.descripcion;
	}
	
}
