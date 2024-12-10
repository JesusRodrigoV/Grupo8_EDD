package com.farmacia.dao;

import com.farmacia.model.Producto;
import com.farmacia.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String INSERT_PRODUCTO = "INSERT INTO producto (id_producto, nombre, precio, descripcion, fecha_vencimiento, cod_barras, stock, numero_lote) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PRODUCTOS = "SELECT * FROM producto";

    public void registrarProducto(Producto producto) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCTO)) {

            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setDate(5, new java.sql.Date(producto.getFechaVencimiento().getTime()));
            ps.setString(6, producto.getCodigoBarras());
            ps.setInt(7, producto.getStock());
            ps.setString(8, producto.getNumeroLote());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PRODUCTOS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getString("cod_barras"),
                        rs.getDate("fecha_vencimiento"),
                        rs.getInt("stock"),
                        rs.getString("numero_lote")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
    
    public Producto obtenerProductoPorCodigoBarras(String codigoBarras) {
        String query = "SELECT id_producto, nombre, precio, descripcion, fecha_vencimiento, cod_barras, stock, numero_lote " +
                       "FROM producto WHERE cod_barras = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigoBarras);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getString("cod_barras"),
                        rs.getDate("fecha_vencimiento"),
                        rs.getInt("stock"),
                        rs.getString("numero_lote")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }

}
