package com.farmacia.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.farmacia.dao.ProductoDAO;
import com.farmacia.model.Producto;

public class IWindowAgregarProducto extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidadEnStock;
    private JTextField txtFechaDeVencimiento;
    private JTextField txtNumeroDeLote;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IWindowAgregarProducto frame = new IWindowAgregarProducto();
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
    public IWindowAgregarProducto() {
        setTitle("Agregar Producto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 400);
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.activeCaption);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("AGREGAR PRODUCTO");
        lblTitulo.setForeground(SystemColor.info);
        lblTitulo.setFont(new Font("Bodoni MT Black", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(90, 10, 250, 30);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 100, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 60, 200, 25);
        contentPane.add(txtNombre);
        txtNombre.setColumns(10);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 100, 100, 25);
        contentPane.add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(150, 100, 200, 25);
        contentPane.add(txtDescripcion);
        txtDescripcion.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 140, 100, 25);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(150, 140, 200, 25);
        contentPane.add(txtPrecio);
        txtPrecio.setColumns(10);

        JLabel lblCantidadEnStock = new JLabel("Cantidad en Stock:");
        lblCantidadEnStock.setBounds(30, 180, 120, 25);
        contentPane.add(lblCantidadEnStock);

        txtCantidadEnStock = new JTextField();
        txtCantidadEnStock.setBounds(150, 180, 200, 25);
        contentPane.add(txtCantidadEnStock);
        txtCantidadEnStock.setColumns(10);

        JLabel lblFechaDeVencimiento = new JLabel("Fecha de Vencimiento:");
        lblFechaDeVencimiento.setBounds(30, 220, 150, 25);
        contentPane.add(lblFechaDeVencimiento);

        txtFechaDeVencimiento = new JTextField();
        txtFechaDeVencimiento.setBounds(180, 220, 170, 25);
        contentPane.add(txtFechaDeVencimiento);
        txtFechaDeVencimiento.setColumns(10);

        JLabel lblNumeroDeLote = new JLabel("Número de Lote:");
        lblNumeroDeLote.setBounds(30, 260, 120, 25);
        contentPane.add(lblNumeroDeLote);

        txtNumeroDeLote = new JTextField();
        txtNumeroDeLote.setBounds(150, 260, 200, 25);
        contentPane.add(txtNumeroDeLote);
        txtNumeroDeLote.setColumns(10);

        JButton btnRegistrar = new JButton("Registrar Producto");
        btnRegistrar.setBounds(150, 300, 200, 30);
        contentPane.add(btnRegistrar);

 
        btnRegistrar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                int cantidadEnStock = Integer.parseInt(txtCantidadEnStock.getText());
                String fechaDeVencimiento = txtFechaDeVencimiento.getText();
                int numeroDeLote = Integer.parseInt(txtNumeroDeLote.getText()); 

                /*Producto producto = new Producto(nombre, descripcion, precio, cantidadEnStock, fechaDeVencimiento, numeroDeLote);
                ProductoDAO productoDAO = new ProductoDAO();
                productoDAO.registrarProducto(producto);
*/

                JLabel lblConfirmacion = new JLabel("Producto registrado exitosamente.");
                lblConfirmacion.setBounds(150, 340, 200, 25);
                contentPane.add(lblConfirmacion);
            } catch (NumberFormatException ex) {
                JLabel lblError = new JLabel("Error en el formato de número. Por favor, revise los datos.");
                lblError.setBounds(150, 340, 250, 25);
                contentPane.add(lblError);
                ex.printStackTrace();
            } catch (Exception ex) {
                JLabel lblError = new JLabel("Error al registrar el producto. Por favor, revise los datos.");
                lblError.setBounds(150, 340, 250, 25);
                contentPane.add(lblError);
                ex.printStackTrace();
            }
        });
    }
}
