package com.farmacia.model;

import java.util.Date;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String codigoBarras;
    private Date fechaVencimiento;
    private int stock;
    private String numeroLote;

    public Producto(String nombre, String descripcion, double precio, String codigoBarras,
                    Date fechaVencimiento, int stock, String numeroLote) {
        this.id = generarId(codigoBarras);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
        this.numeroLote = numeroLote;
    }

    private int generarId(String codigoBarras) {
        return codigoBarras.hashCode();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getStock() {
        return stock;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
                + ", codigoBarras=" + codigoBarras + ", stock=" + stock + ", numeroLote=" + numeroLote + "]";
    }
}
