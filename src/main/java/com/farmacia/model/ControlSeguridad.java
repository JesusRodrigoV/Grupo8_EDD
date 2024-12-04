package com.farmacia.model;

import javax.persistence.Entity;

@Entity
public class ControlSeguridad {
    public static void checkAccess(Usuario user, String reqPermiso) {
        if (user.conPermiso(reqPermiso)) {
            System.out.println("Acceso a " + reqPermiso);
        } else {
            System.out.println("Acceso a " + reqPermiso);
        }
    }
}
