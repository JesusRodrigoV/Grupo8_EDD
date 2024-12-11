package com.farmacia.gui;

import java.awt.EventQueue;

import com.farmacia.model.Usuario;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class InicioGUI extends JFrame {
    public InicioGUI(Usuario user) {
        setTitle("Sistema de Gestión de Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(398, 298);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(18, 200, 204));
        panel.setLayout(new GridBagLayout());
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Menú Principal");
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        JButton btnAbrirInventario = new JButton("Abrir Inventario");
        btnAbrirInventario.setBackground(new Color(102, 91, 196));
        btnAbrirInventario.setForeground(Color.WHITE);
        btnAbrirInventario.setFont(new Font("Times New Roman", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(btnAbrirInventario, gbc);

        JButton btnAbrirVentas = new JButton("Abrir Ventas");
        btnAbrirVentas.setBackground(new Color(102, 91, 196));
        btnAbrirVentas.setForeground(Color.WHITE);
        btnAbrirVentas.setFont(new Font("Times New Roman", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(btnAbrirVentas, gbc);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(102, 91, 196));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Times New Roman", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnSalir, gbc);

        btnAbrirInventario.addActionListener((ActionEvent e) -> {
            ProductoInventarioGUI inventarioVentana = new ProductoInventarioGUI();
            inventarioVentana.setVisible(true);
        });

        btnAbrirVentas.addActionListener((ActionEvent e) -> {
            VentaGUI ventaVentana = new VentaGUI(user);
            ventaVentana.setVisible(true);
        });

        btnSalir.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        setContentPane(panel);
    }

    public static void main(String[] args) {
        Usuario user = null; 
        EventQueue.invokeLater(() -> {
            try {
                InicioGUI frame = new InicioGUI(user);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
