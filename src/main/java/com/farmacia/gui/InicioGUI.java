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
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Menú Principal");
        lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTitulo.setBounds(113, 25, 171, 40);
        panel.add(lblTitulo);

        JButton btnAbrirInventario = new JButton("Abrir Inventario");
        btnAbrirInventario.setBackground(new Color(102, 91, 196));
        btnAbrirInventario.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnAbrirInventario.setBounds(113, 95, 157, 30);
        panel.add(btnAbrirInventario);

        JButton btnAbrirVentas = new JButton("Abrir Ventas");
        btnAbrirVentas.setBackground(new Color(102, 91, 196));
        btnAbrirVentas.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnAbrirVentas.setBounds(113, 125, 157, 30);
        panel.add(btnAbrirVentas);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBackground(new Color(102, 91, 196));
        btnSalir.setFont(new Font("Times New Roman", Font.BOLD, 16));
        btnSalir.setBounds(113, 185, 157, 30);
        panel.add(btnSalir);

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
