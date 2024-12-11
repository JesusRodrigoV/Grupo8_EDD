package com.farmacia.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import com.farmacia.controller.UsuarioController;
import com.farmacia.model.Usuario;

public class UsuariosGUI extends JFrame {
    private JTable tableUsuarios;
    private DefaultTableModel tableModel;
    private UsuarioController usuarioController = new UsuarioController();

    public UsuariosGUI() {
        setTitle("Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(18, 200, 204));
        getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta
        JLabel lblUsuarios = new JLabel("Lista de Usuarios");
        lblUsuarios.setFont(new Font("Times New Roman", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        getContentPane().add(lblUsuarios, gbc);

        // Tabla para mostrar los usuarios
        tableModel = new DefaultTableModel(new String[]{"ID", "Usuario", "Rol"}, 0);
        tableUsuarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        getContentPane().add(scrollPane, gbc);

        // Botón para agregar usuario
        JButton btnAgregar = new JButton("Agregar Usuario");
        btnAgregar.setBackground(new Color(102, 91, 196));
        btnAgregar.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(btnAgregar, gbc);

        // Botón para editar usuario
        JButton btnEditar = new JButton("Editar Usuario");
        btnEditar.setBackground(new Color(102, 91, 196));
        btnEditar.setForeground(Color.WHITE);
        gbc.gridx = 1;
        getContentPane().add(btnEditar, gbc);

        // Botón para eliminar usuario
        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.setBackground(new Color(102, 91, 196));
        btnEliminar.setForeground(Color.WHITE);
        gbc.gridy = 4;
        gbc.gridx = 0;
        getContentPane().add(btnEliminar, gbc);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.setBackground(new Color(102, 91, 196));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        gbc.gridx = 1;
        getContentPane().add(btnCerrar, gbc);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
                cargarUsuarios();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarUsuario();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
        cargarUsuarios();
    }

    public void cargarUsuarios() {
        List<Usuario> usuarios = usuarioController.obtenerUsuarios();
        tableModel.setRowCount(0); // Limpiar tabla
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getUsuario(),
                    usuario.getRole().getNombre()
            });
        }
    }

    private void agregarUsuario() {
        UsuarioGUI usuarioGUI = new UsuarioGUI();
        usuarioGUI.setVisible(true);
        cargarUsuarios();
    }

    private void editarUsuario() {
        int selectedRow = tableUsuarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.");
            return;
        }

        int usuarioId = (int) tableModel.getValueAt(selectedRow, 0);
        String nombreUsuario = (String) tableModel.getValueAt(selectedRow, 1);
        String rol = (String) tableModel.getValueAt(selectedRow, 2);

        // Ventana para editar usuario
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre de usuario:", nombreUsuario);
        String nuevoRol = JOptionPane.showInputDialog(this, "Nuevo rol:", rol);

        if (nuevoNombre != null && nuevoRol != null) {
            usuarioController.editarUsuario(usuarioId, nuevoNombre, nuevoRol);
            cargarUsuarios();
        }
    }

    private void eliminarUsuario() {
        int selectedRow = tableUsuarios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }

        int usuarioId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar este usuario?");
        if (confirm == JOptionPane.YES_OPTION) {
            usuarioController.eliminarUsuario(usuarioId);
            cargarUsuarios();
        }
    }

    public static void main(String[] args) {
        UsuariosGUI frame = new UsuariosGUI();
        frame.setVisible(true);
    }
}
