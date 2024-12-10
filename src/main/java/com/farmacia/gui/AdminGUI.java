package com.farmacia.gui;

import javax.swing.*;
import com.farmacia.model.Usuario;

public class AdminGUI extends JFrame {
    public AdminGUI(Usuario usuario) {
        setTitle("Panel de Administrador - " + usuario.getUsuario());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido, Administrador");
        lblBienvenida.setBounds(20, 20, 300, 25);
        add(lblBienvenida);

        JButton btnGestionUsuarios = new JButton("Gestión de Usuarios");
        btnGestionUsuarios.setBounds(20, 60, 200, 30);
        add(btnGestionUsuarios);

        JButton btnGestionProductos = new JButton("Gestión de Productos");
        btnGestionProductos.setBounds(20, 100, 200, 30);
        add(btnGestionProductos);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(20, 200, 100, 30);
        btnSalir.addActionListener(e -> dispose());
        add(btnSalir);

        // Listeners para botones (ejemplo)
        btnGestionUsuarios.addActionListener(e -> new UsuariosGUI().setVisible(true));
    }
}
