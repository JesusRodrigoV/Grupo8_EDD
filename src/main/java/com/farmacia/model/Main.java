package com.farmacia.model;
import com.farmacia.controller.*;
import com.farmacia.dao.*;
import java.util.List;
import java.util.Scanner;

import com.farmacia.dao.RegistroProducto;

public class Main {
	/*
    public static void main(String[] args) {
    	Usuario userSesion = iniciarSesion();
    	int opc = 0;
    	Scanner scan = new Scanner (System.in);
    	do {
    		//;
    		
    		scan.nextLine();
    		menu();
    		System.out.println("Ingrese una opcion");
    		opc = scan.nextInt();
    		controlMenu(opc, userSesion);
    	}while(opc != 100);
    	/*
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
    public static void menu() {
    	 separar();
    	System.out.println("\tMenu\t");
    	separar();
    	System.out.println("\nSeleccione una opción:");
        System.out.println("1. Registrar nuevo producto");
        System.out.println("2. Registrar existencias en inventario");
        System.out.println("3. Verificar stock de productos");
        System.out.println("4. Mostrar inventario completo");
        System.out.println("5. Salir");
    }
    public static void controlMenu(int opc, Usuario user) {
    	confRoles algo = new confRoles();
    	switch(opc) {
		case 1:
			pruebaUsuarios();
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		case 90:
			algo.verUsuarios();
			break;
		case 91:
			crearUsuario();
			break;
		case 92:
			iniciarSesion();
			break;
		case 93:
			sesionActual(user);
			break;
		default:
			System.out.println("Error. Opción inválida. Menu");
			break;
	}
    }
    public static void separar() {
    	System.out.println("======================================\t");
    }
    public static void pruebaUsuarios() {
    	UsuarioDAO us = new UsuarioDAO();
    	List<Usuario> u = us.usuariosLista();
		for (Usuario user : u) {
			System.out.println(user.getUsuario());
		}
    }
    public static void crearUsuario() {
    	Scanner sc = new Scanner(System.in);
    	confRoles algo = new confRoles();
    	separar();
    	System.out.println("Crear usuario");
    	separar();
    	System.out.println("Usuario:");
    	String usuario = sc.nextLine(); 
    	System.out.println("Contraseña:");
    	String contrasena = sc.nextLine();
    	System.out.println("Rol:");
    	System.out.println("1.Farmaceutico\n2.Asistente\n3.Cajero");
    	int rol = sc.nextInt();
    	String elRol = "";
    	boolean crear = true;
    	switch (rol) {
		case 1:
			elRol = "Farmaceutico";
			break;
		case 2:
			elRol = "Asistente";
			break;
		case 3:
			elRol = "Cajero";
			break;
		default:
			System.out.println("Error, cuenta no creada");
			crear = false;
			break;
		}
    	if(crear) {
    		algo.verifNombre(usuario, contrasena, elRol);	
    	}
		
    }
    public static Usuario iniciarSesion() {
    	Scanner scan = new Scanner(System.in);
    	confRoles algo = new confRoles();
    	separar();
    	System.out.println("\tInicio de Sesion");
    	separar();
    	System.out.println("Usuario:");
    	String usuario = scan.nextLine(); 
    	if(algo.existeUsuario(usuario)) {
    		System.out.println("Contraseña:");
        	String contrasena = scan.nextLine();
        	return algo.iniciarSesion(usuario, contrasena); 
        	
    	}else{
    		System.out.println("Usuario no existe");
    	}
    	return null;
    }
    public static void cerrarSesion(Usuario userSesion) {
    	userSesion = null;
    	System.out.println("Sesion Cerrada");
    }
    public static void sesionActual(Usuario us) {
    	if(us != null) {
    		System.out.println(us.getUsuario());
    		System.out.println(us.getRole());
    	} else {
    		System.out.println("No hay sesion activa");
    	}
    }
    public static void nProducto() {
    	List<Producto> productos;
        List<Inventario> inventario;
    	RegistroProducto prod = new RegistroProducto();
    	Scanner scanner = new Scanner(System.in);

    	System.out.print("Introduce el nombre del producto: ");
    	String nombre = scanner.nextLine();

        System.out.print("Introduce la descripción del producto: ");
        String descripcion = scanner.nextLine();

        System.out.print("Introduce el precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Introduce el código de barras del producto: ");
        String codigoBarras = scanner.nextLine();

        System.out.print("Introduce el stock inicial del producto: ");
        int stockInicial = scanner.nextInt();
        System.out.print("Introduce el stock mínimo del producto: ");
        int stockMinimo = scanner.nextInt();

        System.out.print("Introduce el stock máximo del producto: ");
    	int stockMaximo = scanner.nextInt();

    	prod.registrarNuevoProducto(nombre, descripcion, precio, codigoBarras, stockInicial, stockMinimo, stockMaximo);
    	   
    	

    }
    
    */
}
