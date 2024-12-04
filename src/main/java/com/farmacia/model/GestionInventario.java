package com.farmacia.model;
import java.util.HashMap;

public class GestionInventario {
    private HashMap<Integer, Inventario> inventarios;

    public GestionInventario() {
        this.inventarios = new HashMap<>();
    }

    // Agregar un nuevo inventario al sistema
    public void agregarInventario(Inventario inventario) {
        inventarios.put(inventario.getProducto().getId(), inventario);
    }

    // Actualizar la cantidad de existencias para un producto dado
    public void actualizarExistencia(int idProducto, int cantidad) {
        Inventario inventario = inventarios.get(idProducto);
        if (inventario != null) {
            int nuevoStock = inventario.getStockActual() + cantidad;

            // Validar límites de stock
            if (nuevoStock < 0) {
                System.out.println("Error: No se puede reducir el stock por debajo de 0.");
            } else if (nuevoStock > inventario.getStockMaximo()) {
                System.out.println("Aviso: El stock excede el máximo permitido. Ajustando al límite máximo.");
                inventario.registrarExistencia(inventario.getStockMaximo() - inventario.getStockActual());
            } else {
                inventario.registrarExistencia(cantidad);
                System.out.println("Stock actualizado. Stock actual de " + inventario.getProducto().getNombre() + ": " + inventario.getStockActual());
            }
        } else {
            System.out.println("Error: Producto no encontrado en el inventario.");
        }
    }

    // Verificar el estado del stock para todos los productos
    public void verificarStock() {
        System.out.println("\nEstado del stock:");
        for (Inventario inv : inventarios.values()) {
            if (inv.getStockActual() < inv.getStockMinimo()) {
                System.out.println("⚠️ Alerta: El producto " + inv.getProducto().getNombre() + " está por agotarse.");
            } else {
                System.out.println("✔️ El producto " + inv.getProducto().getNombre() + " tiene suficiente stock.");
            }
        }
    }

    // Mostrar el inventario completo con todos los detalles
    public void mostrarInventario() {
        System.out.println("\nInventario completo:");
        for (Inventario inv : inventarios.values()) {
            System.out.println("Producto: " + inv.getProducto().getNombre() +
                               ", Stock actual: " + inv.getStockActual() +
                               ", Stock mínimo: " + inv.getStockMinimo() +
                               ", Stock máximo: " + inv.getStockMaximo());
        }
    }

    // Obtener el inventario completo como HashMap
    public HashMap<Integer, Inventario> getInventarios() {
        return inventarios;
    }

    // Buscar inventario por ID de producto
    public Inventario buscarInventarioPorProductoId(int idProducto) {
        return inventarios.get(idProducto);
    }
}
