package com.farmacia.model;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String codigoBarras;
    
    // Constructor modificado para usar el código como base del ID
    public Producto(String nombre, String descripcion, double precio, String codigoBarras) {
        this.id = generarId(codigoBarras);  // Generar el id a partir del código
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigoBarras = codigoBarras;
    }

    // Generar un id único, por ejemplo, basado en el código de barras
    private int generarId(String codigoBarras) {
        return codigoBarras.hashCode();  // Genera un hash del código de barras para obtener un id único
    }

    // Métodos de acceso
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

    // Método para mostrar información del producto
    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
                + ", codigoBarras=" + codigoBarras + "]";
    }
}
