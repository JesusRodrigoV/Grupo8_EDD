package com.farmacia.model;

public class VentaModel {
    private int idVenta;
    private int idCliente;
    private int cantidad;
    private double total;
    private String fecha;

    public VentaModel(int idVenta, int idCliente, int cantidad, double total, String fecha) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public String getFecha() {
        return fecha;
    }
}
