package com.farmacia.gui;

import com.farmacia.controller.ClienteController;
import com.farmacia.model.ClienteModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

        // Configurar color de fondo del panel
        getContentPane().setBackground(new Color(18, 200, 204));

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 55, 100, 25);
        lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 14));
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(160, 56, 200, 25);
        getContentPane().add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(393, 55, 100, 25);
        lblApellidos.setFont(new Font("Times New Roman", Font.BOLD, 14));
        getContentPane().add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setBounds(503, 56, 200, 25);
        getContentPane().add(txtApellidos);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(50, 90, 100, 25);
        lblTelefono.setFont(new Font("Times New Roman", Font.BOLD, 14));
        getContentPane().add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(160, 91, 200, 25);
        getContentPane().add(txtTelefono);

        JButton btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setBounds(66, 127, 310, 25);
        btnRegistrar.setBackground(new Color(102, 91, 196)); // Color de fondo del botón
        btnRegistrar.setForeground(Color.WHITE); // Color del texto del botón
        getContentPane().add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 760, 260);
        getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellidos", "Teléfono"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setBackground(new Color(102, 91, 196));
        btnCerrar.setBounds(393, 127, 310, 25);
        getContentPane().add(btnCerrar);
        
        JLabel lblNewLabel = new JLabel("Gestion Clientes");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 20, 766, 25);
        getContentPane().add(lblNewLabel);

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
