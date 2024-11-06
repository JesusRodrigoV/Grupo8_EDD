package registroDeVentas;

import java.sql.Date;

public class Producto {
	private Long idProducto;
    private String nombre;
    private Integer estado;
    private Date fechaProduccion;
    private Date fechaVencimiento;
    private Date fechaLlegada;
    private Long idProveedor;
    private Long idFarmaco;
    private Long idNoFarmaco;
    
    public Producto(Long idProducto, String nombre, Integer estado, Date fechaProduccion, 
            Date fechaVencimiento, Date fechaLlegada) {
 this.idProducto = idProducto;
 this.nombre = nombre;
 this.estado = estado;
 this.fechaProduccion = fechaProduccion;
 this.fechaVencimiento = fechaVencimiento;
 this.fechaLlegada = fechaLlegada;
}
    
    public Long getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public Integer getEstado() { return estado; }
    public Date getFechaVencimiento() { return fechaVencimiento; }

    public void setIdProducto(Long idProducto) {this.idProducto = idProducto;}
    public void setNombre(String nombre) {this.nombre = nombre;}public void setEstado(Integer estado) {this.estado = estado;}
    public void setFechaVencimiento(Date fechaVencimiento) {this.fechaVencimiento = fechaVencimiento;}
}
