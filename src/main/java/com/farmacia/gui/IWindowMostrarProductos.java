package com.farmacia.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.farmacia.dao.ProductoDAO;
import com.farmacia.model.Producto;

public class IWindowMostrarProductos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IWindowMostrarProductos frame = new IWindowMostrarProductos();
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
    public IWindowMostrarProductos() {
        setResizable(false);
        setTitle("Productos Lista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 300);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel LabelProductosLista = new JLabel("PRODUCTOS LISTA");
        LabelProductosLista.setForeground(SystemColor.info);
        LabelProductosLista.setBounds(356, 5, 272, 24);
        LabelProductosLista.setFont(new Font("Bodoni MT Black", Font.BOLD, 20));
        LabelProductosLista.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(LabelProductosLista);

        JPanel panel = new JPanel();
        panel.setBounds(10, 34, 964, 222);
        panel.setBackground(SystemColor.inactiveCaption);
        contentPane.add(panel);
        panel.setLayout(null);

        ImageIcon fondoAmarillo = new ImageIcon("FondoAmarillo.jpg");

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        model.addColumn("Producto ID");
        model.addColumn("Nombre");
        model.addColumn("Descipción");
        model.addColumn("Precio");
        model.addColumn("Cantidad En Stock");
        model.addColumn("Fecha De Vencimiento");
        model.addColumn("Número De Lote");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 9, 944, 202);
        panel.add(scrollPane);

        JLabel LabelImagen1 = new JLabel("");
        LabelImagen1.setBounds(0, 0, 964, 222);
        panel.add(LabelImagen1);
        LabelImagen1.setIcon(new ImageIcon(fondoAmarillo.getImage().getScaledInstance(970, 225, Image.SCALE_SMOOTH)));

        JLabel LabelImagen2 = new JLabel("");
        ImageIcon fondo = new ImageIcon("Fondo.png");
        LabelImagen2.setIcon(new ImageIcon(fondo.getImage().getScaledInstance(990, 280, Image.SCALE_SMOOTH)));
        LabelImagen2.setBounds(0, 0, 984, 274);
        contentPane.add(LabelImagen2);


        ProductoDAO productoDAO = new ProductoDAO();
        List<Producto> productos = productoDAO.obtenerProductos();
        for (Producto producto : productos) {
            model.addRow(new Object[]{
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getCantidadEnStock(),
                producto.getFechaDeVencimiento(),
                producto.getNumeroDeLote()
            });
        }
    }
}
