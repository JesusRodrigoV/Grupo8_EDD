package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.model.ClienteModel;
import com.farmacia.util.DatabaseConnection;

public class ClienteDAO 
{

    private static final String INSERT_CLIENTE = "INSERT INTO CLIENTES (ci, nombre, apellidos, telefono) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_CLIENTES = "SELECT * FROM CLIENTES";
    private static final String SELECT_CLIENTE_BY_ID = "SELECT * FROM CLIENTES WHERE id_cliente = ?";

    public void registrarCliente(ClienteModel cliente) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_CLIENTE)) 
        {

            ps.setString(1, cliente.getCi());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getTelefono());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ClienteModel> obtenerClientes() 
    {
        List<ClienteModel> clientes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_CLIENTES);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) 
            {
                ClienteModel cliente = new ClienteModel(
                        rs.getInt("id_cliente"),
                        rs.getString("ci"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public ClienteModel obtenerClientePorId(int idCliente) 
    {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CLIENTE_BY_ID)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) 
                {
                    return new ClienteModel(
                        rs.getInt("id_cliente"),
                        rs.getString("ci"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
