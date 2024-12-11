package com.farmacia.controller;

import com.farmacia.dao.MedidasDAO;
import com.farmacia.model.MedidasModel;

import java.util.List;

public class MedidasController 
{
    private MedidasDAO medidasDAO;

    public MedidasController() 
    {
        medidasDAO = new MedidasDAO();
    }

    public void registrarMedida(MedidasModel medida) 
    {
        medidasDAO.registrarMedida(medida);
    }

    public List<MedidasModel> obtenerMedidas() 
    {
        return medidasDAO.obtenerMedidas();
    }

    public MedidasModel obtenerMedidaPorId(int idMedida) 
    {
        return medidasDAO.obtenerMedidaPorId(idMedida);
    }

    public boolean actualizarMedida(MedidasModel medida) 
    {
        return medidasDAO.actualizarMedida(medida);
    }

    public boolean eliminarMedida(int idMedida) 
    {
        return medidasDAO.eliminarMedida(idMedida);
    }
}
