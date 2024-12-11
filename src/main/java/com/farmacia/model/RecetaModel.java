package com.farmacia.model;

import java.util.Date;

public class RecetaModel 
{
    private int idReceta;
    private int idCliente;
    private int idProducto;
    private int idIndicacion;
    private int idMedida;
    private Date fechaEmision;
    private double cantidad;

    public RecetaModel(int idReceta, int idCliente, int idProducto, int idIndicacion, int idMedida, Date fechaEmision, double cantidad) 
    {
        this.idReceta = idReceta;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.idIndicacion = idIndicacion;
        this.idMedida = idMedida;
        this.fechaEmision = fechaEmision;
        this.cantidad = cantidad;
    }

    public int getIdReceta() 
    {
        return idReceta;
    }

    public void setIdReceta(int idReceta) 
    {
        this.idReceta = idReceta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) 
    {
        this.idCliente = idCliente;
    }

    public int getIdProducto() 
    {
        return idProducto;
    }

    public void setIdProducto(int idProducto) 
    {
        this.idProducto = idProducto;
    }

    public int getIdIndicacion() 
    {
        return idIndicacion;
    }

    public void setIdIndicacion(int idIndicacion) 
    {
        this.idIndicacion = idIndicacion;
    }

    public int getIdMedida() 
    {
        return idMedida;
    }

    public void setIdMedida(int idMedida) 
    {
        this.idMedida = idMedida;
    }

    public Date getFechaEmision() 
    {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) 
    {
        this.fechaEmision = fechaEmision;
    }

    public double getCantidad() 
    {
        return cantidad;
    }

    public void setCantidad(double cantidad) 
    {
        this.cantidad = cantidad;
    }
}