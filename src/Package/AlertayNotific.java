package ejercicios3parcial;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

class Producto {
    private String nombre;
    private int cantidadEnStock;
    private int diasParaVencimiento;

    public Producto(String nombre, int cantidadEnStock, int diasParaVencimiento) {
        this.nombre = nombre;
        this.cantidadEnStock = cantidadEnStock;
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre + ", Cantidad en stock: " + cantidadEnStock +
               ", Días para vencimiento: " + diasParaVencimiento;
    }
}


class AlertaComparador implements Comparator<Producto> {
    @Override
    public int compare(Producto p1, Producto p2) {
        if (p1.getDiasParaVencimiento() != p2.getDiasParaVencimiento()) {
            return Integer.compare(p1.getDiasParaVencimiento(), p2.getDiasParaVencimiento());
        } else {
            return Integer.compare(p1.getCantidadEnStock(), p2.getCantidadEnStock());
        }
    }
}


class SistemaAlertas {
    private PriorityQueue<Producto> colaDeAlertas;

    public SistemaAlertas() {
      
        colaDeAlertas = new PriorityQueue<>(new AlertaComparador());
    }

   
    public void agregarAlerta(Producto producto) {
        colaDeAlertas.add(producto);
    }

    public void mostrarAlertas() {
        System.out.println("Alertas de Productos:");
        while (!colaDeAlertas.isEmpty()) {
            Producto producto = colaDeAlertas.poll();
            System.out.println("Alerta: " + producto);
        }
    }
}

public class AlertayNotific {
    public static void main(String[] args) {
        SistemaAlertas sistema = new SistemaAlertas();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la cantidad de productos a agregar: ");
        int cantidadProductos = scanner.nextInt();
        scanner.nextLine();  

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("\nProducto " + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Cantidad en stock: ");
            int cantidadEnStock = scanner.nextInt();

            System.out.print("Días para vencimiento: ");
            int diasParaVencimiento = scanner.nextInt();
            scanner.nextLine();  

            
            Producto producto = new Producto(nombre, cantidadEnStock, diasParaVencimiento);
            sistema.agregarAlerta(producto);
        }

   
        sistema.mostrarAlertas();
        
        scanner.close();
    }
}
