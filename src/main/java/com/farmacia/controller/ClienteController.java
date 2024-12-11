package com.farmacia.controller;

import java.util.List;

import com.farmacia.dao.ClienteDAO;
import com.farmacia.model.ClienteModel;

public class ClienteController 
{
    private ClienteDAO clienteDAO;

    public ClienteController() 
    {
        clienteDAO = new ClienteDAO();
    }

    public void registrarCliente(ClienteModel cliente) 
    {
        clienteDAO.registrarCliente(cliente);
    }

    public List<ClienteModel> obtenerClientes() 
    {
        return clienteDAO.obtenerClientes();
    }
    
    public ClienteModel obtenerClientePorId(int idCliente) 
    {
        return clienteDAO.obtenerClientePorId(idCliente);
    }

    public ClienteModel buscarClientePorId(int idCliente) 
    {
        return clienteDAO.obtenerClientePorId(idCliente);
    }
}
