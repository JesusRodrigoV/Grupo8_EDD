package com.farmacia.controller;

import com.farmacia.dao.VentaDAO;
import com.farmacia.model.VentaModel;

public class VentaController {
    private VentaDAO ventaDAO = new VentaDAO();

    public boolean registrarVenta(VentaModel venta) {
        return ventaDAO.registrarVenta(venta);
    }
}
