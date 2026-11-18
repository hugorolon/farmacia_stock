package py.com.prestosoftware.data.dtos;



public class ConfiguracionDTO {
	
	private Long id;
	
	private EmpresaDTO empresaId;
	
	private UsuarioDTO usuarioId;
	
	//opciones basica
	private int calculoPrecioVenta;
	
	private int registroProductoIVA;
	
	private int tipoComisionVenta;
	
	private int pideDispNotaPresu;
	
	private int pideDispNotaTrans;
	
	private int pideDispAjusteEntrada;
	
	private int pideDispNotaAjusteSalida;
	
	private int pideDispNotaDevolCompra;
	
	private int pideDispNotaDevolVenta;
	
	private int pideDispNotaAjusteCuenta;
	
	//opciones de venta
	private int pideVendedor;
	
	private int pideDeposito;
	
	private int pideDescuento;
	
	private int pideFlete;
	
	private int imprimeMoneda;
	
	private int imprimeVendedor;
	
	private int imprimePorOrdenAlfabetico;
	
	private int permiteVentaPorReferencia;
	
	private int permiteItemDuplicado;
	
	private int pideDispositivoVenta;
	
	private int bloqueaProductoNegativo;
	
	private int permitePrecioPorCliente;
	
	//opciones de compra
	private int pideMoneda;
	
	private int pideDepositoCompra;
	
	private int pideGastoItemCompra;
	
	private int pideFleteCompraLocal;
	
	private int imprimeCompraResumida;
	
	private int permiteActualizacionAutoCompra;
	private int pideDispCompra;
	
	private int imprimeNotaResCompra;
	
	// Opciones de PDV
	private int cajaIdPDV;
	
	private int depositoIdPDV;
	
	private Long usuarioIdPDV;
	
	// opciones en Usuario
	private int ventaDirectoContado;
	
	private int mensajeFinalizaVenta;
	
	private int notaRecibidaF3;
	
	private int monitorCajaF4;
	
	private int codigoReferencia;
	
	private int alteraPrecioVenta;
	
	private String precioDefinido;
	
	//opciones de usuario
	private Long defineDepositoVenta;
	
	private Long defineDepositoCompra;
	
	private Long defineAperCajaCodigo;
	
	private Long OperacionBloqCaja;
	
	private Long defineCajaTransferencia;
	
	private Long visualizaDepositoUsuario;
	
	private int habilitaDescUsuario;
	
	private int tipoDescUsuario;
	
	private Double limiteDescNotaValor;
	
	private int AutorizaVentaPrecioD;
	
	private int AutorizaVentaPrecioE;
	
	private int AutorizaVentaHastaCosto;
	
	private int AutorizaVentaBajoCosto;
	
	private int AutorizaPlazoPago;
	private int AutorizaLimiteCredito;
	
	private int puedeDescNota;
	
	//usuario stock
	private int puedeVerTodoPventa;
	
	private int puedeVerPrecioA;
	
	private int puedeVerPrecioB;
	
	private int puedeVerPrecioC;
	
	private int puedeVerPrecioD;
	
	private int puedeVerPrecioE;
	
	private int puedeVerCostoFob;
	
	private int puedeVerCostoCif;
	
	private int habilitaLanzamientoCaja;
	
	public ConfiguracionDTO() {}
	
	public ConfiguracionDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPideVendedor() {
		return pideVendedor;
	}

	public void setPideVendedor(int pideVendedor) {
		this.pideVendedor = pideVendedor;
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

	public int getPideFlete() {
		return pideFlete;
	}

	public void setPideFlete(int pideFlete) {
		this.pideFlete = pideFlete;
	}

	public int getImprimeMoneda() {
		return imprimeMoneda;
	}

	public void setImprimeMoneda(int imprimeMoneda) {
		this.imprimeMoneda = imprimeMoneda;
	}

	public int getImprimeVendedor() {
		return imprimeVendedor;
	}

	public void setImprimeVendedor(int imprimeVendedor) {
		this.imprimeVendedor = imprimeVendedor;
	}

	public int getImprimePorOrdenAlfabetico() {
		return imprimePorOrdenAlfabetico;
	}

	public void setImprimePorOrdenAlfabetico(int imprimePorOrdenAlfabetico) {
		this.imprimePorOrdenAlfabetico = imprimePorOrdenAlfabetico;
	}

	public int getPermiteVentaPorReferencia() {
		return permiteVentaPorReferencia;
	}

	public void setPermiteVentaPorReferencia(int permiteVentaPorReferencia) {
		this.permiteVentaPorReferencia = permiteVentaPorReferencia;
	}

	public int getPermiteItemDuplicado() {
		return permiteItemDuplicado;
	}

	public void setPermiteItemDuplicado(int permiteItemDuplicado) {
		this.permiteItemDuplicado = permiteItemDuplicado;
	}

	public Long getUsuarioIdPDV() {
		return usuarioIdPDV;
	}
	
	public void setUsuarioIdPDV(Long usuarioId) {
		this.usuarioIdPDV = usuarioId;
	}

	public EmpresaDTO getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(EmpresaDTO empresaId) {
		this.empresaId = empresaId;
	}

	public int getPideDispositivoVenta() {
		return pideDispositivoVenta;
	}

	public void setPideDispositivoVenta(int pideDispositivoVenta) {
		this.pideDispositivoVenta = pideDispositivoVenta;
	}

	public int getBloqueaProductoNegativo() {
		return bloqueaProductoNegativo;
	}

	public void setBloqueaProductoNegativo(int bloqueaProductoNegativo) {
		this.bloqueaProductoNegativo = bloqueaProductoNegativo;
	}

	public int getPermitePrecioPorCliente() {
		return permitePrecioPorCliente;
	}

	public void setPermitePrecioPorCliente(int permitePrecioPorCliente) {
		this.permitePrecioPorCliente = permitePrecioPorCliente;
	}

	public UsuarioDTO getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(UsuarioDTO usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getPideMoneda() {
		return pideMoneda;
	}

	public void setPideMoneda(int pideMoneda) {
		this.pideMoneda = pideMoneda;
	}

	public int getPideDepositoCompra() {
		return pideDepositoCompra;
	}

	public void setPideDepositoCompra(int pideDepositoCompra) {
		this.pideDepositoCompra = pideDepositoCompra;
	}

	public int getPideGastoItemCompra() {
		return pideGastoItemCompra;
	}

	public void setPideGastoItemCompra(int pideGastoItemCompra) {
		this.pideGastoItemCompra = pideGastoItemCompra;
	}

	public int getPideFleteCompraLocal() {
		return pideFleteCompraLocal;
	}

	public void setPideFleteCompraLocal(int pideFleteCompraLocal) {
		this.pideFleteCompraLocal = pideFleteCompraLocal;
	}

	public int getImprimeCompraResumida() {
		return imprimeCompraResumida;
	}

	public void setImprimeCompraResumida(int imprimeCompraResumida) {
		this.imprimeCompraResumida = imprimeCompraResumida;
	}

	public int getPermiteActualizacionAutoCompra() {
		return permiteActualizacionAutoCompra;
	}

	public void setPermiteActualizacionAutoCompra(int permiteActualizacionAutoCompra) {
		this.permiteActualizacionAutoCompra = permiteActualizacionAutoCompra;
	}

	public int getPideDispCompra() {
		return pideDispCompra;
	}

	public void setPideDispCompra(int pideDispCompra) {
		this.pideDispCompra = pideDispCompra;
	}

	public int getImprimeNotaResCompra() {
		return imprimeNotaResCompra;
	}

	public void setImprimeNotaResCompra(int imprimeNotaResCompra) {
		this.imprimeNotaResCompra = imprimeNotaResCompra;
	}

	public int getCalculoPrecioVenta() {
		return calculoPrecioVenta;
	}

	public void setCalculoPrecioVenta(int calculoPrecioVenta) {
		this.calculoPrecioVenta = calculoPrecioVenta;
	}

	public int getRegistroProductoIVA() {
		return registroProductoIVA;
	}

	public void setRegistroProductoIVA(int registroProductoIVA) {
		this.registroProductoIVA = registroProductoIVA;
	}

	public int getTipoComisionVenta() {
		return tipoComisionVenta;
	}

	public void setTipoComisionVenta(int tipoComisionVenta) {
		this.tipoComisionVenta = tipoComisionVenta;
	}

	public int getPideDispNotaPresu() {
		return pideDispNotaPresu;
	}

	public void setPideDispNotaPresu(int pideDispNotaPresu) {
		this.pideDispNotaPresu = pideDispNotaPresu;
	}

	public int getPideDispNotaTrans() {
		return pideDispNotaTrans;
	}

	public void setPideDispNotaTrans(int pideDispNotaTrans) {
		this.pideDispNotaTrans = pideDispNotaTrans;
	}

	public int getPideDispAjusteEntrada() {
		return pideDispAjusteEntrada;
	}

	public void setPideDispAjusteEntrada(int pideDispAjusteEntrada) {
		this.pideDispAjusteEntrada = pideDispAjusteEntrada;
	}

	public int getPideDispNotaAjusteSalida() {
		return pideDispNotaAjusteSalida;
	}

	public void setPideDispNotaAjusteSalida(int pideDispNotaAjusteSalida) {
		this.pideDispNotaAjusteSalida = pideDispNotaAjusteSalida;
	}

	public int getPideDispNotaDevolCompra() {
		return pideDispNotaDevolCompra;
	}

	public void setPideDispNotaDevolCompra(int pideDispNotaDevolCompra) {
		this.pideDispNotaDevolCompra = pideDispNotaDevolCompra;
	}

	public int getPideDispNotaDevolVenta() {
		return pideDispNotaDevolVenta;
	}

	public void setPideDispNotaDevolVenta(int pideDispNotaDevolVenta) {
		this.pideDispNotaDevolVenta = pideDispNotaDevolVenta;
	}

	public int getPideDispNotaAjusteCuenta() {
		return pideDispNotaAjusteCuenta;
	}

	public void setPideDispNotaAjusteCuenta(int pideDispNotaAjusteCuenta) {
		this.pideDispNotaAjusteCuenta = pideDispNotaAjusteCuenta;
	}

	public Long getDefineDepositoVenta() {
		return defineDepositoVenta;
	}

	public void setDefineDepositoVenta(Long defineDepositoVenta) {
		this.defineDepositoVenta = defineDepositoVenta;
	}

	public Long getDefineDepositoCompra() {
		return defineDepositoCompra;
	}

	public void setDefineDepositoCompra(Long defineDepositoCompra) {
		this.defineDepositoCompra = defineDepositoCompra;
	}

	public Long getDefineAperCajaCodigo() {
		return defineAperCajaCodigo;
	}

	public void setDefineAperCajaCodigo(Long defineAperCajaCodigo) {
		this.defineAperCajaCodigo = defineAperCajaCodigo;
	}

	public Long getOperacionBloqCaja() {
		return OperacionBloqCaja;
	}
	
	public void setOperacionBloqCaja(Long operacionBloqCaja) {
		OperacionBloqCaja = operacionBloqCaja;
	}

	public Long getDefineCajaTransferencia() {
		return defineCajaTransferencia;
	}

	public void setDefineCajaTransferencia(Long defineCajaTransferencia) {
		this.defineCajaTransferencia = defineCajaTransferencia;
	}

	public int getAutorizaVentaPrecioD() {
		return AutorizaVentaPrecioD;
	}

	public void setAutorizaVentaPrecioD(int autorizaVentaPrecioD) {
		AutorizaVentaPrecioD = autorizaVentaPrecioD;
	}

	public int getAutorizaVentaPrecioE() {
		return AutorizaVentaPrecioE;
	}

	public void setAutorizaVentaPrecioE(int autorizaVentaPrecioE) {
		AutorizaVentaPrecioE = autorizaVentaPrecioE;
	}

	public int getAutorizaVentaHastaCosto() {
		return AutorizaVentaHastaCosto;
	}

	public void setAutorizaVentaHastaCosto(int autorizaVentaHastaCosto) {
		AutorizaVentaHastaCosto = autorizaVentaHastaCosto;
	}

	public int getAutorizaVentaBajoCosto() {
		return AutorizaVentaBajoCosto;
	}

	public void setAutorizaVentaBajoCosto(int autorizaVentaBajoCosto) {
		AutorizaVentaBajoCosto = autorizaVentaBajoCosto;
	}

	public int getAutorizaPlazoPago() {
		return AutorizaPlazoPago;
	}

	public void setAutorizaPlazoPago(int autorizaPlazoPago) {
		AutorizaPlazoPago = autorizaPlazoPago;
	}

	public int getAutorizaLimiteCredito() {
		return AutorizaLimiteCredito;
	}

	public void setAutorizaLimiteCredito(int autorizaLimiteCredito) {
		AutorizaLimiteCredito = autorizaLimiteCredito;
	}

	public int getPuedeDescNota() {
		return puedeDescNota;
	}

	public void setPuedeDescNota(int puedeDescNota) {
		this.puedeDescNota = puedeDescNota;
	}

	public int getPuedeVerTodoPventa() {
		return puedeVerTodoPventa;
	}

	public void setPuedeVerTodoPventa(int puedeVerTodoPventa) {
		this.puedeVerTodoPventa = puedeVerTodoPventa;
	}

	public int getPuedeVerPrecioA() {
		return puedeVerPrecioA;
	}

	public void setPuedeVerPrecioA(int puedeVerPrecioA) {
		this.puedeVerPrecioA = puedeVerPrecioA;
	}

	public int getPuedeVerPrecioB() {
		return puedeVerPrecioB;
	}

	public void setPuedeVerPrecioB(int puedeVerPrecioB) {
		this.puedeVerPrecioB = puedeVerPrecioB;
	}

	public int getPuedeVerPrecioC() {
		return puedeVerPrecioC;
	}

	public void setPuedeVerPrecioC(int puedeVerPrecioC) {
		this.puedeVerPrecioC = puedeVerPrecioC;
	}

	public int getPuedeVerPrecioD() {
		return puedeVerPrecioD;
	}

	public void setPuedeVerPrecioD(int puedeVerPrecioD) {
		this.puedeVerPrecioD = puedeVerPrecioD;
	}

	public int getPuedeVerPrecioE() {
		return puedeVerPrecioE;
	}

	public void setPuedeVerPrecioE(int puedeVerPrecioE) {
		this.puedeVerPrecioE = puedeVerPrecioE;
	}

	
	public String getPrecioDefinido() {
		return precioDefinido;
	}

	public void setPrecioDefinido(String precioDefinido) {
		this.precioDefinido = precioDefinido;
	}

	public int getPuedeVerCostoFob() {
		return puedeVerCostoFob;
	}

	public void setPuedeVerCostoFob(int puedeVerCostoFob) {
		this.puedeVerCostoFob = puedeVerCostoFob;
	}

	public int getPuedeVerCostoCif() {
		return puedeVerCostoCif;
	}

	public void setPuedeVerCostoCif(int puedeVerCostoCif) {
		this.puedeVerCostoCif = puedeVerCostoCif;
	}

	public int getVentaDirectoContado() {
		return ventaDirectoContado;
	}

	public void setVentaDirectoContado(int ventaDirectoContado) {
		this.ventaDirectoContado = ventaDirectoContado;
	}

	public int getMensajeFinalizaVenta() {
		return mensajeFinalizaVenta;
	}

	public void setMensajeFinalizaVenta(int mensajeFinalizaVenta) {
		this.mensajeFinalizaVenta = mensajeFinalizaVenta;
	}

	public int getNotaRecibidaF3() {
		return notaRecibidaF3;
	}

	public void setNotaRecibidaF3(int notaRecibidaF3) {
		this.notaRecibidaF3 = notaRecibidaF3;
	}

	public int getMonitorCajaF4() {
		return monitorCajaF4;
	}

	public void setMonitorCajaF4(int monitorCajaF4) {
		this.monitorCajaF4 = monitorCajaF4;
	}

	public int getCodigoReferencia() {
		return codigoReferencia;
	}

	public void setCodigoReferencia(int codigoReferencia) {
		this.codigoReferencia = codigoReferencia;
	}

	public int getAlteraPrecioVenta() {
		return alteraPrecioVenta;
	}

	public void setAlteraPrecioVenta(int alteraPrecioVenta) {
		this.alteraPrecioVenta = alteraPrecioVenta;
	}

	public int getHabilitaDescUsuario() {
		return habilitaDescUsuario;
	}

	public void setHabilitaDescUsuario(int habilitaDescUsuario) {
		this.habilitaDescUsuario = habilitaDescUsuario;
	}

	public int getTipoDescUsuario() {
		return tipoDescUsuario;
	}

	public void setTipoDescUsuario(int tipoDescUsuario) {
		this.tipoDescUsuario = tipoDescUsuario;
	}

	public Double getLimiteDescNotaValor() {
		return limiteDescNotaValor;
	}

	public void setLimiteDescNotaValor(Double limiteDescNotaValor) {
		this.limiteDescNotaValor = limiteDescNotaValor;
	}

	public Long getVisualizaDepositoUsuario() {
		return visualizaDepositoUsuario;
	}

	public void setVisualizaDepositoUsuario(Long visualizaDepositoUsuario) {
		this.visualizaDepositoUsuario = visualizaDepositoUsuario;
	}

	public int getCajaIdPDV() {
		return cajaIdPDV;
	}

	public void setCajaIdPDV(int cajaIdPDV) {
		this.cajaIdPDV = cajaIdPDV;
	}

	public int getDepositoIdPDV() {
		return depositoIdPDV;
	}

	public void setDepositoIdPDV(int depositoIdPDV) {
		this.depositoIdPDV = depositoIdPDV;
	}

	public int getHabilitaLanzamientoCaja() {
		return habilitaLanzamientoCaja;
	}

	public void setHabilitaLanzamientoCaja(int habilitaLanzamientoCaja) {
		this.habilitaLanzamientoCaja = habilitaLanzamientoCaja;
	}
	
	

}
