package Package;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistroProducto registroProducto = new RegistroProducto();
        GestionInventario gestionInventario = new GestionInventario(registroProducto.getInventarios());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar nuevo producto");
            System.out.println("2. Registrar existencias en inventario");
            System.out.println("3. Verificar stock de productos");
            System.out.println("4. Mostrar inventario completo");
            System.out.println("5. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese la descripción del producto: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Ingrese el precio del producto: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Ingrese el código de barras del producto: ");
                    String codigoBarras = scanner.nextLine();
                    System.out.print("Ingrese el stock inicial: ");
                    int stockInicial = scanner.nextInt();
                    System.out.print("Ingrese el stock mínimo: ");
                    int stockMinimo = scanner.nextInt();
                    System.out.print("Ingrese el stock máximo: ");
                    int stockMaximo = scanner.nextInt();
                    registroProducto.registrarNuevoProducto(nombre, descripcion, precio, codigoBarras, stockInicial, stockMinimo, stockMaximo);
                    break;

                case 2:
                    System.out.print("Ingrese el ID del producto para actualizar el inventario: ");
                    int idProducto = scanner.nextInt();
                    System.out.print("Ingrese la cantidad a agregar o restar del inventario: ");
                    int cantidad = scanner.nextInt();
                    gestionInventario.actualizarExistencia(idProducto, cantidad);
                    break;

                case 3:
                    gestionInventario.verificarStock();
                    break;

                case 4:
                    gestionInventario.mostrarInventario();
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
