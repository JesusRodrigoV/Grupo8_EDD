package Package;

import java.util.List;

public class GestionInventario {
    private List<Inventario> inventarios;

    public GestionInventario(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public void actualizarExistencia(int idProducto, int cantidad) {
        Inventario inventario = buscarInventarioPorProductoId(idProducto);
        if (inventario != null) {
            inventario.registrarExistencia(cantidad);
            System.out.println("Stock actualizado. Stock actual de " + inventario.getProducto().getNombre() + ": " + inventario.getStockActual());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void verificarStock() {
        for (Inventario inv : inventarios) {
            if (inv.verificarStock()) {
                System.out.println("Alerta: El producto " + inv.getProducto().getNombre() + " está por agotarse.");
            } else {
                System.out.println("El producto " + inv.getProducto().getNombre() + " tiene suficiente stock.");
            }
        }
    }

    public void mostrarInventario() {
        System.out.println("\nInventario completo:");
        for (Inventario inv : inventarios) {
            System.out.println("Producto: " + inv.getProducto().getNombre() +
                               ", Stock actual: " + inv.getStockActual() +
                               ", Stock mínimo: " + inv.getStockMinimo());
        }
    }

    private Inventario buscarInventarioPorProductoId(int idProducto) {
        for (Inventario inv : inventarios) {
            if (inv.getProducto().getId() == idProducto) {
                return inv;
            }
        }
        return null;
    }
}
