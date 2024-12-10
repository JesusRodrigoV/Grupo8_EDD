package com.farmacia.model;

import java.util.HashSet;
import java.util.Set;

public class Rol {
	private String name;
	private Set<String> permisos;
	
	public Rol(String name) {
		this.name = name;
		this.permisos = new HashSet<>();
	}
	
	public String getName() {
		return name;
	}
	public void agregarPermiso(String permission) {
		permisos.add(permission);
    }

	 public boolean conPermiso(String permission) {
	        return permisos.contains(permission);
	    }
	
}
