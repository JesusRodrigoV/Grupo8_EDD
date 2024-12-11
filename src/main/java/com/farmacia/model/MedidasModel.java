package com.farmacia.model;

public class MedidasModel 
{
	private int id_medida;
	private String medida;
	
	public MedidasModel(int id_medida, String medida) 
	{
		this.id_medida = id_medida;
		this.medida = medida;
	}

	public int getId_medida() 
	{
		return id_medida;
	}

	public void setId_medida(int id_medida) 
	{
		this.id_medida = id_medida;
	}

	public String getMedida() 
	{
		return medida;
	}

	public void setMedida(String medida) 
	{
		this.medida = medida;
	}
	
	@Override
	public String toString() 
	{
	    return medida;
	}
}
