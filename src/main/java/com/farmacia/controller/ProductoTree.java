package com.farmacia.controller;

import java.util.TreeMap;

import com.farmacia.model.Producto;

public class ProductoTree {
    private TreeMap<String, Producto> productosPorNombre = new TreeMap<>();
    private TreeMap<String, Producto> productosPorCodigoBarras = new TreeMap<>();

    public void agregarProducto(Producto producto) {
        productosPorNombre.put(producto.getNombre(), producto);
        productosPorCodigoBarras.put(producto.getCodigoBarras(), producto);
    }

    public Producto buscarPorNombre(String nombre) {
        return productosPorNombre.get(nombre);
    }

    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return productosPorCodigoBarras.get(codigoBarras);
    }
}
