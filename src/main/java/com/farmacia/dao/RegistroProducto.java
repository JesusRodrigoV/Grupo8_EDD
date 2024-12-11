package com.farmacia.dao;

import java.sql.Date; 
import java.util.HashMap;
import com.farmacia.model.Producto;
public class RegistroProducto {
    private HashMap<Integer, Producto> productos;

    public RegistroProducto() {
        this.productos = new HashMap<>();
    }

    // Registrar un nuevo producto
    public void registrarNuevoProducto(String nombre, String descripcion, double precio, String codigoBarras,
            Date fechaVencimiento, int stock, String numeroLote) {
		Producto nuevoProducto = new Producto(nombre, descripcion, precio, codigoBarras, fechaVencimiento, stock, numeroLote);
		
		productos.put(nuevoProducto.getId(), nuevoProducto); 
	}



    // Buscar un producto por código de barras
    public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de barras no puede estar vacío.");
        }
        int id = codigoBarras.trim().hashCode(); 
        return productos.get(id); 
    }


    public static void main(String[] args) {
        RegistroProducto registro = new RegistroProducto();
        registro.registrarNuevoProducto(
            "Producto A", 
            "Descripción A", 
            10.5, 
            "123456789", 
            new java.sql.Date(System.currentTimeMillis()), 
            50, 
            "Lote001" 
        );

        Producto encontrado = registro.buscarProductoPorCodigoBarras("123456789");

        if (encontrado != null) {
            System.out.println("Producto encontrado: " + encontrado.getNombre());
            System.out.println("Descripción: " + encontrado.getDescripcion());
            System.out.println("Precio: " + encontrado.getPrecio());
            System.out.println("Stock: " + encontrado.getStock());
            System.out.println("Número de Lote: " + encontrado.getNumeroLote());
        } else {
            System.out.println("Error: Producto no encontrado");
        }
    }

}

