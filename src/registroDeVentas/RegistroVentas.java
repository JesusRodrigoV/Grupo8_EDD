package registroDeVentas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroVentas {
    private Map<Long, Producto> productos;
    private Map<Long, Inventario> inventarioPorSucursal;
    private List<Venta> ventas;
    
    public RegistroVentas() {
        this.productos = new HashMap<>();
        this.inventarioPorSucursal = new HashMap<>();
        this.ventas = new ArrayList<>();
    }
    
    // Verificar disponibilidad de producto en una sucursal específica
    public boolean verificarDisponibilidad(Long idProducto, Long idSucursal, int cantidad) {
        return inventarioPorSucursal.values().stream()
                .filter(inv -> inv.getIdProducto().equals(idProducto) 
                        && inv.getIdSucursal().equals(idSucursal))
                .anyMatch(inv -> inv.getCantidad() >= cantidad);
    }
    
    // Registrar una nueva venta
    public Venta registrarVenta(Double importe, Integer cantidad, Long idSucursal, 
                               Long idProducto, Long idEmpleado, Long idCliente) {
        // Verificar disponibilidad
        if (!verificarDisponibilidad(idProducto, idSucursal, cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente en la sucursal");
        }
        
        // Actualizar inventario
        actualizarInventario(idProducto, idSucursal, cantidad);
        
        // Crear y registrar la venta
        Venta venta = new Venta(
            generateId(), importe, new Date(System.currentTimeMillis()), cantidad, 
            idSucursal, idProducto, idEmpleado, idCliente
        );
        ventas.add(venta);
        
        return venta;
    }
    
    // Obtener historial de ventas por producto y sucursal
    public List<Venta> obtenerHistorialVentas(Long idProducto, Long idSucursal) {
        return ventas.stream()
                .filter(v -> v.getIdProducto().equals(idProducto) 
                        && v.getIdSucursal().equals(idSucursal))
                .collect(Collectors.toList());
    }
    
    // Analizar rotación de producto por sucursal
    public Map<String, Object> analizarRotacionProducto(Long idProducto, Long idSucursal) {
        List<Venta> ventasProducto = obtenerHistorialVentas(idProducto, idSucursal);
        
        Map<String, Object> analisis = new HashMap<>();
        analisis.put("totalVentas", ventasProducto.size());
        analisis.put("cantidadTotal", ventasProducto.stream()
                .mapToInt(Venta::getCantidad).sum());
        analisis.put("importeTotal", ventasProducto.stream()
                .mapToDouble(Venta::getImporte).sum());
        
        return analisis;
    }
    
    private void actualizarInventario(Long idProducto, Long idSucursal, int cantidadVendida) {
        inventarioPorSucursal.values().stream()
                .filter(inv -> inv.getIdProducto().equals(idProducto) 
                        && inv.getIdSucursal().equals(idSucursal))
                .findFirst()
                .ifPresent(inv -> inv.setCantidad(inv.getCantidad() - cantidadVendida));
    }
    
    private Long generateId() {
        return System.currentTimeMillis();
    }
}
