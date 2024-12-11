package com.farmacia.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.farmacia.controller.ProductoController;
import com.farmacia.model.Producto;
import com.farmacia.model.SistemaAlertas;

public class AlertasGUI extends JFrame {
    private SistemaAlertas sistema;
    private JTextArea alertasArea;
    private ProductoController productoController;

    public AlertasGUI() {
        sistema = new SistemaAlertas();
        productoController = new ProductoController();
        setTitle("Sistema de Alertas de Productos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());
        
        // Configurar color de fondo del panel
        getContentPane().setBackground(new Color(18, 200, 204));
        
        // Título
        JLabel titulo = new JLabel("Alertas del Sistema");
        titulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        getContentPane().add(titulo);
        
        alertasArea = new JTextArea(10, 30);
        alertasArea.setEditable(false);
        getContentPane().add(new JScrollPane(alertasArea));
        
        JButton actualizarAlertasButton = new JButton("Actualizar Alertas");
        actualizarAlertasButton.setBackground(new Color(102, 91, 196)); // Color de fondo del botón
        actualizarAlertasButton.setForeground(Color.WHITE); // Color del texto del botón
        actualizarAlertasButton.addActionListener(e -> actualizarAlertas());
        add(actualizarAlertasButton);

    }

    public void recibirProducto(Producto producto) {
        sistema.agregarAlerta(producto);
        alertasArea.setText(sistema.mostrarAlertas());
    }
    
    public void actualizarAlertas() {
        // Simula obtener la lista de productos desde un controlador o fuente de datos
        List<Producto> productos = productoController.obtenerProductos(); // Debes implementar este método
        sistema.procesarProductos(productos);
        alertasArea.setText(sistema.mostrarAlertas());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlertasGUI ventana = new AlertasGUI();
            ventana.setVisible(true);
        });
    }
}
