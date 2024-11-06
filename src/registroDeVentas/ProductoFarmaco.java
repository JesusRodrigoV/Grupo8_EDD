package registroDeVentas;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductoFarmaco {
	private Double dosis;
    private String material;
    private List<Efecto> efectos;

    public ProductoFarmaco(Long idProducto, String nombre, Integer estado, 
                          Date fechaProduccion, Date fechaVencimiento, 
                          Date fechaLlegada, Double dosis, String material) {
        this.dosis = dosis;
        this.material = material;
        this.efectos = new ArrayList<>();
    }
}
