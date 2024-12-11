package com.farmacia.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import com.farmacia.controller.UsuarioController;

public class UsuarioGUI extends JFrame {
    private JTextField txtNombreUsuario;
    private JPasswordField txtContrasena;
    private JComboBox<String> cmbRol;
    private UsuarioController usuarioController = new UsuarioController();

    public UsuarioGUI() {
        setTitle("Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        // Configurar color de fondo del panel
        getContentPane().setBackground(new Color(18, 200, 204));

        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        lblNombreUsuario.setBounds(50, 50, 150, 25);
        add(lblNombreUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 50, 150, 25);
        add(txtNombreUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 100, 150, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(200, 100, 150, 25);
        add(txtContrasena);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(50, 150, 150, 25);
        add(lblRol);

        cmbRol = new JComboBox<>(new String[]{"Administrador", "Farmacéutico", "Vendedor"});
        cmbRol.setBounds(200, 150, 150, 25);
        add(cmbRol);

        JButton btnAgregarUsuario = new JButton("Agregar Usuario");
        btnAgregarUsuario.setBounds(120, 200, 150, 30);
        btnAgregarUsuario.setBackground(new Color(102, 91, 196)); // Color de fondo del botón
        btnAgregarUsuario.setForeground(Color.WHITE); // Color del texto del botón
        add(btnAgregarUsuario);

        btnAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
            }
        });
    }

    private void agregarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());
        String rol = (String) cmbRol.getSelectedItem();

        if (usuarioController.agregarUsuario(nombreUsuario, contrasena, rol)) {
            JOptionPane.showMessageDialog(this, "Usuario agregado exitosamente.");
            limpiarFormulario();
            UsuariosGUI user = new UsuariosGUI();
            user.cargarUsuarios();
            dispose();
            user.cargarUsuarios();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar usuario.");
        }
    }

    private void limpiarFormulario() {
        txtNombreUsuario.setText("");
        txtContrasena.setText("");
        cmbRol.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        UsuarioGUI frame = new UsuarioGUI();
        frame.setVisible(true);
    }
}
