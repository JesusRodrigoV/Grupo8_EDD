package com.farmacia.controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.farmacia.model.*;
import com.farmacia.dao.*;

@ManagedBean
@ViewScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario("", "", null); // Usuario vacío para el formulario
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private ConfRoles confRoles = new ConfRoles();

    public Usuario getUsuario() {
        return usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarioDAO.usuariosLista();
    }

    public void agregarUsuario() {
        if (usuario != null) {
            usuarioDAO.crearNuevoUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado exitosamente."));
            usuario = new Usuario("", "", new Rol("Usuario")); // Reiniciar usuario
        }
    }
}

