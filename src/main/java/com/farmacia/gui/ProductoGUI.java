package com.farmacia.gui;

import com.farmacia.controller.*;
import com.farmacia.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTable table;
    private DefaultTableModel tableModel;

    private ProductoBean productoBean = new ProductoBean();

    public ProductoGUI() {
        setTitle("Gestión de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 80, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 10, 200, 25);
        getContentPane().add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 50, 80, 25);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(100, 50, 200, 25);
        getContentPane().add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 90, 80, 25);
        getContentPane().add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(100, 90, 200, 25);
        getContentPane().add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(10, 130, 80, 25);
        getContentPane().add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(100, 130, 200, 25);
        getContentPane().add(txtStock);

        JButton btnRegistrar = new JButton("Registrar Producto");
        btnRegistrar.setBounds(10, 170, 290, 25);
        getContentPane().add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 210, 560, 140);
        getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Precio", "Stock"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });

        actualizarTabla();
    }

    private void registrarProducto() {
        try {
            Producto producto = new Producto();
            producto.setNombre(txtNombre.getText());
            producto.setDescripcion(txtDescripcion.getText());
            producto.setPrecio(Double.parseDouble(txtPrecio.getText()));
            producto.setStock(Integer.parseInt(txtStock.getText()));

            productoBean.setProducto(producto);
            productoBean.registrarProducto();
            actualizarTabla();

            JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el producto: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0); // Limpiar la tabla
        List<Producto> productos = productoBean.obtenerProductos();
        for (Producto producto : productos) {
            tableModel.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ProductoGUI frame = new ProductoGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
