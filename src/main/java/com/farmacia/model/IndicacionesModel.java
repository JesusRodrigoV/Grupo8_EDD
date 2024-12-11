package com.farmacia.model;

public class IndicacionesModel 
{
    private int idIndicacion;
    private String indicacion;

    public IndicacionesModel(int idIndicacion, String indicacion) 
    {
        this.idIndicacion = idIndicacion;
        this.indicacion = indicacion;
    }

    public IndicacionesModel(String indicacion) 
    {
        this.indicacion = indicacion;
    }

    public int getIdIndicacion() 
    {
        return idIndicacion;
    }

    public void setIdIndicacion(int idIndicacion) 
    {
        this.idIndicacion = idIndicacion;
    }

    public String getIndicacion() 
    {
        return indicacion;
    }

    public void setIndicacion(String indicacion) 
    {
        this.indicacion = indicacion;
    }
    
    @Override
	public String toString() 
	{
	    return indicacion;
	}
}