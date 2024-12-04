package com.farmacia.controller;

import com.farmacia.dao.*;
import com.farmacia.model.Producto;

import java.util.List;

public class ProductoBean {
    private Producto producto = new Producto();
    private ProductoDAO productoDAO = new ProductoDAO();

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void registrarProducto() {
        productoDAO.registrarProducto(producto);
    }

    public List<Producto> obtenerProductos() {
        return productoDAO.obtenerProductos();
    }
}
