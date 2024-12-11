package com.farmacia.dao;

import com.farmacia.model.Producto;
import com.farmacia.util.GConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoDAO {

    private static Connection connection;
    private static Map<Integer, Producto> productosMap;

    public ProductoDAO() {
        ProductoDAO.connection = GConexion.establecerConexion();
        productosMap = new HashMap<>();
        cargarProductosDesdeDB(); 
    }

    private void cargarProductosDesdeDB() {
        String query = "SELECT * FROM producto";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descipcion"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidadEnStock"),
                    rs.getString("fecha_produccion"),
                    rs.getInt("numeroDeLote")
                );
                productosMap.put(producto.getId(), producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registrarProducto(Producto producto) {
        if (!productosMap.containsKey(producto.getId())) {
            productosMap.put(producto.getId(), producto);

            String query = "INSERT INTO producto (id_producto, nombre, precio, descipcion, fecha_produccion, cantidadEnStock, numeroDeLote) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(query)) {
                pstmt.setInt(1, producto.getId());
                pstmt.setString(2, producto.getNombre());
                pstmt.setDouble(3, producto.getPrecio());
                pstmt.setString(4, producto.getDescripcion());
                pstmt.setString(5, producto.getFechaDeVencimiento());
                pstmt.setInt(6, producto.getCantidadEnStock());
                pstmt.setInt(7, producto.getNumeroDeLote());
                pstmt.executeUpdate();
                System.out.println("El producto '" + producto.getNombre() + "' se añadió correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El producto con ID '" + producto.getId() + "' ya existe");
        }
    }

    public List<Producto> obtenerProductos() {
        return new ArrayList<>(productosMap.values());
    }

    public List<Producto> buscarProductosPorNombre(String nombre) {
        List<Producto> productos = new ArrayList<>();
        for (Producto producto : productosMap.values()) {
            if (producto.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                productos.add(producto);
            }
        }
        return productos;
    }
}
