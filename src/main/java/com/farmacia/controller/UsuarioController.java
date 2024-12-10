package com.farmacia.controller;

import java.util.List;

import com.farmacia.dao.UsuarioDAO;
import com.farmacia.model.ConfRoles;
import com.farmacia.model.Rol;
import com.farmacia.model.Usuario;

public class UsuarioController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ConfRoles confRoles = new ConfRoles();

    public boolean agregarUsuario(String nombreUsuario, String contrasena, String rolNombre) {
        Rol rol = confRoles.obtenerRol(rolNombre);

        if (rol == null) {
            System.out.println("Error: Rol no válido.");
            return false;
        }

        Usuario nuevoUsuario = new Usuario(nombreUsuario, "", rol);
        nuevoUsuario.setContrasenaHash(nuevoUsuario.hashPassword(contrasena));

        usuarioDAO.crearNuevoUsuario(nuevoUsuario);
        return true;
    }
    public List<Usuario> obtenerUsuarios() {
        return usuarioDAO.obtenerTodosUsuarios();
    }

    public void editarUsuario(int id, String nuevoNombre, String nuevoRol) {
        usuarioDAO.actualizarUsuario(id, nuevoNombre, nuevoRol);
    }

    public void eliminarUsuario(int id) {
        usuarioDAO.eliminarUsuario(id);
    }
    
    public Usuario iniciarSesion(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioDAO.buscarUsuario(nombreUsuario);
        if (usuario != null && usuario.verificarContrasena(contrasena)) {
            return usuario;
        }
        return null;
    }

}
