package com.farmacia.gui;

import javax.swing.*;
import com.farmacia.controller.VentaController;
import com.farmacia.dao.ClienteDAO;
import com.farmacia.dao.ProductoDAO;
import com.farmacia.model.*;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentaGUI extends JFrame {
    private Usuario usuarioActual;
    private JTextField txtCantidad, txtTotal;
    private JComboBox<ClienteModel> cbClientes;
    private JComboBox<Producto> cbProductos;
    private JButton btnRegistrarVenta;
    private VentaController controller;

    public VentaGUI(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.controller = new VentaController();
        initialize();
    }

    private void initialize() {
        setTitle("Gestión de Ventas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(null);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(20, 20, 100, 25);
        add(lblCliente);

        cbClientes = new JComboBox<>();
        cbClientes.setBounds(120, 20, 200, 25);
        add(cbClientes);
        cbClientes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof ClienteModel) {
                    ClienteModel cliente = (ClienteModel) value;
                    label.setText(cliente.getNombre());  // Solo mostrar el nombre
                }
                return label;
            }
        });


        JLabel lblProducto = new JLabel("Producto:");
        lblProducto.setBounds(20, 60, 100, 25);
        add(lblProducto);

        cbProductos = new JComboBox<>();
        cbProductos.setBounds(120, 60, 200, 25);
        add(cbProductos);

        cbProductos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Producto) {
                    Producto producto = (Producto) value;
                    label.setText(producto.getNombre());  // Solo mostrar el nombre
                }
                return label;
            }
        });

        
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 100, 100, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 100, 200, 25);
        add(txtCantidad);


        btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.setBounds(120, 180, 150, 30);
        add(btnRegistrarVenta);

        btnRegistrarVenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarVenta();
            }
        });

        cargarClientes();
        cargarProductos();

        setVisible(true);
    }

    private void cargarClientes() {
        // Cargar clientes desde la base de datos.
        ClienteDAO clienteDAO = new ClienteDAO();
        List<ClienteModel> clientes = clienteDAO.listarClientes();
        for (ClienteModel cliente : clientes) {
            cbClientes.addItem(cliente);
        }
    }

    private void cargarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.listarProductosDisponibles();
        for (Producto producto : productos) {
            cbProductos.addItem(producto);
        }
    }

    private void registrarVenta() {
        ClienteModel cliente = (ClienteModel) cbClientes.getSelectedItem();
        Producto producto = (Producto) cbProductos.getSelectedItem();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        double total = producto.getPrecio() * cantidad;
        String fecha = java.time.LocalDate.now().toString();

        VentaModel venta = new VentaModel(0, cliente.getId_cliente(), producto.getId(), cantidad, total, fecha);

        if (controller.registrarVenta(venta)) {
            JOptionPane.showMessageDialog(this, "Venta registrada con éxito");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta");
        }
    }


}
