package com.farmacia.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.sql.Date; 
import java.util.concurrent.atomic.AtomicInteger;

public class Producto {
    private static final AtomicInteger idCounter = new AtomicInteger(1); // Contador para IDs
    private static final String CODIGO_BARRAS_PREFIX = "PROD-"; // Prefijo para el código de barras

    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String codigoBarras;
    private Date fechaVencimiento;
    private int stock;
    private String numeroLote;

    public Producto(String nombre, String descripcion, double precio,String codigoBarras,  Date fechaVencimiento, int stock, String numeroLote) {
        this.id = idCounter.getAndIncrement(); // Generar un ID único
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigoBarras = codigoBarras; // Generar código de barras
        this.fechaVencimiento = fechaVencimiento;
        this.stock = stock;
        this.numeroLote = numeroLote;
    }

    private String generarCodigoBarras() {
        return CODIGO_BARRAS_PREFIX + id; // Generar código de barras basado en el ID
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

    public int getDiasParaVencimiento() {
        if (fechaVencimiento == null) {
            throw new IllegalStateException("La fecha de vencimiento no está definida.");
        }
        LocalDate fechaVenc = fechaVencimiento.toLocalDate();
        LocalDate fechaActual = LocalDate.now();
        return (int) ChronoUnit.DAYS.between(fechaActual, fechaVenc);
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
                + ", codigoBarras=" + codigoBarras + ", stock=" + stock + ", numeroLote=" + numeroLote + "]";
    }
}