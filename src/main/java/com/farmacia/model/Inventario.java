package com.farmacia.model;


public class Inventario {
    private Producto producto;
    private int stockActual;
    private int stockMinimo;
    private int stockMaximo;

    public Inventario(Producto producto, int stockActual, int stockMinimo, int stockMaximo) {
        this.producto = producto;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public int getStockMaximo() {
        return stockMaximo;
    }

    // Método para actualizar el stock directamente
    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public void registrarExistencia(int cantidad) {
        stockActual += cantidad;
        if (stockActual > stockMaximo) {
            stockActual = stockMaximo;
        }
    }

    public boolean verificarStock() {
        return stockActual < stockMinimo;
    }
}
