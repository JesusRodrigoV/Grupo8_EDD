package com.farmacia.gui;

import com.farmacia.model.Usuario;

public class PantallaGestor {
    private InicioGUI inicioGUI;
    private EmpleadoGUI empleadoGUI;

    public PantallaGestor(Usuario usuario) {
        inicioGUI = new InicioGUI(usuario);
        empleadoGUI = new EmpleadoGUI(usuario);
    }

    public void mostrarPantallaAnterior() {
        empleadoGUI.dispose(); // Cierra la pantalla actual
        inicioGUI.setVisible(true); // Muestra la pantalla anterior
    }
}
