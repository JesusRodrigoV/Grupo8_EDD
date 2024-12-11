package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.farmacia.model.IndicacionesModel;
import com.farmacia.util.DatabaseConnection;

public class IndicacionesDAO 
{

    private static final String INSERT_INDICACION = "INSERT INTO indicaciones (indicacion) VALUES (?)";
    private static final String SELECT_ALL_INDICACIONES = "SELECT * FROM indicaciones";
    private static final String UPDATE_INDICACION = "UPDATE indicaciones SET indicacion = ? WHERE id_indicacion = ?";
    private static final String DELETE_INDICACION = "DELETE FROM indicaciones WHERE id_indicacion = ?";

    public void registrarIndicacion(IndicacionesModel indicacion) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_INDICACION)) {

            ps.setString(1, indicacion.getIndicacion());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<IndicacionesModel> obtenerIndicaciones() 
    {
        List<IndicacionesModel> indicaciones = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_INDICACIONES);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) 
            {
            	IndicacionesModel indicacion = new IndicacionesModel(
                        rs.getInt("id_indicacion"),
                        rs.getString("indicacion")
                );
                indicaciones.add(indicacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indicaciones;
    }

    public IndicacionesModel obtenerIndicacionPorId(int idIndicacion) 
    {
        String query = "SELECT id_indicacion, indicacion FROM indicaciones WHERE id_indicacion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idIndicacion);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) 
                {
                    return new IndicacionesModel(
                            rs.getInt("id_indicacion"),
                            rs.getString("indicacion")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void editarIndicacion(IndicacionesModel indicacion) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_INDICACION)) {

            ps.setString(1, indicacion.getIndicacion());
            ps.setInt(2, indicacion.getIdIndicacion());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarIndicacion(int idIndicacion) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_INDICACION)) {

            ps.setInt(1, idIndicacion);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
