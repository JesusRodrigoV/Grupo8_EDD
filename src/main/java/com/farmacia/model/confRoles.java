package com.farmacia.model;

import java.security.Permission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

import com.farmacia.util.*;

public class confRoles {
	public static Rol configureRole(String nombreRol) {
        Rol role = new Rol(nombreRol);

        switch (nombreRol) {
            case "Cajero":
                role.agregarPermiso("VIEW_SALES");
                role.agregarPermiso("VIEW_INVENTORY");
                break;
            case "Farmacéutico":
                role.agregarPermiso("VIEW_INVENTORY");
                role.agregarPermiso("EDIT_PRESCRIPTIONS");
                role.agregarPermiso("VIEW_PRESCRIPTIONS");
                break;
            case "Asistente":
                role.agregarPermiso("VIEW_INVENTORY");
                role.agregarPermiso("EDIT_INVENTORY");
                break;
        }

        return role;
    }
	
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
	public boolean verifNombre(String nuevoUsuario, String nuevaContrasena, String rol) {
		String query = "SELECT COUNT(*) FROM usuario WHERE nombre = ?";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
		    stmt.setString(1, nuevoUsuario);
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next()) {
		        int count = rs.getInt(1);
		        if (count > 0) {
		            System.out.println("El usuario ya existe en la base de datos.");
		            return false;
		        } else {
		            //crearNuevoUsuario(nuevoUsuario, nuevaContrasena, rol);
		            return true;
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("Hubo un error al verificar la existencia del usuario.");
		}
		return false;
	}
	public boolean existeUsuario(String nombre) {
		String query = "SELECT COUNT(*) FROM usuario WHERE nombre = ?";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
		    stmt.setString(1, nombre);
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next()) {
		        int count = rs.getInt(1);
		        return count > 0 ? true : false;
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("Hubo un error al verificar la existencia del usuario.");
		}
		return false;
	}
	
	

	
	public Usuario iniciarSesion(String nombre, String contra) {
		Usuario user = buscarUsuario(nombre);
		if(user.verificarContrasena(contra)) {
			System.out.println("Contraseña correcta");
			return user;
		} else {
			System.out.println("Contraseña incorrecta");
			return null;
		}
		
	}
	public void verUsuarios() {
		String query = "SELECT * FROM usuario";
		try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery()) { 
			System.out.printf("%-20s %-20s %-20s\n", "Nombre", "Contraseña", "Rol"); 
			System.out.println("----------------------------------------"); 
			while (rs.next()) { 
				String nombre = rs.getString("nombre");
				String contra = rs.getString("contrasena");
				String rol = rs.getString("rol"); 
				System.out.printf("%-20s %-20s %-20s\n", nombre, contra, rol); 
			}
		} catch (SQLException e) {
		    e.printStackTrace();
		    System.out.println("Hubo un error al verificar la existencia del usuario.");
		}
	}
    /* Obtiene los roles de un usuario por su ID
    private List<Rol> getRolesByUserId(int userId) {
        String query = "SELECT r.role_id, r.role_name FROM roles r " +
                       "JOIN user_roles ur ON r.role_id = ur.role_id WHERE ur.user_id = ?";
        List<Rol> roles = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Rol role = new Rol(rs.getInt("role_id"), rs.getString("role_name"));
                role.setPermissions(getPermissionsByRoleId(role.getRoleId()));
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    // Obtiene los permisos de un rol
    private List<Permission> getPermissionsByRoleId(int roleId) {
        String query = "SELECT p.permission_id, p.permission_name FROM permissions p " +
                       "JOIN role_permissions rp ON p.permission_id = rp.permission_id WHERE rp.role_id = ?";
        List<Permission> permissions = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, roleId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Permission permission = new Permission(rs.getInt("permission_id"), rs.getString("permission_name"));
                permissions.add(permission);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return permissions;
    }
    */
}
