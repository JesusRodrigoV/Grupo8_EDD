package com.farmacia.model;

public class ClienteModel 
{
	private int id_cliente;
	private String ci;
	private String nombre;
	private String apellidos;
	private String telefono;
	
	public ClienteModel(int id_cliente, String ci, String nombre, String apellidos, String telefono) 
	{
		this.id_cliente = id_cliente;
		this.ci = ci;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
	}

	public int getId_cliente() 
	{
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) 
	{
		this.id_cliente = id_cliente;
	}

	public String getCi() 
	{
		return ci;
	}

	public void setCi(String ci) 
	{
		this.ci = ci;
	}
	
	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellidos() 
	{
		return apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}

	public String getTelefono() 
	{
		return telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}
	
	@Override
	public String toString() 
	{
	    return ci + " - " + nombre + " " + apellidos;
	}
}
