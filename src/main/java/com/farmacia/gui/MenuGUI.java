package com.farmacia.gui;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuGUI frame = new MenuGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MenuGUI() {
        setTitle("Farmacia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600); // Ajustar tamaño de la ventana
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        // Crear el panel izquierdo (sidebar) con tamaño fijo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBounds(-5, 0, 161, 577);
        panelIzquierdo.setBackground(new Color(233, 240, 216));
        panelIzquierdo.setPreferredSize(new Dimension(150, 0)); // Ancho fijo de 150 píxeles
        panelIzquierdo.setLayout(null);

        JButton boton1 = new JButton("Algo");
        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Acción del botón
            }
        });
        boton1.setForeground(new Color(255, 255, 255));
        boton1.setBackground(new Color(74, 144, 226));
        boton1.setFont(new Font("Roboto", Font.PLAIN, 12));
        boton1.setBorderPainted(false);
        boton1.setFocusPainted(false);
        boton1.setBounds(10, 41, 151, 31);
        panelIzquierdo.add(boton1);

        // Crear el panel derecho (contenido) que se expande
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBounds(155, 5, 631, 558);
        panelDerecho.setBackground(new Color(255, 255, 255));
        contentPane.setLayout(null);

        // Añadir los paneles al contentPane
        contentPane.add(panelIzquierdo); // Panel izquierdo fijo
        contentPane.add(panelDerecho); // Panel derecho que se expande
    }
}
