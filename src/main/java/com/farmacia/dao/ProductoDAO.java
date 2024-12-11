package com.farmacia.dao;

import com.farmacia.model.Producto;
import com.farmacia.util.GConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static Connection connection;

    public ProductoDAO() {
        ProductoDAO.connection = GConexion.establecerConexion();
    }

    public void registrarProducto(Producto producto) {
  
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
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM producto";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id_producto"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDouble("precio"),
                    rs.getInt("cantidadEnStock"),
                    rs.getString("fecha_produccion"),
                    rs.getInt("numeroDeLote")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
