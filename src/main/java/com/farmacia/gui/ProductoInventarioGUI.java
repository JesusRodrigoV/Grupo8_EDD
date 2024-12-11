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
    private JTextField txtNombre, txtDescripcion, txtPrecio, txtCodigo, txtStock, txtMin, txtMax, txtActualizarId, txtActualizarCantidad;
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
        setBounds(100, 100, 875, 617);
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
        btnRegistrar.setBounds(50, 451, 200, 25);
        contentPane.add(btnRegistrar);

        // Tabla para mostrar inventario
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{
       	    "ID", "Código", "Nombre", "Descripción", "Precio", "Stock Actual", "Stock Mínimo", "Stock Máximo", "Fecha Venc.", "N° Lote"
       	});
       	tableInventario = new JTable(modeloTabla);
       	tableInventario.setBackground(new Color(186, 255, 41));
        JScrollPane scrollPane = new JScrollPane(tableInventario);
        scrollPane.setBounds(291, 76, 542, 400);
        contentPane.add(scrollPane);

        // Botones de acciones
        JLabel lblActualizarId = new JLabel("Código Producto:");
        lblActualizarId.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lblActualizarId.setBounds(10, 506, 100, 25);
        contentPane.add(lblActualizarId);

        txtActualizarId = new JTextField();
        txtActualizarId.setBounds(120, 506, 150, 25);
        contentPane.add(txtActualizarId);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblCantidad.setBounds(10, 543, 100, 25);
        contentPane.add(lblCantidad);

        txtActualizarCantidad = new JTextField();
        txtActualizarCantidad.setBounds(120, 543, 150, 25);
        contentPane.add(txtActualizarCantidad);

        JButton btnActualizar = new JButton("Actualizar Stock");
        btnActualizar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(102, 91, 196));
        btnActualizar.setBounds(312, 505, 200, 25);
        contentPane.add(btnActualizar);

        JButton btnVerificar = new JButton("Verificar Stock");
        btnVerificar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnVerificar.setBackground(new Color(102, 91, 196));
        btnVerificar.setBounds(546, 505, 200, 25);
        contentPane.add(btnVerificar);
        
        JLabel lbltitulo = new JLabel("GESTIÓN DE PRODUCTOS");
        lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbltitulo.setBackground(new Color(18, 200, 204));
        lbltitulo.setFont(new Font("Times New Roman", Font.BOLD, 30));
        lbltitulo.setBounds(234, 28, 471, 25);
        contentPane.add(lbltitulo);
        
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnVolver.setBackground(new Color(102, 91, 196));
        btnVolver.setBounds(10, 16, 150, 25);
        contentPane.add(btnVolver);

        btnVolver.addActionListener(e -> {
            dispose();
        });

     // Botones de acciones
        JButton btnEnviarAAlertas = new JButton("Enviar a Alertas");
        btnEnviarAAlertas.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnEnviarAAlertas.setBackground(new Color(102, 91, 196));
        btnEnviarAAlertas.setBounds(312, 543, 200, 25);
        contentPane.add(btnEnviarAAlertas);

        JButton btnAbrirAlertas = new JButton("Abrir Alertas");
        btnAbrirAlertas.setFont(new Font("Times New Roman", Font.BOLD, 14));
        btnAbrirAlertas.setBackground(new Color(102, 91, 196));
        btnAbrirAlertas.setBounds(546, 543, 200, 25);
        contentPane.add(btnAbrirAlertas);

        // Acción para enviar a alertas
        btnEnviarAAlertas.addActionListener(e -> {
            int selectedRow = tableInventario.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la tabla.");
                return;
            }

            String codigo = (String) modeloTabla.getValueAt(selectedRow, 0);
            String nombre = (String) modeloTabla.getValueAt(selectedRow, 1);
            String descripcion = (String) modeloTabla.getValueAt(selectedRow, 2);
            int stock = (int) modeloTabla.getValueAt(selectedRow, 3);
            int minimo = (int) modeloTabla.getValueAt(selectedRow, 4);
            int maximo = (int) modeloTabla.getValueAt(selectedRow, 5);

            Producto producto = new Producto(nombre, descripcion, 0, codigo, null, stock, null);

            // Crear instancia de AlertasGUI y pasarle el producto
            AlertasGUI alertasGUI = new AlertasGUI();
            alertasGUI.recibirProducto(producto);
            alertasGUI.setVisible(true);
        });

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

        // Acción para actualizar stock
        btnActualizar.addActionListener(e -> {
            String codigoStr = txtActualizarId.getText().trim();
            if (codigoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(txtActualizarCantidad.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Inventario inventario = gestionInventario.getInventarios().get(codigoStr);
            if (inventario != null) {
                int nuevoStock = inventario.getStockActual() + cantidad;
                inventario.setStockActual(nuevoStock);
                String mensajeEstado = generarMensajeEstadoStock(inventario);
                JOptionPane.showMessageDialog(this, mensajeEstado, "Estado del Stock", JOptionPane.INFORMATION_MESSAGE);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error: Producto no encontrado en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción para verificar stock
        btnVerificar.addActionListener(e -> {
            String codigo = txtActualizarId.getText().trim();
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Inventario inventario = gestionInventario.getInventarios().get(codigo);
            if (inventario != null) {
                String mensaje = generarMensajeEstadoStock(inventario);
                JOptionPane.showMessageDialog(this, mensaje, "Verificación de Stock", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Producto no encontrado en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
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