package com.farmacia.dao; 

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.farmacia.util.DatabaseConnection;
import com.farmacia.model.Rol;
import com.farmacia.model.Usuario;
import com.farmacia.model.confRoles;

public class UsuarioDAO {
	public Usuario buscarUsuario(String username) {
		String query = "SELECT * FROM usuario WHERE nombre = ?";
		Usuario user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            	String nombre = rs.getString("nombre");
                String contra = rs.getString("contrasena");
                String rol = rs.getString("rol");
                Rol role = new Rol (rol);
                user = new Usuario(nombre, contra, role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
	public boolean iniciarSesion(String usuario, String contrasena) {
    	confRoles algo = new confRoles();
    	Usuario user = buscarUsuario(usuario);
    	System.out.println("\tInicio de Sesion");
    	System.out.println("Usuario:");
    	if(user.verificarContrasena(contrasena)) {
			System.out.println("Contraseña correcta");
			return true;
		} else {
			System.out.println("Contraseña incorrecta");
			return false;
		}
    }
}
