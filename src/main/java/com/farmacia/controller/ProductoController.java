package com.farmacia.controller;

import com.farmacia.dao.ProductoDAO;
import com.farmacia.dao.RegistroProducto;
import com.farmacia.model.Producto;

import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;
    private RegistroProducto registroProducto;

    public ProductoController() {
        productoDAO = new ProductoDAO();
        registroProducto = new RegistroProducto();
    }

    public void registrarProducto(Producto producto) {
        registroProducto.registrarNuevoProducto(
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getCodigoBarras(),
            producto.getFechaVencimiento(),
            producto.getStock(),
            producto.getNumeroLote()
        );
        productoDAO.registrarProducto(producto);
    }


    public List<Producto> obtenerProductos() {
        return productoDAO.obtenerProductos();
    }

    public Producto buscarProductoPorCodigoBarras(String codigoBarras) {
        Producto producto = registroProducto.buscarProductoPorCodigoBarras(codigoBarras);
        if (producto != null) {
            return producto;
        }
        return productoDAO.obtenerProductoPorCodigoBarras(codigoBarras);
    }
}
