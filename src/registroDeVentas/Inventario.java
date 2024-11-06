package registroDeVentas;

public class Inventario {
    private Long idInventario;
    private Integer cantidad;
    private Long idProducto;
    private Long idSucursal;

    public Inventario(Long idInventario, Integer cantidad, Long idProducto, Long idSucursal) {
        this.idInventario = idInventario;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
    }

    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Long idSucursal) {
        this.idSucursal = idSucursal;
    }

}
