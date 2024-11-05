package Package;

public class RecetaMedica {
    private int numReceta;
    private String nombrePaciente;
    private String medicamentos;
    private boolean esAutentica;
    private boolean estaDispensada;

    public RecetaMedica(int numReceta, String nombrePaciente, String medicamentos, boolean esAutentica, boolean estaDispensada) {
        this.numReceta = numReceta;
        this.nombrePaciente = nombrePaciente;
        this.medicamentos = medicamentos;
        this.esAutentica = esAutentica;
        this.estaDispensada = estaDispensada;
    }

    public int getNumReceta() {
        return numReceta;
    }

    public void setNumReceta(int numReceta) {
        this.numReceta = numReceta;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public boolean esAutentica() {
        return esAutentica;
    }

    public void setEsAutentica(boolean esAutentica) {
        this.esAutentica = esAutentica;
    }

    public boolean estaDispensada() {
        return estaDispensada;
    }

    public void setEstaDispensada(boolean estaDispensada) {
        this.estaDispensada = estaDispensada;
    }
}