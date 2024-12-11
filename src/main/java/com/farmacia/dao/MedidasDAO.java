package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.model.MedidasModel;
import com.farmacia.util.DatabaseConnection;

public class MedidasDAO 
{
    private static final String INSERT_MEDIDA = "INSERT INTO medidas (medida) VALUES (?)";
    private static final String SELECT_ALL_MEDIDAS = "SELECT * FROM medidas";
    private static final String UPDATE_MEDIDA = "UPDATE medidas SET medida = ? WHERE id_medida = ?";
    private static final String DELETE_MEDIDA = "DELETE FROM medidas WHERE id_medida = ?";

    public void registrarMedida(MedidasModel medida) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_MEDIDA)) {

            ps.setString(1, medida.getMedida());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<MedidasModel> obtenerMedidas() 
    {
        List<MedidasModel> medidas = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_MEDIDAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) 
            {
                MedidasModel medida = new MedidasModel(
                        rs.getInt("id_medida"),
                        rs.getString("medida")
                );
                medidas.add(medida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medidas;
    }

    public MedidasModel obtenerMedidaPorId(int idMedida) 
    {
        String query = "SELECT id_medida, medida FROM medidas WHERE id_medida = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idMedida);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) 
                {
                    return new MedidasModel(
                        rs.getInt("id_medida"),
                        rs.getString("medida")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean actualizarMedida(MedidasModel medida) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_MEDIDA)) {

            ps.setString(1, medida.getMedida());
            ps.setInt(2, medida.getId_medida());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarMedida(int idMedida) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_MEDIDA)) {

            ps.setInt(1, idMedida);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
