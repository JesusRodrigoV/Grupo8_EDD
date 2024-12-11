package com.farmacia.dao;

import com.farmacia.model.*;
import com.farmacia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaDAO {

    public boolean registrarVenta(VentaModel venta) {
        String sql = "INSERT INTO ventas (id_cliente, cantidad, monto, fecha_venta) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, venta.getIdCliente());
            stmt.setInt(2, venta.getCantidad());
            stmt.setDouble(3, venta.getTotal());
            Date fecha = Date.valueOf(venta.getFecha());  
            stmt.setDate(4, fecha);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
