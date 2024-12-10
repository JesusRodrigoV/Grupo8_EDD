package com.farmacia.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        getContentPane().setLayout(null);

        // Etiqueta
        JLabel lblUsuarios = new JLabel("Lista de Usuarios");
        lblUsuarios.setBounds(20, 10, 200, 25);
        getContentPane().add(lblUsuarios);

        // Tabla para mostrar los usuarios
        tableModel = new DefaultTableModel(new String[]{"ID", "Usuario", "Rol"}, 0);
        tableUsuarios = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableUsuarios);
        scrollPane.setBounds(20, 40, 550, 200);
        getContentPane().add(scrollPane);

        // Botón para cargar usuarios
        JButton btnCargar = new JButton("Cargar Usuarios");
        btnCargar.setBounds(20, 260, 150, 30);
        getContentPane().add(btnCargar);

        // Botón para agregar usuario
        JButton btnAgregar = new JButton("Agregar Usuario");
        btnAgregar.setBounds(180, 260, 150, 30);
        getContentPane().add(btnAgregar);

        // Botón para editar usuario
        JButton btnEditar = new JButton("Editar Usuario");
        btnEditar.setBounds(340, 260, 150, 30);
        getContentPane().add(btnEditar);

        // Botón para eliminar usuario
        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.setBounds(20, 300, 150, 30);
        getContentPane().add(btnEliminar);
        
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnCerrar.setBounds(180, 300, 150, 30);
        getContentPane().add(btnCerrar);

        // Acciones de los botones
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarUsuarios();
            }
        });

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuario();
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
    }

    private void cargarUsuarios() {
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
