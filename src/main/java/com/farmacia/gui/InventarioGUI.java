package com.farmacia.gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.farmacia.dao.RegistroProducto;
import com.farmacia.model.GestionInventario;
import com.farmacia.model.Inventario;
import com.farmacia.model.Producto;

import java.awt.event.*;
import java.util.HashMap;

public class InventarioGUI extends JFrame {
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField  txtDescripcion;
    private JTextField  txtPrecio;
    private JTextField  txtCodigo;
    private JTextField  txtStock;
    private JTextField  txtMin;
    private JTextField  txtMax;
    private JTextField  txtActualizarId;
    private JTextField  txtActualizarCantidad;
    private JTable tableInventario;
    private DefaultTableModel modeloTabla;
    private RegistroProducto registroProducto;
    private GestionInventario gestionInventario;

    public InventarioGUI() {
        registroProducto = new RegistroProducto();
        gestionInventario = new GestionInventario();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 846, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Formulario para registrar productos
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 100, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 10, 150, 25);
        contentPane.add(txtNombre);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(10, 40, 100, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(120, 40, 150, 25);
        contentPane.add(txtDescripcion);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(10, 70, 100, 25);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 70, 150, 25);
        contentPane.add(txtPrecio);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(10, 100, 100, 25);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(120, 100, 150, 25);
        contentPane.add(txtCodigo);

        JLabel lblStock = new JLabel("Stock inicial:");
        lblStock.setBounds(10, 130, 100, 25);
        contentPane.add(lblStock);

        txtStock = new JTextField();
        txtStock.setBounds(120, 130, 150, 25);
        contentPane.add(txtStock);

        JLabel lblMin = new JLabel("Stock mínimo:");
        lblMin.setBounds(10, 160, 100, 25);
        contentPane.add(lblMin);

        txtMin = new JTextField();
        txtMin.setBounds(120, 160, 150, 25);
        contentPane.add(txtMin);

        JLabel lblMax = new JLabel("Stock máximo:");
        lblMax.setBounds(10, 190, 100, 25);
        contentPane.add(lblMax);

        txtMax = new JTextField();
        txtMax.setBounds(120, 190, 150, 25);
        contentPane.add(txtMax);

        JButton btnRegistrar = new JButton("Registrar Producto");
        btnRegistrar.setBounds(10, 220, 200, 25);
        contentPane.add(btnRegistrar);

        // Tabla para mostrar inventario
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{"Código", "Nombre", "Descripción", "Stock Actual", "Stock Mínimo", "Stock Máximo"});
        tableInventario = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tableInventario);
        scrollPane.setBounds(280, 10, 526, 400);
        contentPane.add(scrollPane);

        // Botones de acciones
        JLabel lblActualizarId = new JLabel("Código Producto:");
        lblActualizarId.setBounds(10, 260, 100, 25);
        contentPane.add(lblActualizarId);

        txtActualizarId = new JTextField();
        txtActualizarId.setBounds(120, 260, 150, 25);
        contentPane.add(txtActualizarId);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(10, 290, 100, 25);
        contentPane.add(lblCantidad);

        txtActualizarCantidad = new JTextField();
        txtActualizarCantidad.setBounds(120, 290, 150, 25);
        contentPane.add(txtActualizarCantidad);

        JButton btnActualizar = new JButton("Actualizar Stock");
        btnActualizar.setBounds(10, 320, 200, 25);
        contentPane.add(btnActualizar);

        JButton btnVerificar = new JButton("Verificar Stock");
        btnVerificar.setBounds(10, 350, 200, 25);
        contentPane.add(btnVerificar);

        // Acción para registrar producto
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            String codigo = txtCodigo.getText();
            int stock = Integer.parseInt(txtStock.getText());
            int minimo = Integer.parseInt(txtMin.getText());
            int maximo = Integer.parseInt(txtMax.getText());

            // Crear el producto, el id se genera automáticamente
            Producto producto = new Producto(nombre, descripcion, precio, codigo);
            Inventario inventario = new Inventario(producto, stock, minimo, maximo);
            gestionInventario.agregarInventario(inventario);

            modeloTabla.addRow(new Object[]{producto.getCodigoBarras(), producto.getNombre(), descripcion, stock, minimo, maximo});
            JOptionPane.showMessageDialog(null, "Producto registrado exitosamente.");
        });

        // Acción para actualizar stock
        btnActualizar.addActionListener(e -> {
            String codigoStr = txtActualizarId.getText();
            int cantidad = Integer.parseInt(txtActualizarCantidad.getText());

            // Buscar el producto por código
            boolean productoExistente = false;
            for (Inventario inv : gestionInventario.getInventarios().values()) {
                if (inv.getProducto().getCodigoBarras().equals(codigoStr)) {
                    productoExistente = true;
                    inv.setStockActual(cantidad);  // Reemplazamos el stock actual por el valor ingresado

                    // Verificar el estado del stock
                    String mensajeEstado = "Estado del stock:\n";
                    if (cantidad < inv.getStockMinimo()) {
                        mensajeEstado += "✔️ El producto " + inv.getProducto().getNombre() + " está por debajo del stock mínimo, realizar pedido.";
                    } else if (cantidad == inv.getStockMinimo()) {
                        mensajeEstado += "✔️ El producto " + inv.getProducto().getNombre() + " está en el stock mínimo.";
                    } else if (cantidad > inv.getStockMaximo()) {
                        mensajeEstado += "✔️ El producto " + inv.getProducto().getNombre() + " está por encima del stock máximo.";
                    } else {
                        mensajeEstado += "✔️ El producto " + inv.getProducto().getNombre() + " tiene suficiente stock.";
                    }

                    JOptionPane.showMessageDialog(null, mensajeEstado);  // Mostrar mensaje del estado del stock
                    break;
                }
            }

            if (!productoExistente) {
                JOptionPane.showMessageDialog(null, "Error: Producto no encontrado en el inventario.");
            }

            // Actualizar la tabla
            actualizarTabla();
        });

        // Acción para verificar stock
        btnVerificar.addActionListener(e -> {
            String codigo = txtActualizarId.getText().trim(); // Eliminar espacios en blanco
            if (codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Inventario inventario = gestionInventario.getInventarios().get(codigo);
            if (inventario != null) {
                // Generar el mensaje del estado del stock
                String mensaje = generarMensajeEstadoStock(inventario);
                JOptionPane.showMessageDialog(this, mensaje, "Verificación de Stock", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error: Producto no encontrado en el inventario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Inventario inv : gestionInventario.getInventarios().values()) {
            modeloTabla.addRow(new Object[]{
                inv.getProducto().getCodigoBarras(),
                inv.getProducto().getNombre(),
                inv.getProducto().getDescripcion(),
                inv.getStockActual(),
                inv.getStockMinimo(),
                inv.getStockMaximo()
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	InventarioGUI frame = new InventarioGUI();
            frame.setVisible(true);
        });
    }
}
