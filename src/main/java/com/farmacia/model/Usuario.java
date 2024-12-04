package com.farmacia.model;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {
	private int id;
    private String usuario;
    private String contrasenaHash; 
    private Rol role;

    // Constructor
    public Usuario(String usuario, String contrasenaHash, Rol role) {
        this.usuario = usuario;
        this.contrasenaHash = contrasenaHash; 
        this.role = role;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getContrasenaHash() {
		return contrasenaHash;
	}

	public void setContrasenaHash(String contrasenaHash) {
		this.contrasenaHash = contrasenaHash;
	}

	public String hashPassword(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    public boolean verificarContrasena(String contrasena) {
        return BCrypt.checkpw(contrasena, this.contrasenaHash);
    }

    public boolean conPermiso(String permiso) {
        return role.conPermiso(permiso);
    }

    public Rol getRole() {
        return role;
    }

    public String getUsuario() {
        return usuario;
    }
}
