package com.farmacia.controller;

import java.util.List;

import com.farmacia.dao.RecetaDAO;
import com.farmacia.model.RecetaModel;

public class RecetaController 
{
    private RecetaDAO recetaDAO;

    public RecetaController() 
    {
        recetaDAO = new RecetaDAO();
    }

    public void registrarReceta(RecetaModel receta) 
    {
        recetaDAO.registrarReceta(receta);
    }

    public List<RecetaModel> obtenerRecetas() 
    {
        return recetaDAO.obtenerRecetas();
    }

    public List<RecetaModel> obtenerRecetasEnCola() 
    {
        return recetaDAO.obtenerRecetasEnCola();
    }

    public RecetaModel obtenerRecetaPorId(int idReceta) 
    {
        return recetaDAO.obtenerRecetaPorId(idReceta);
    }
}