package com.farmacia.gui;

import com.farmacia.controller.ProductoController;
import com.farmacia.dao.RegistroProducto;
import com.farmacia.model.GestionInventario;
import com.farmacia.model.Inventario;
import com.farmacia.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ProductoInventarioGUI extends JFrame {
    private JPanel contentPane;
    private JTextField txtNombre, txtDescripcion, txtPrecio, txtCodigo, txtStock, txtMin, txtMax;
    private JTextField txtFechaVenc, txtLote;
    private JTable tableInventario;
    private DefaultTableModel modeloTabla;
    private RegistroProducto registroProducto;
    private GestionInventario gestionInventario;
    private ProductoController productoController;

    public ProductoInventarioGUI() {
        registroProducto = new RegistroProducto();
        gestionInventario = new GestionInventario();
        productoController = new ProductoController();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 959, 564);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(18, 200, 204));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Formulario para registrar productos
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblNombre.setBounds(10, 121, 100, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 122, 150, 25);
        contentPane.add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblDescripcion.setBounds(10, 156, 100, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 157, 150, 25);
        contentPane.add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPrecio.setBounds(10, 191, 100, 25);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 192, 150, 25);
        contentPane.add(txtPrecio);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCodigo.setBounds(10, 226, 100, 25);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 227, 150, 25);
        contentPane.add(txtCodigo);

        JLabel lblStock = new JLabel("Stock inicial:");
        lblStock.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblStock.setBounds(10, 261, 100, 25);
        contentPane.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120, 262, 150, 25);
        contentPane.add(txtStock);

        JLabel lblMin = new JLabel("Stock mínimo:");
        lblMin.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblMin.setBounds(10, 296, 100, 25);
        contentPane.add(lblMin);

        txtMin = new JTextField();
        txtMin.setBounds(120, 297, 150, 25);
        contentPane.add(txtMin);

        JLabel lblMax = new JLabel("Stock máximo:");
        lblMax.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblMax.setBounds(10, 331, 100, 25);
        contentPane.add(lblMax);

        txtMax = new JTextField();
        txtMax.setBounds(120, 332, 150, 25);
        contentPane.add(txtMax);
        
        JLabel lblFechaVenc = new JLabel("Fecha Venc:");
        lblFechaVenc.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblFechaVenc.setBounds(10, 366, 100, 25);
        contentPane.add(lblFechaVenc);

        txtFechaVenc = new JTextField();
        txtFechaVenc.setBounds(120, 367, 150, 25);
        contentPane.add(txtFechaVenc);

        JLabel lblLote = new JLabel("N° Lote:");
        lblLote.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblLote.setBounds(10, 401, 100, 25);
        contentPane.add(lblLote);

        txtLote = new JTextField();
        txtLote.setBounds(120, 402, 150, 25);
        contentPane.add(txtLote);

        JButton btnRegistrar = new JButton("Registrar Producto");
        btnRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnRegistrar.setBackground(new Color(102, 91, 196));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setBounds(50, 451, 200, 25);
        contentPane.add(btnRegistrar);

        // Tabla para mostrar inventario
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{
       	    "ID", "Código", "Nombre", "Descripción", "Precio", "Stock Actual", "Stock Mínimo", "Stock Máximo", "Fecha Venc.", "N° Lote"
       	});
       	tableInventario = new JTable(modeloTabla);
       	tableInventario.setBackground(new Color(186, 255, 41));
        JScrollPane scrollPane = new JScrollPane(tableInventario);
        scrollPane.setBounds(291, 76, 631, 435);
        contentPane.add(scrollPane);
        
        JLabel lbltitulo = new JLabel("GESTIÓN DE PRODUCTOS");
        lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitulo.setBackground(new Color(18, 200, 204));
        lbltitulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbltitulo.setBounds(234, 28, 471, 25);
        contentPane.add(lbltitulo);
        
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(new Color(102, 91, 196));
        btnVolver.setBounds(10, 16, 150, 25);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(e -> {
            dispose();
        });

        JButton btnAbrirAlertas = new JButton("Abrir Alertas");
        btnAbrirAlertas.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnAbrirAlertas.setForeground(Color.WHITE);
        btnAbrirAlertas.setBackground(new Color(102, 91, 196));
        btnAbrirAlertas.setBounds(50, 486, 200, 25);
        contentPane.add(btnAbrirAlertas);

        // Acción para abrir alertas
        btnAbrirAlertas.addActionListener(e -> {
            AlertasGUI alertasGUI = new AlertasGUI();
            alertasGUI.actualizarAlertas();
            alertasGUI.setVisible(true);
        });
        
        // Acción para registrar producto
        btnRegistrar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String descripcion = txtDescripcion.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText());
                String codigo = txtCodigo.getText().trim();
                int stock = Integer.parseInt(txtStock.getText());
                int minimo = Integer.parseInt(txtMin.getText());
                int maximo = Integer.parseInt(txtMax.getText());
                String fechaStr = txtFechaVenc.getText().trim();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date utilDate = sdf.parse(fechaStr);
                java.sql.Date fechaVencimiento = new java.sql.Date(utilDate.getTime());

                if (nombre.isEmpty() || descripcion.isEmpty() || codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (minimo > maximo) {
                    JOptionPane.showMessageDialog(this, "El stock mínimo no puede ser mayor que el máximo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Producto producto = new Producto(nombre, descripcion, precio, codigo, fechaVencimiento, stock, txtLote.getText().trim());
                Inventario inventario = new Inventario(producto, stock, minimo, maximo);
                gestionInventario.agregarInventario(inventario);

                //modeloTabla.addRow(new Object[]{codigo, nombre, descripcion, stock, minimo, maximo});
                productoController.registrarProducto(producto);
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Producto registrado exitosamente.");
                limpiarCamposRegistro();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        actualizarTabla();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Inventario inventario : gestionInventario.getInventarios().values()) {
            Producto producto = inventario.getProducto();
            modeloTabla.addRow(new Object[]{
                producto.getId(), // ID del producto
                producto.getCodigoBarras(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                inventario.getStockActual(),
                inventario.getStockMinimo(),
                inventario.getStockMaximo(),
                producto.getFechaVencimiento(),
                producto.getNumeroLote()
            });
        }
    }

    private String generarMensajeEstadoStock(Inventario inventario) {
        int stockActual = inventario.getStockActual();
        int stockMinimo = inventario.getStockMinimo();
        int stockMaximo = inventario.getStockMaximo();

        if (stockActual < stockMinimo) {
            return "✔️ El producto " + inventario.getProducto().getNombre() + " está por debajo del stock mínimo.";
        } else if (stockActual == stockMinimo) {
            return "✔️ El producto " + inventario.getProducto().getNombre() + " está en el stock mínimo.";
        } else if (stockActual > stockMaximo) {
            return "✔️ El producto " + inventario.getProducto().getNombre() + " está por encima del stock máximo.";
        } else {
            return "✔️ El producto " + inventario.getProducto().getNombre() + " tiene suficiente stock.";
        }
    }

    private void limpiarCamposRegistro() {
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCodigo.setText("");
        txtStock.setText("");
        txtMin.setText("");
        txtMax.setText("");
        txtFechaVenc.setText("");
        txtLote.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductoInventarioGUI frame = new ProductoInventarioGUI();
            frame.setVisible(true);
        });
    }
}