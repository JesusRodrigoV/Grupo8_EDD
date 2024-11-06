package registroDeVentas;

import java.sql.Date;

public class Venta {
    private Long idVenta;
    private Double importe;
    private Date fecha;
    private Integer cantidad;
    private Long idSucursal;
    private Long idProducto;
    private Long idEmpleado;
    private Long idCliente;

    public Venta(Long idVenta, Double importe, Date fecha, Integer cantidad, 
                 Long idSucursal, Long idProducto, Long idEmpleado, Long idCliente) {
        this.idVenta = idVenta;
        this.importe = importe;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.idSucursal = idSucursal;
        this.idProducto = idProducto;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
    }

    // Getters
    public Long getIdVenta() { return idVenta; }
    public Double getImporte() { return importe; }
    public Date getFecha() { return fecha; }
    public Integer getCantidad() { return cantidad; }
    public Long getIdSucursal() { return idSucursal; }
    public Long getIdProducto() { return idProducto; }
}
