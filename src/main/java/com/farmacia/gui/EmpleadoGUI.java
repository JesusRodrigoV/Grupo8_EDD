package com.farmacia.gui;

import javax.swing.*;
import com.farmacia.model.Usuario;

public class EmpleadoGUI extends JFrame {
    public EmpleadoGUI(Usuario usuario) {
        setTitle("Panel de Empleado - " + usuario.getUsuario());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido, Empleado");
        lblBienvenida.setBounds(20, 20, 300, 25);
        add(lblBienvenida);

        JButton btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setBounds(20, 60, 200, 30);
        add(btnRegistrarVenta);

        JButton btnConsultarStock = new JButton("Consultar Stock");
        btnConsultarStock.setBounds(20, 100, 200, 30);
        add(btnConsultarStock);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(20, 200, 100, 30);
        btnSalir.addActionListener(e -> dispose());
        add(btnSalir);

        // Listeners para botones (ejemplo)
        btnRegistrarVenta.addActionListener(e -> JOptionPane.showMessageDialog(this, "Función de Registrar Venta"));
    }
}
