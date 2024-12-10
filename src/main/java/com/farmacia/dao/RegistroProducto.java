package com.farmacia.dao;

import java.util.HashMap;
import com.farmacia.model.Producto;
public class RegistroProducto {
    private HashMap<Integer, Producto> productos;

    public RegistroProducto() {
        this.productos = new HashMap<>();
    }

    // Registrar un nuevo producto
    public Producto registrarNuevoProducto(String nombre, String descripcion, double precio, String codigoBarras) {
        Producto nuevoProducto = new Producto(nombre, descripcion, precio, codigoBarras.trim());
        productos.put(nuevoProducto.getId(), nuevoProducto); // Clave: ID generado del código de barras
        return nuevoProducto;
    }

    // Buscar un producto por código de barras
    public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
        int id = new Producto("", "", 0, codigoBarras.trim()).getId(); // Generar ID basado en el código ingresado
        return productos.get(id);
    }

    public static void main(String[] args) {
        RegistroProducto registro = new RegistroProducto();

        // Registrar un producto
        registro.registrarNuevoProducto("Producto A", "Descripción A", 10.5, "123456789");

        // Buscar el producto por código de barras
        Producto encontrado = registro.buscarProductoPorCodigoBarras("123456789");

        if (encontrado != null) {
            System.out.println("Producto encontrado: " + encontrado.getNombre());
        } else {
            System.out.println("Error: Producto no encontrado");
        }
    }
}

