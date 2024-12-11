package com.farmacia.gui;

import com.farmacia.controller.ProductoController;
import com.farmacia.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.sql.Date; 
import java.util.List;

public class ProductoGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtFechaVencimiento;
    private JTextField txtNumeroLote;
    private JTable table;
    private DefaultTableModel tableModel;

    private ProductoController productoController;

    public ProductoGUI() {
    	productoController = new ProductoController();
        setTitle("Gestión de Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 100, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 10, 200, 25);
        getContentPane().add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 50, 100, 25);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 50, 200, 25);
        getContentPane().add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 90, 100, 25);
        getContentPane().add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 90, 200, 25);
        getContentPane().add(txtPrecio);

        JLabel lblStock = new JLabel("Stock:");
        lblStock.setBounds(10, 130, 100, 25);
        getContentPane().add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120, 130, 200, 25);
        getContentPane().add(txtStock);

        JLabel lblFechaVencimiento = new JLabel("Fecha Vencimiento (yyyy-MM-dd):");
        lblFechaVencimiento.setBounds(10, 170, 200, 25);
        getContentPane().add(lblFechaVencimiento);

        txtFechaVencimiento = new JTextField();
        txtFechaVencimiento.setBounds(220, 170, 100, 25);
        getContentPane().add(txtFechaVencimiento);

        JLabel lblNumeroLote = new JLabel("Número de Lote:");
        lblNumeroLote.setBounds(10, 210, 100, 25);
        getContentPane().add(lblNumeroLote);

        txtNumeroLote = new JTextField();
        txtNumeroLote.setBounds(120, 210, 200, 25);
        getContentPane().add(txtNumeroLote);

        JButton btnRegistrar = new JButton("Registrar Producto");
        btnRegistrar.setBounds(10, 250, 310, 25);
        getContentPane().add(btnRegistrar);
        
        JButton btnEnviarAAlertas = new JButton("Enviar a Alertas");
        btnEnviarAAlertas.setBounds(330, 250, 200, 25);
        getContentPane().add(btnEnviarAAlertas);
        btnEnviarAAlertas.addActionListener(e -> enviarProductoAAlertas());

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 290, 760, 160);
        getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción", "Precio", "Stock", "Fecha Venc.", "Lote"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        btnRegistrar.addActionListener(e -> registrarProducto());

        JButton btnAbrirAlertas = new JButton("Abrir Alertas");
        btnAbrirAlertas.setBounds(550, 250, 200, 25);
        btnAbrirAlertas.addActionListener(e -> abrirAlertas());
        getContentPane().add(btnAbrirAlertas);

        

        
        actualizarTabla();
    }
    
    private void abrirAlertas() {
        AlertasGUI alertasGUI = new AlertasGUI();
        //alertasGUI.actualizarAlertas(productoController.obtenerProductos());
        alertasGUI.actualizarAlertas();
        alertasGUI.setVisible(true);
    }
    
    private void enviarProductoAAlertas() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la tabla.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        String nombre = (String) tableModel.getValueAt(selectedRow, 1);
        String descripcion = (String) tableModel.getValueAt(selectedRow, 2);
        double precio = (double) tableModel.getValueAt(selectedRow, 3);
        int stock = (int) tableModel.getValueAt(selectedRow, 4);
        Date fechaVencimiento = (Date) tableModel.getValueAt(selectedRow, 5);
        String numeroLote = (String) tableModel.getValueAt(selectedRow, 6);

        Producto producto = new Producto(nombre, descripcion, precio, String.valueOf(id), fechaVencimiento, stock, numeroLote);

        // Crear instancia de AlertasGUI y pasarle el producto
        AlertasGUI alertasGUI = new AlertasGUI();
        alertasGUI.recibirProducto(producto);
        alertasGUI.setVisible(true);
    }

    private void registrarProducto() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(txtFechaVencimiento.getText().trim());
            java.sql.Date fechaVencimiento = new java.sql.Date(utilDate.getTime());

            Producto producto = new Producto(
                txtNombre.getText().trim(),
                txtDescripcion.getText().trim(),
                Double.parseDouble(txtPrecio.getText().trim()),
                txtNombre.getText().hashCode() + "", // Generar un código único basado en el nombre
                fechaVencimiento,
                Integer.parseInt(txtStock.getText().trim()),
                txtNumeroLote.getText().trim()
            );

            productoController.registrarProducto(producto);
            

            JOptionPane.showMessageDialog(this, "Producto registrado correctamente.");
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el producto: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0); 
        List<Producto> productos = productoController.obtenerProductos();
        for (Producto producto : productos) {
            tableModel.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getFechaVencimiento(),
                producto.getNumeroLote()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtFechaVencimiento.setText("");
        txtNumeroLote.setText("");
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
