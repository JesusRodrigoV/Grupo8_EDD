package com.farmacia.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import com.farmacia.controller.UsuarioController;
import com.farmacia.model.Usuario;

public class LoginGUI extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    private UsuarioController usuarioController = new UsuarioController();

    public LoginGUI() {
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(null);

        // Configurar color de fondo del panel
        getContentPane().setBackground(new Color(18, 200, 204));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(20, 30, 100, 25);
        add(lblUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(20, 70, 100, 25);
        add(lblContrasena);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 30, 150, 25);
        add(txtUsuario);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(120, 70, 150, 25);
        add(txtContrasena);

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(90, 120, 120, 30);
        btnLogin.setBackground(new Color(102, 91, 196)); // Color de fondo del botón
        btnLogin.setForeground(Color.WHITE); // Color del texto del botón
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        Usuario user = usuarioController.iniciarSesion(usuario, contrasena);
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. Bienvenido, " + user.getUsuario());
            abrirPantallaPorRol(user);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirPantallaPorRol(Usuario user) {
        switch (user.getRole().getNombre().toLowerCase()) {
            case "administrador":
                new AdminGUI(user).setVisible(true);
                break;
            case "farmacéutico":
            case "cajero":
                new InicioGUI(user).setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Rol no reconocido.");
        }
        dispose(); 
    }

    public static void main(String[] args) {
        LoginGUI login = new LoginGUI();
        login.setVisible(true);
    }
}
