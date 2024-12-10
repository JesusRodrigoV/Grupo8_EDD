package com.farmacia.model;

import java.util.ArrayList;
import java.util.List;

public class Rol {
    private String nombre;
    private List<String> permisos;

    public Rol(String nombre) {
        this.nombre = nombre;
        this.permisos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getPermisos() {
        return permisos;
    }

    public void agregarPermiso(String permiso) {
        if (!permisos.contains(permiso)) {
            permisos.add(permiso);
        }
    }

    public boolean tienePermiso(String permiso) {
        return permisos.contains(permiso);
    }
}
