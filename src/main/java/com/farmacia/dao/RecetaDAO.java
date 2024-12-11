package com.farmacia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.farmacia.model.RecetaModel;
import com.farmacia.util.DatabaseConnection;

public class RecetaDAO 
{
    private static final String INSERT_RECETA = "INSERT INTO recetas (id_cliente, id_producto, id_indicacion, id_medida, fecha_emision, cantidad) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_RECETAS = "SELECT * FROM recetas";
    private static final String SELECT_RECETA_BY_ID = "SELECT * FROM recetas WHERE id_receta = ?";

    
    private LinkedList<RecetaModel> listaRecetas = new LinkedList<>();

    public void registrarReceta(RecetaModel receta) 
    {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_RECETA)) {

            ps.setInt(1, receta.getIdCliente());
            ps.setInt(2, receta.getIdProducto());
            ps.setInt(4, receta.getIdIndicacion());
            ps.setInt(3, receta.getIdMedida());
            ps.setDate(5, new java.sql.Date(receta.getFechaEmision().getTime()));
            ps.setDouble(6, receta.getCantidad());

            ps.executeUpdate();

            listaRecetas.addLast(receta); // lista enlazada
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RecetaModel> obtenerRecetas() 
    {
        List<RecetaModel> recetas = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_RECETAS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) 
            {
            	RecetaModel receta = new RecetaModel(
                        rs.getInt("id_receta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_producto"),
                        rs.getInt("id_indicacion"),
                        rs.getInt("id_medida"),
                        rs.getDate("fecha_emision"),
                        rs.getDouble("cantidad")
                );
                recetas.add(receta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recetas;
    }
    
    public RecetaModel obtenerRecetaPorId(int idReceta) 
    {
        RecetaModel receta = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_RECETA_BY_ID)) {

            ps.setInt(1, idReceta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) 
                {
                    receta = new RecetaModel(
                        rs.getInt("id_receta"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_producto"),
                        rs.getInt("id_indicacion"),
                        rs.getInt("id_medida"),
                        rs.getDate("fecha_emision"),
                        rs.getDouble("cantidad")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receta;
    }

    public LinkedList<RecetaModel> obtenerRecetasEnCola() 
    {
        return listaRecetas;
    }
}