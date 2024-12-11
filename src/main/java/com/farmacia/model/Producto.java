package com.farmacia.model;

import java.util.Objects;
import java.util.Random;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadEnStock;
    private String fechaDeVencimiento;
    private int numeroDeLote;


    public Producto() {
        this.id = generarIdAleatorio();
    }


    public Producto(String nombre, String descripcion, double precio, int cantidadEnStock, String fechaDeVencimiento, int numeroDeLote) {
        this.id = generarIdAleatorio();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.numeroDeLote = numeroDeLote;
    }


    public Producto(int id, String nombre, String descripcion, double precio, int cantidadEnStock, String fechaDeVencimiento, int numeroDeLote) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadEnStock = cantidadEnStock;
        this.fechaDeVencimiento = fechaDeVencimiento;
        this.numeroDeLote = numeroDeLote;
    }

  
    private int generarIdAleatorio() {
        Random random = new Random();
        return random.nextInt(100000); 
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

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public int getNumeroDeLote() {
        return numeroDeLote;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    public void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public void setNumeroDeLote(int numeroDeLote) {
        this.numeroDeLote = numeroDeLote;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
                + ", cantidadEnStock=" + cantidadEnStock + ", fechaDeVencimiento=" + fechaDeVencimiento
                + ", numeroDeLote=" + numeroDeLote + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
