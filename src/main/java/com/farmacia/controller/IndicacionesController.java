package com.farmacia.controller;

import java.util.List;

import com.farmacia.dao.IndicacionesDAO;
import com.farmacia.model.IndicacionesModel;

public class IndicacionesController 
{
    private IndicacionesDAO indicacionesDAO;

    public IndicacionesController() 
    {
        indicacionesDAO = new IndicacionesDAO();
    }

    public void registrarIndicacion(IndicacionesModel indicacion) 
    {
        indicacionesDAO.registrarIndicacion(indicacion);
    }

    public List<IndicacionesModel> obtenerIndicaciones() 
    {
        return indicacionesDAO.obtenerIndicaciones();
    }

    public IndicacionesModel obtenerIndicacionPorId(int idIndicacion) 
    {
        return indicacionesDAO.obtenerIndicacionPorId(idIndicacion);
    }

    public void editarIndicacion(IndicacionesModel indicacion) 
    {
        indicacionesDAO.editarIndicacion(indicacion);
    }

    public void eliminarIndicacion(int idIndicacion) 
    {
        indicacionesDAO.eliminarIndicacion(idIndicacion);
    }
}