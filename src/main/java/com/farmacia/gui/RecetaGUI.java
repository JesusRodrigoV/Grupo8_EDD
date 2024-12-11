package com.farmacia.gui;

import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.farmacia.controller.ClienteController;
import com.farmacia.controller.IndicacionesController;
import com.farmacia.controller.MedidasController;
import com.farmacia.controller.ProductoController;
import com.farmacia.controller.RecetaController;
import com.farmacia.model.ClienteModel;
import com.farmacia.model.IndicacionesModel;
import com.farmacia.model.MedidasModel;
import com.farmacia.model.Producto;
import com.farmacia.model.RecetaModel;

public class RecetaGUI extends JFrame 
{
    private JComboBox<ClienteModel> comboCliente;
    private JComboBox<Producto> comboProducto;
    private JComboBox<MedidasModel> comboMedida;
    private JComboBox<IndicacionesModel> comboIndicacion;
    
    private JTextField txtBuscarId;
    private JButton btnBuscarReceta;
    private JTextArea txtDetalleReceta;
    
    private JTextField txtCantidad;
    private JTable tableProductos;
    private DefaultTableModel tableModelProductos;
    private JTable tableRecetas;
    private DefaultTableModel tableModelRecetas;
    
    private RecetaController recetaController = new RecetaController();
    private ProductoController productoController = new ProductoController();
    private ClienteController clienteController = new ClienteController();
    private IndicacionesController indicacionController = new IndicacionesController();
    private MedidasController medidasController = new MedidasController();

    public RecetaGUI() 
    {
    	setTitle("Gestión de Recetas Médicas");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setBounds(100, 100, 900, 650); 
    	getContentPane().setLayout(null);

    	JLabel lblBuscarId = new JLabel("Buscar por ID de Receta:");
    	lblBuscarId.setBounds(10, 10, 150, 25);
    	getContentPane().add(lblBuscarId);

    	txtBuscarId = new JTextField();
    	txtBuscarId.setBounds(160, 10, 200, 25);
    	getContentPane().add(txtBuscarId);

    	btnBuscarReceta = new JButton("Buscar");
    	btnBuscarReceta.setBounds(370, 10, 100, 25);
    	getContentPane().add(btnBuscarReceta);
    	btnBuscarReceta.addActionListener(e -> buscarRecetaPorId());

    	txtDetalleReceta = new JTextArea();
    	txtDetalleReceta.setBounds(10, 50, 850, 150);
    	txtDetalleReceta.setEditable(false);
    	getContentPane().add(txtDetalleReceta);

    	JLabel lblCliente = new JLabel("Cliente:");
    	lblCliente.setBounds(10, 220, 100, 25);
    	getContentPane().add(lblCliente);
    	comboCliente = new JComboBox<>();
    	comboCliente.setBounds(120, 220, 200, 25);
    	getContentPane().add(comboCliente);
    	cargarClientes();

    	JLabel lblProducto = new JLabel("Producto:");
    	lblProducto.setBounds(10, 260, 100, 25);
    	getContentPane().add(lblProducto);
    	comboProducto = new JComboBox<>();
    	comboProducto.setBounds(120, 260, 200, 25);
    	getContentPane().add(comboProducto);
    	cargarProductos();

    	JLabel lblMedida = new JLabel("Medida:");
    	lblMedida.setBounds(10, 300, 100, 25);
    	getContentPane().add(lblMedida);
    	comboMedida = new JComboBox<>();
    	comboMedida.setBounds(120, 300, 200, 25);
    	getContentPane().add(comboMedida);
    	cargarMedidas();

    	JLabel lblIndicacion = new JLabel("Indicación:");
    	lblIndicacion.setBounds(10, 340, 100, 25);
    	getContentPane().add(lblIndicacion);
    	comboIndicacion = new JComboBox<>();
    	comboIndicacion.setBounds(120, 340, 200, 25);
    	getContentPane().add(comboIndicacion);
    	cargarIndicaciones();

    	JLabel lblCantidad = new JLabel("Cantidad:");
    	lblCantidad.setBounds(10, 380, 100, 25);
    	getContentPane().add(lblCantidad);
    	txtCantidad = new JTextField();
    	txtCantidad.setBounds(120, 380, 200, 25);
    	getContentPane().add(txtCantidad);

    	JButton btnAgregarProducto = new JButton("Agregar Producto");
    	btnAgregarProducto.setBounds(10, 420, 200, 25); 
    	getContentPane().add(btnAgregarProducto);
    	btnAgregarProducto.addActionListener(e -> agregarProductoReceta());

    	JScrollPane scrollPaneProductos = new JScrollPane();
    	scrollPaneProductos.setBounds(10, 460, 850, 150);
    	getContentPane().add(scrollPaneProductos);
    	tableModelProductos = new DefaultTableModel(new Object[]{"Producto", "Cantidad", "Medida", "Indicación"}, 0);
    	tableProductos = new JTable(tableModelProductos);
    	scrollPaneProductos.setViewportView(tableProductos);

    	JScrollPane scrollPaneRecetas = new JScrollPane();
    	scrollPaneRecetas.setBounds(10, 620, 850, 150); 
    	getContentPane().add(scrollPaneRecetas);
    	tableModelRecetas = new DefaultTableModel(new Object[]{"CI", "Nombre", "Apellidos", "Producto", "Cantidad", "Fecha", "Indicación"}, 0);
    	tableRecetas = new JTable(tableModelRecetas);
    	scrollPaneRecetas.setViewportView(tableRecetas);

    	JButton btnRegistrarReceta = new JButton("Registrar Receta");
    	btnRegistrarReceta.setBounds(220, 420, 200, 25);
    	getContentPane().add(btnRegistrarReceta);
    	btnRegistrarReceta.addActionListener(e -> registrarReceta());

    	actualizarTablaRecetas();

    }

    private void cargarClientes() 
    {
        List<ClienteModel> clientes = clienteController.obtenerClientes();
        for (ClienteModel cliente : clientes) 
        {
            comboCliente.addItem(cliente);
        }
    }

    private void cargarProductos() 
    {
        List<Producto> productos = productoController.obtenerProductos();
        for (Producto producto : productos) 
        {
            comboProducto.addItem(producto);
        }
    }

    private void cargarMedidas() 
    {
        List<MedidasModel> medidas = medidasController.obtenerMedidas();
        for (MedidasModel medida : medidas) 
        {
            comboMedida.addItem(medida);
        }
    }

    private void cargarIndicaciones() 
    {
        List<IndicacionesModel> indicaciones = indicacionController.obtenerIndicaciones();
        for (IndicacionesModel indicacion : indicaciones) 
        {
            comboIndicacion.addItem(indicacion);
        }
    }
    
    private void agregarProductoReceta() 
    {
        Producto producto = (Producto) comboProducto.getSelectedItem();
        MedidasModel medida = (MedidasModel) comboMedida.getSelectedItem();
        IndicacionesModel indicacion = (IndicacionesModel) comboIndicacion.getSelectedItem();
        String cantidad = txtCantidad.getText();

        if (producto != null && medida != null && indicacion != null && !cantidad.isEmpty()) 
        {
            boolean productoExistente = false;
            for (int i = 0; i < tableModelProductos.getRowCount(); i++) 
            {
                Producto productoExistenteEnTabla = (Producto) tableModelProductos.getValueAt(i, 0);
                MedidasModel medidaExistenteEnTabla = (MedidasModel) tableModelProductos.getValueAt(i, 2);
                IndicacionesModel indicacionExistenteEnTabla = (IndicacionesModel) tableModelProductos.getValueAt(i, 3);

                if (productoExistenteEnTabla.equals(producto) && medidaExistenteEnTabla.equals(medida) && indicacionExistenteEnTabla.equals(indicacion)) 
                {
                    Double cantidadExistente = Double.parseDouble(tableModelProductos.getValueAt(i, 1).toString());
                    Double cantidadNueva = Double.parseDouble(cantidad);
                    tableModelProductos.setValueAt(cantidadExistente + cantidadNueva, i, 1);
                    productoExistente = true;
                    break;
                }
            }

            if (!productoExistente) 
            {
                tableModelProductos.addRow(new Object[]{producto, cantidad, medida, indicacion});
            }
        }
    }

    private void registrarReceta() 
    {
        try {
            ClienteModel cliente = (ClienteModel) comboCliente.getSelectedItem();
            if (cliente == null) 
            {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un cliente.");
                return;
            }

            for (int i = 0; i < tableModelProductos.getRowCount(); i++) 
            {
                Producto producto = (Producto) tableModelProductos.getValueAt(i, 0);
                MedidasModel medida = (MedidasModel) tableModelProductos.getValueAt(i, 2);
                IndicacionesModel indicacion = (IndicacionesModel) tableModelProductos.getValueAt(i, 3);

           
                try {
                    Double cantidad = Double.parseDouble(tableModelProductos.getValueAt(i, 1).toString());

                    boolean productoYaExistente = false;
                    for (int j = 0; j < i; j++) 
                    { 
                        Producto productoAnterior = (Producto) tableModelProductos.getValueAt(j, 0);
                        MedidasModel medidaExistente = (MedidasModel) tableModelProductos.getValueAt(j, 2);
                        IndicacionesModel indicacionExistente = (IndicacionesModel) tableModelProductos.getValueAt(j, 3);

                        if (productoAnterior.equals(producto) && medidaExistente.equals(medida) && indicacionExistente.equals(indicacion)) 
                        {
                            Double cantidadExistente = Double.parseDouble(tableModelProductos.getValueAt(j, 1).toString());
                            tableModelProductos.setValueAt(cantidadExistente + cantidad, j, 1);
                            productoYaExistente = true; 
                            break;
                        }
                    }

                    if (!productoYaExistente) 
                    {
                        RecetaModel receta = new RecetaModel(0, cliente.getId_cliente(), producto.getId(), medida.getId_medida(), indicacion.getIdIndicacion(), new Date(), cantidad);
                        recetaController.registrarReceta(receta);
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un número válido en la fila " + (i + 1) + ".");
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Recetas registradas exitosamente.");
            actualizarTablaRecetas();
            limpiarCampos(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al registrar la receta: " + e.getMessage());
        }
    }

    private void actualizarTablaRecetas() 
    {
        tableModelRecetas.setRowCount(0);  
        List<RecetaModel> recetas = recetaController.obtenerRecetas();
        for (RecetaModel receta : recetas) 
        {
            ClienteModel cliente = clienteController.obtenerClientePorId(receta.getIdCliente());
            Producto producto = productoController.obtenerProductoPorId(receta.getIdProducto());
            IndicacionesModel indicacion = indicacionController.obtenerIndicacionPorId(receta.getIdIndicacion());

            tableModelRecetas.addRow(new Object[]{cliente.getCi(), cliente.getNombre(), cliente.getApellidos(), producto.getNombre(), receta.getCantidad(), receta.getFechaEmision(), indicacion.getIndicacion()});
        }
    }

    private void limpiarCampos() 
    {
        comboCliente.setSelectedIndex(0);
        comboProducto.setSelectedIndex(0);
        comboMedida.setSelectedIndex(0);
        comboIndicacion.setSelectedIndex(0);
        txtCantidad.setText("");
        tableModelProductos.setRowCount(0);
    }
    
    private void buscarRecetaPorId() 
    {
        try {
            String idRecetaStr = txtBuscarId.getText();
            if (idRecetaStr.isEmpty()) 
            {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un ID de receta.");
                return;
            }

            int idReceta = Integer.parseInt(idRecetaStr);

            RecetaModel receta = recetaController.obtenerRecetaPorId(idReceta);
            if (receta != null) 
            {
            	mostrarDetallesReceta(receta);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una receta con ese ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        }
    }

    private void mostrarDetallesReceta(RecetaModel receta) 
    {
        ClienteModel cliente = clienteController.obtenerClientePorId(receta.getIdCliente());
        Producto producto = productoController.obtenerProductoPorId(receta.getIdProducto());
        IndicacionesModel indicacion = indicacionController.obtenerIndicacionPorId(receta.getIdIndicacion());
        
        String detalles = "ID Receta: " + receta.getIdReceta() +
                          "\nCliente: " + cliente.getNombre() + " (" + cliente.getCi() + ")" +
                          "\nProducto: " + producto.getNombre() +
                          "\nCantidad: " + receta.getCantidad() +
                          "\nFecha: " + receta.getFechaEmision();
                          
        txtDetalleReceta.setText(detalles);
    }

    public static void main(String[] args) 
    {
        EventQueue.invokeLater(() -> {
            try {
                RecetaGUI window = new RecetaGUI();
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}