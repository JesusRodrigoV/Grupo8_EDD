package com.farmacia.model;

import java.util.List;
import java.util.PriorityQueue;

public class SistemaAlertas {
    private PriorityQueue<Producto> colaDeAlertas;

    public SistemaAlertas() {
        colaDeAlertas = new PriorityQueue<>(new AlertaComparador());
    }

    public void agregarAlerta(Producto producto) {
        colaDeAlertas.add(producto);
    }
    
    public PriorityQueue<Producto> getColaDeAlertas() {
		return colaDeAlertas;
	}

	public void procesarProductos(List<Producto> productos) {
        colaDeAlertas.clear(); 
        for (Producto producto : productos) {
            if (producto.getStock() < 10 || producto.getDiasParaVencimiento() <= 30) {
                colaDeAlertas.add(producto);
            }
        }
    }

    // Mostrar alertas sin vaciar la cola
    public String mostrarAlertas() {
        StringBuilder alertas = new StringBuilder("Alertas de Productos:\n");
        for (Producto producto : colaDeAlertas) {
            alertas.append("Producto: ").append(producto.getNombre())
                    .append(", Stock: ").append(producto.getStock())
                    .append(", Días para Vencimiento: ").append(producto.getDiasParaVencimiento())
                    .append("\n");
        }
        return alertas.toString();
    }
}
