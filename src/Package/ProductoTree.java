package Package;

public class ProductoTree {
    class NodoProducto {
        Producto producto;
        NodoProducto izquierda, derecha;

        public NodoProducto(Producto producto) {
            this.producto = producto;
            izquierda = derecha = null;
        }
    }

    private NodoProducto raiz;


    public ProductoTree() {
        raiz = null;
    }


    public void agregarProducto(Producto producto) {
        raiz = agregarRecursivo(raiz, producto);
    }

    private NodoProducto agregarRecursivo(NodoProducto actual, Producto producto) {
        if (actual == null) {
            return new NodoProducto(producto);
        }

        if (producto.getNombre().compareToIgnoreCase(actual.producto.getNombre()) < 0) {
            actual.izquierda = agregarRecursivo(actual.izquierda, producto);
        } else if (producto.getNombre().compareToIgnoreCase(actual.producto.getNombre()) > 0) {
            actual.derecha = agregarRecursivo(actual.derecha, producto);
        }
        return actual;
    }


    public Producto buscarProducto(String nombre) {
        return buscarRecursivo(raiz, nombre);
    }

    private Producto buscarRecursivo(NodoProducto actual, String nombre) {
        if (actual == null) {
            return null;
        }

        if (nombre.equalsIgnoreCase(actual.producto.getNombre())) {
            return actual.producto;
        }

        return nombre.compareToIgnoreCase(actual.producto.getNombre()) < 0
                ? buscarRecursivo(actual.izquierda, nombre)
                : buscarRecursivo(actual.derecha, nombre);
    }
}
