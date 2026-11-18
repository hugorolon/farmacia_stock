package py.com.prestosoftware.data.dtos;

public class UsuarioDTO {
	
	private Long id;
	
	private RolDTO rol;
	
	private EmpresaDTO empresa;
	
	private String usuario;
	private String clave;
	
	private int activo;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(String usuario, String clave, EmpresaDTO empresa, RolDTO rol) {
		this.usuario = usuario;
		this.clave = clave;
		this.empresa = empresa;
		this.rol = rol;
	}
	
	public UsuarioDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}
	
	public EmpresaDTO getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() {
		return usuario;
	}
}
