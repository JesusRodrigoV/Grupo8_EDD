package registroDeVentas;

public class Efecto {
    private int idEfecto;
    private String descripcion;
    private String advertencia;

    public Efecto() {}

    public Efecto(int idEfecto, String descripcion, String advertencia) {
        this.idEfecto = idEfecto;
        this.descripcion = descripcion;
        this.advertencia = advertencia;
    }

    public int getIdEfecto() {
        return idEfecto;
    }

    public void setIdEfecto(int idEfecto) {
        this.idEfecto = idEfecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAdvertencia() {
        return advertencia;
    }

    public void setAdvertencia(String advertencia) {
        this.advertencia = advertencia;
    }

    @Override
    public String toString() {
        return "Efecto{" +
                "idEfecto=" + idEfecto +
                ", descripcion='" + descripcion + '\'' +
                ", advertencia='" + advertencia + '\'' +
                '}';
    }
}
