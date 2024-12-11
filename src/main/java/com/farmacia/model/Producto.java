package com.farmacia.model;

<<<<<<< HEAD
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.sql.Date; 
import java.util.concurrent.atomic.AtomicInteger;
=======
import java.util.Objects;
import java.util.Random;
>>>>>>> 3767f359ad7dba2ba215a35a4ba32ddb35217ba7

public class Producto {
    private static final AtomicInteger idCounter = new AtomicInteger(1); // Contador para IDs
    private static final String CODIGO_BARRAS_PREFIX = "PROD-"; // Prefijo para el código de barras

    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadEnStock;
    private String fechaDeVencimiento;
    private int numeroDeLote;

<<<<<<< HEAD
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
=======

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
>>>>>>> 3767f359ad7dba2ba215a35a4ba32ddb35217ba7
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