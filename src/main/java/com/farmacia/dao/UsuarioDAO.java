package com.farmacia.dao;

import com.farmacia.model.Rol;
import com.farmacia.model.Usuario;
import com.farmacia.model.ClienteModel;
import com.farmacia.model.ConfRoles;
import com.farmacia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private ConfRoles confRoles = new ConfRoles();

    public Usuario buscarUsuario(String username) {
        String query = "SELECT * FROM usuario WHERE nombre = ?";
        Usuario user = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String contrasena = rs.getString("contrasena");
                String rol = rs.getString("rol");

                Rol role = new Rol(rol);
                user = new Usuario(nombre, contrasena, role);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public void crearNuevoUsuario(Usuario usuario) {
        String query = "INSERT INTO usuario (nombre, contrasena, rol) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getContrasenaHash());
            stmt.setString(3, usuario.getRole().getNombre());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Usuario> obtenerTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String contrasena = rs.getString("contrasena");
                String rol = rs.getString("rol");

                Rol role = new Rol(rol);
                Usuario usuario = new Usuario(nombre, contrasena, role);
                usuario.setId(id);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
    public void actualizarUsuario(int id, String nuevoNombre, String nuevoRol) {
        String query = "UPDATE usuario SET nombre = ?, rol = ? WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevoRol);
            stmt.setInt(3, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void eliminarUsuario(int id) {
        String query = "DELETE FROM usuario WHERE id_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Usuario obtenerUsuarioPorId(int idUsuario) {
        String query = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
        		PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            	int id = rs.getInt("id");
                String nombre = rs.getString("usuario");
                String contra = rs.getString("contrasena_hash");
            	String rol = rs.getString("rol");
            	Rol role = new Rol(rol);	
                return new Usuario(nombre, contra, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
