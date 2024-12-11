package com.farmacia.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.farmacia.controller.MedidasController;
import com.farmacia.model.MedidasModel;

public class MedidasGUI extends JFrame 
{
    private JTextField txtMedida;
    private JTable table;
    private DefaultTableModel tableModel;

    private MedidasController medidasController = new MedidasController();

    public MedidasGUI() 
    {
        setTitle("Gestión de Medidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);

        JLabel lblMedida = new JLabel("Medida:");
        lblMedida.setBounds(10, 10, 100, 25);
        getContentPane().add(lblMedida);

        txtMedida = new JTextField();
        txtMedida.setBounds(120, 10, 200, 25);
        getContentPane().add(txtMedida);

        JButton btnRegistrar = new JButton("Registrar Medida");
        btnRegistrar.setBounds(10, 50, 310, 25);
        getContentPane().add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 760, 350);
        getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Medida"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        btnRegistrar.addActionListener(e -> registrarMedida());

        actualizarTabla();
    }

    private void registrarMedida() 
    {
        try {
            String medida = txtMedida.getText().trim();

            if (!medida.isEmpty()) 
            {
                MedidasModel nuevaMedida = new MedidasModel(0,medida);
                medidasController.registrarMedida(nuevaMedida);

                JOptionPane.showMessageDialog(this, "Medida registrada correctamente.");
                actualizarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese una medida válida.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la medida: " + ex.getMessage());
        }
    }

    private void actualizarTabla() 
    {
        tableModel.setRowCount(0);
        List<MedidasModel> medidas = medidasController.obtenerMedidas();
        for (MedidasModel medida : medidas) 
        {
            tableModel.addRow(new Object[]{
                medida.getId_medida(),
                medida.getMedida()
            });
        }
    }

    private void limpiarCampos() 
    {
        txtMedida.setText("");
    }

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> {
            try {
                MedidasGUI frame = new MedidasGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
