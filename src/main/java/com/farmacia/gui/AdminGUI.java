package com.farmacia.gui;

import javax.swing.*;
import com.farmacia.model.Usuario;
import com.farmacia.controller.ProductoController;
import com.farmacia.model.Producto;
import com.farmacia.model.SistemaAlertas;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

public class AdminGUI extends JFrame {
    private SistemaAlertas sistemaAlertas;
    private ProductoController productoController;

    public AdminGUI(Usuario usuario) {
        sistemaAlertas = new SistemaAlertas();
        productoController = new ProductoController();

        setTitle("Panel de Administrador - " + usuario.getUsuario());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Establecer el fondo del panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(18, 200, 204));
        panel.setLayout(new GridBagLayout());
        getContentPane().add(panel);

        // Etiqueta de bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido, Administrador");
        lblBienvenida.setFont(new Font("Times New Roman", Font.BOLD, 20));
        GridBagConstraints gbc_lblBienvenida = new GridBagConstraints();
        gbc_lblBienvenida.insets = new Insets(10, 10, 10, 10);
        gbc_lblBienvenida.gridx = 0;
        gbc_lblBienvenida.gridy = 0;
        gbc_lblBienvenida.gridwidth = 2;
        panel.add(lblBienvenida, gbc_lblBienvenida);

        // Botón de gestión de usuarios
        JButton btnGestionUsuarios = new JButton("Gestión de Usuarios");
        btnGestionUsuarios.setBackground(new Color(102, 91, 196));
        btnGestionUsuarios.setForeground(Color.WHITE);
        GridBagConstraints gbc_btnGestionUsuarios = new GridBagConstraints();
        gbc_btnGestionUsuarios.insets = new Insets(10, 10, 10, 10);
        gbc_btnGestionUsuarios.gridx = 0;
        gbc_btnGestionUsuarios.gridy = 1;
        panel.add(btnGestionUsuarios, gbc_btnGestionUsuarios);

        // Botón de gestión de productos
        JButton btnGestionProductos = new JButton("Gestión de Productos");
        btnGestionProductos.setBackground(new Color(102, 91, 196));
        btnGestionProductos.setForeground(Color.WHITE);
        btnGestionProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductoInventarioGUI productoInventarioGUI = new ProductoInventarioGUI();
                productoInventarioGUI.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnGestionProductos = new GridBagConstraints();
        gbc_btnGestionProductos.insets = new Insets(10, 10, 10, 10);
        gbc_btnGestionProductos.gridx = 1;
        gbc_btnGestionProductos.gridy = 1;
        panel.add(btnGestionProductos, gbc_btnGestionProductos);

        // Botón de gestión de clientes
        JButton btnGestionClientes = new JButton("Gestión de Clientes");
        btnGestionClientes.setBackground(new Color(102, 91, 196));
        btnGestionClientes.setForeground(Color.WHITE);
        btnGestionClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClienteGUI clienteGUI = new ClienteGUI();
                clienteGUI.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnGestionClientes = new GridBagConstraints();
        gbc_btnGestionClientes.insets = new Insets(10, 10, 10, 10);
        gbc_btnGestionClientes.gridx = 0;
        gbc_btnGestionClientes.gridy = 2;
        panel.add(btnGestionClientes, gbc_btnGestionClientes);
        
                // Botón de salir
                JButton btnSalir = new JButton("Salir");
                btnSalir.setBackground(new Color(102, 91, 196));
                btnSalir.setForeground(Color.WHITE);
                btnSalir.addActionListener(e -> dispose());
                
                        // Botón para notificaciones con alertas
                        JButton btnNotificaciones = new JButton("Notificaciones");
                        btnNotificaciones.setBackground(new Color(102, 91, 196));
                        btnNotificaciones.setForeground(Color.WHITE);
                        btnNotificaciones.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                mostrarAlertas();
                            }
                        });
                        GridBagConstraints gbc_btnNotificaciones = new GridBagConstraints();
                        gbc_btnNotificaciones.fill = GridBagConstraints.HORIZONTAL;
                        gbc_btnNotificaciones.insets = new Insets(10, 10, 10, 10);
                        gbc_btnNotificaciones.gridx = 1;
                        gbc_btnNotificaciones.gridy = 2;
                        panel.add(btnNotificaciones, gbc_btnNotificaciones);
                GridBagConstraints gbc_btnSalir = new GridBagConstraints();
                gbc_btnSalir.gridwidth = 2;
                gbc_btnSalir.insets = new Insets(10, 10, 10, 10);
                gbc_btnSalir.gridx = 0;
                gbc_btnSalir.gridy = 3;
                panel.add(btnSalir, gbc_btnSalir);

        // Listeners para botones (ejemplo)
        btnGestionUsuarios.addActionListener(e -> new UsuariosGUI().setVisible(true));
    }

    private void mostrarAlertas() {
        List<Producto> productos = productoController.obtenerProductos(); // Supone que este método está implementado
        procesarProductos(productos);

        StringBuilder alertas = new StringBuilder();
        for (Producto producto : sistemaAlertas.getColaDeAlertas()) {
            alertas.append("Producto: ").append(producto.getNombre())
                   .append(", Stock: ").append(producto.getStock())
                   .append(", Días para vencimiento: ").append(producto.getDiasParaVencimiento())
                   .append("\n");
        }

        if (alertas.length() == 0) {
            alertas.append("No hay alertas en este momento.");
        }

        JOptionPane.showMessageDialog(this, alertas.toString(), "Alertas del Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private void procesarProductos(List<Producto> productos) {
        sistemaAlertas.getColaDeAlertas().clear();
        for (Producto producto : productos) {
            if (producto.getStock() < 10 || producto.getDiasParaVencimiento() <= 30) {
                sistemaAlertas.getColaDeAlertas().add(producto);
            }
        }
    }

    public static void main(String[] args) {
        Usuario usuario = new Usuario("admin", "admin@example.com", null);
        AdminGUI adminGUI = new AdminGUI(usuario);
        adminGUI.setVisible(true);
    }
}
