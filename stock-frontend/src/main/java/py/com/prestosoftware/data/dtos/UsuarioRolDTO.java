package py.com.prestosoftware.data.dtos;

public class UsuarioRolDTO {
	
	private Long id;
	
	private RolDTO rol;
	
	private UsuarioDTO usuario;
	
	
	public UsuarioRolDTO() {}
	
	public UsuarioRolDTO(UsuarioDTO usuario, RolDTO rol) {
		this.usuario= usuario;
		this.rol = rol;
	}
	
	public UsuarioRolDTO(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolDTO getRol() {
		return rol;
	}

	public void setRol(RolDTO rol) {
		this.rol = rol;
	}
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario= usuario;
	}

}
