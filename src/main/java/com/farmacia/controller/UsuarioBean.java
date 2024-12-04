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
	private Usuario usuario = null;
	private UsuarioDAO  us = new UsuarioDAO();
	
	public Usuario getUs() {
		return usuario;
	}
	public List<Usuario> getUsuarios(){
		return us.usuariosLista();
	}
	public void agregarUsuario() {
		us.crearNuevoUsuario(usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado exitosamente."));
		usuario = null;
	}
}
