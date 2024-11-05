
public class Usuario {
	    private String usuario;
	    private String contrasena; 
	    private Rol role;

	    public Usuario(String usuario, String contrasena, Rol role) {
	        this.usuario = usuario;
	        this.contrasena = contrasena;
	        this.role = role;
	    }

	    public boolean conPermiso(String permiso) {
	        return role.conPermiso(permiso);
	    }

	    public Rol getRole() {
	        return role;
	    }
	
}
