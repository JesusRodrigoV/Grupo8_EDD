package Package;

import java.util.ArrayList;
import java.util.List;

public class RegistroProducto {
    private List<Producto> productos;
    private List<Inventario> inventarios;

    public RegistroProducto() {
        this.productos = new ArrayList<>();
        this.inventarios = new ArrayList<>();
    }

    public void registrarNuevoProducto(String nombre, String descripcion, double precio, String codigoBarras, int stockInicial, int stockMinimo, int stockMaximo) {
        Producto nuevoProducto = new Producto(productos.size() + 1, nombre, descripcion, precio, codigoBarras);
        productos.add(nuevoProducto);

        Inventario nuevoInventario = new Inventario(nuevoProducto, stockInicial, stockMinimo, stockMaximo);
        inventarios.add(nuevoInventario);

        System.out.println("Producto y su inventario registrado exitosamente.");
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public Inventario buscarInventarioPorProductoId(int idProducto) {
        for (Inventario inv : inventarios) {
            if (inv.getProducto().getId() == idProducto) {
                return inv;
            }
        }
        return null;
    }
}
