package com.farmacia.model;

import java.util.HashMap;

public class ConfRoles {
    private HashMap<String, Rol> roles;

    public ConfRoles() {
        roles = new HashMap<>();
        configurarRoles();
    }

    private void configurarRoles() {
        Rol admin = new Rol("Administrador");
        admin.agregarPermiso("gestionar_usuarios");
        admin.agregarPermiso("ver_inventario");
        admin.agregarPermiso("gestionar_productos");

        Rol vendedor = new Rol("Vendedor");
        vendedor.agregarPermiso("ver_inventario");
        vendedor.agregarPermiso("registrar_venta");

        Rol farmaceutico = new Rol("Farmacéutico");
        farmaceutico.agregarPermiso("ver_inventario");
        farmaceutico.agregarPermiso("gestionar_recetas");

        roles.put(admin.getNombre(), admin);
        roles.put(vendedor.getNombre(), vendedor);
        roles.put(farmaceutico.getNombre(), farmaceutico);
    }

    public Rol obtenerRol(String nombreRol) {
        return roles.get(nombreRol);
    }
}
