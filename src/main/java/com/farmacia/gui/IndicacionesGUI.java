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

import com.farmacia.controller.IndicacionesController;
import com.farmacia.model.IndicacionesModel;

public class IndicacionesGUI extends JFrame 
{
    private JTextField txtIndicacion;
    private JTable table;
    private DefaultTableModel tableModel;

    private IndicacionesController indicacionesController = new IndicacionesController();

    public IndicacionesGUI() 
    {
        setTitle("Gestión de Indicaciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        getContentPane().setLayout(null);

        JLabel lblIndicacion = new JLabel("Indicacion:");
        lblIndicacion.setBounds(10, 10, 100, 25);
        getContentPane().add(lblIndicacion);

        txtIndicacion = new JTextField();
        txtIndicacion.setBounds(120, 10, 200, 25);
        getContentPane().add(txtIndicacion);

        JButton btnRegistrar = new JButton("Registrar Indicacion");
        btnRegistrar.setBounds(10, 50, 310, 25);
        getContentPane().add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 90, 760, 350);
        getContentPane().add(scrollPane);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Indicacion"}, 0);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        btnRegistrar.addActionListener(e -> registrarIndicacion());

        actualizarTabla();
    }

    private void registrarIndicacion() 
    {
        try {
            String indicacion = txtIndicacion.getText().trim();

            if (!indicacion.isEmpty()) 
            {
            	IndicacionesModel nuevaIndicacion = new IndicacionesModel(indicacion);
                indicacionesController.registrarIndicacion(nuevaIndicacion);

                JOptionPane.showMessageDialog(this, "Indicacion registrada correctamente.");
                actualizarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese una indicacion válida.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la indicacion: " + ex.getMessage());
        }
    }

    private void actualizarTabla() 
    {
        tableModel.setRowCount(0);
        List<IndicacionesModel> indicaciones = indicacionesController.obtenerIndicaciones();
        for (IndicacionesModel indicacion : indicaciones) 
        {
            tableModel.addRow(new Object[]{
                indicacion.getIdIndicacion(),
                indicacion.getIndicacion()
            });
        }
    }

    private void limpiarCampos() 
    {
        txtIndicacion.setText("");
    }

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> {
            try {
                IndicacionesGUI frame = new IndicacionesGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
