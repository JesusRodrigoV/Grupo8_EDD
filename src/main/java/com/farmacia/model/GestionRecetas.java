package com.farmacia.model;

import java.util.LinkedList;

public class GestionRecetas {
    private LinkedList<RecetaMedica> listaRecetas;

    public GestionRecetas() {
        this.listaRecetas = new LinkedList<>();
    }

    public void agregarReceta(RecetaMedica receta) {
        listaRecetas.add(receta);
    }

    public void eliminarReceta(int numReceta) {
        for (RecetaMedica receta : listaRecetas) {
            if (receta.getNumReceta() == numReceta) {
                listaRecetas.remove(receta);
                break;
            }
        }
    }

    public boolean verificarAutenticidad(int numReceta) {
        for (RecetaMedica receta : listaRecetas) {
            if (receta.getNumReceta() == numReceta) {
                return receta.esAutentica();
            }
        }
        return false;
    }

    public void registrarDispensacion(int numReceta) {
        for (RecetaMedica receta : listaRecetas) {
            if (receta.getNumReceta() == numReceta) {
                receta.setEstaDispensada(true);
                break;
            }
        }
    }

    public boolean estaEnRevision(int numReceta) {
        for (RecetaMedica receta : listaRecetas) {
            if (receta.getNumReceta() == numReceta) {
                return !receta.estaDispensada();
            }
        }
        return false;
    }
}
