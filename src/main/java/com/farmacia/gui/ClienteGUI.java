package com.farmacia.gui;

import com.farmacia.controller.ClienteController;
import com.farmacia.model.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ClienteGUI extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTable table;
    private DefaultTableModel tableModel;

    private ClienteController clienteController = new ClienteController();

    public ClienteGUI() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(10, 10, 100, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(120, 10, 200, 25);
        getContentPane().add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(10, 50, 100, 25);
        getContentPane().add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(120, 50, 200, 25);
        getContentPane().add(txtApellidos);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(10, 90, 100, 25);
        getContentPane().add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(120, 90, 200, 25);
        getContentPane().add(txtTelefono);

        JButton btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setBounds(10, 130, 310, 25);
        getContentPane().add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 760, 260);
        getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "Teléfono"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        btnRegistrar.addActionListener(e -> registrarCliente());

        actualizarTabla();
    }

    private void registrarCliente() {
        try {
            ClienteModel cliente = new ClienteModel(
                0,
                txtNombre.getText().trim(),
                txtApellidos.getText().trim(),
                txtTelefono.getText().trim()
            );

            clienteController.registrarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
            actualizarTabla();
            limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el cliente: " + ex.getMessage());
        }
    }

    private void actualizarTabla() {
        tableModel.setRowCount(0); 
        List<ClienteModel> clientes = clienteController.obtenerClientes();
        for (ClienteModel cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getId_cliente(),
                cliente.getNombre(),
                cliente.getApellidos(),
                cliente.getTelefono()
            });
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClienteGUI frame = new ClienteGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
