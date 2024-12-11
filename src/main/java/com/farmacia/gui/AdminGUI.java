package com.farmacia.gui;

import javax.swing.*;
import com.farmacia.model.Usuario;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {
    public AdminGUI(Usuario usuario) {
        setTitle("Panel de Administrador - " + usuario.getUsuario());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(null);

        // Establecer el fondo del panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18, 200, 204));
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 300);
        add(panel);

        JLabel lblBienvenida = new JLabel("Bienvenido, Administrador");
        lblBienvenida.setBounds(20, 20, 300, 25);
        panel.add(lblBienvenida);

        JButton btnGestionUsuarios = new JButton("Gestión de Usuarios");
        btnGestionUsuarios.setBackground(new Color(102, 91, 196));
        btnGestionUsuarios.setBounds(20, 60, 200, 30);
        panel.add(btnGestionUsuarios);

        JButton btnGestionProductos = new JButton("Gestión de Productos");
        btnGestionProductos.setBackground(new Color(102, 91, 196));
        btnGestionProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ProductoInventarioGUI productoInventarioGUI = new ProductoInventarioGUI();
        		productoInventarioGUI.setVisible(true);
        	}
        });
        btnGestionProductos.setBounds(20, 100, 200, 30);
        panel.add(btnGestionProductos);

        JButton btnGestionClientes = new JButton("Gestión de Clientes");
        btnGestionClientes.setBackground(new Color(102, 91, 196));
        btnGestionClientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ClienteGUI clienteGUI = new ClienteGUI();
        		clienteGUI.setVisible(true);
        	}
        });
        btnGestionClientes.setBounds(20, 140, 200, 30);
        panel.add(btnGestionClientes);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(102, 91, 196));
        btnSalir.setBounds(20, 200, 100, 30);
        btnSalir.addActionListener(e -> dispose());
        panel.add(btnSalir);

        // Listeners para botones (ejemplo)
        btnGestionUsuarios.addActionListener(e -> new UsuariosGUI().setVisible(true));
    }
}