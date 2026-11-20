package interfaz;

import modelo.Producto;
import modelo.Productonoperecedero;
import modelo.Productoperecedero;
import negocio.Inventario;

import java.util.Scanner;

public class MenuConsola {
    private Inventario inventario;
    private Scanner scanner;

    public MenuConsola(int capacidad) {
        this.inventario = new Inventario(capacidad);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar salto de linea
                procesarOpcion(opcion);
            } else {
                System.out.println("Ingresa un numero valido.");
                scanner.nextLine();
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== MICROMERCADO - INVENTARIO ===");
        System.out.println("1. Registrar producto perecedero");
        System.out.println("2. Registrar producto no perecedero");
        System.out.println("3. Listar todos los productos");
        System.out.println("4. Buscar producto por codigo");
        System.out.println("5. Actualizar stock");
        System.out.println("6. Registrar venta");
        System.out.println("7. Ver alertas de stock (< 3)");
        System.out.println("0. Salir");
        System.out.print("Elige una opcion: ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarPerecedero();
                break;
            case 2:
                registrarNoPerecedero();
                break;
            case 3:
                System.out.println(inventario.listarProductos());
                break;
            case 4:
                buscarProducto();
                break;
            case 5:
                actualizarStock();
                break;
            case 6:
                registrarVenta();
                break;
            case 7:
                System.out.println(inventario.listarAlertasStock());
                break;
            case 0:
                System.out.println("Saliendo del sistema...");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    private void registrarPerecedero() {
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Stock inicial: ");
        int stock = leerEntero();
        System.out.print("Fecha de caducidad (ej. 2026-12-15): ");
        String fecha = scanner.nextLine();

        Producto p = new Productoperecedero(codigo, nombre, precio, stock, fecha);
        if (inventario.agregarProducto(p)) {
            System.out.println("Producto perecedero registrado.");
        } else {
            System.out.println("No se pudo registrar (codigo repetido o inventario lleno).");
        }
    }

    private void registrarNoPerecedero() {
        System.out.print("Codigo: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = leerDouble();
        System.out.print("Stock inicial: ");
        int stock = leerEntero();
        System.out.print("Meses de garantia: ");
        int meses = leerEntero();

        Producto p = new Productonoperecedero(codigo, nombre, precio, stock, meses);
        if (inventario.agregarProducto(p)) {
            System.out.println("Producto no perecedero registrado.");
        } else {
            System.out.println("No se pudo registrar (codigo repetido o inventario lleno).");
        }
    }

    private void buscarProducto() {
        System.out.print("Ingresa el codigo a buscar: ");
        String codigo = scanner.nextLine();
        Producto p = inventario.buscarPorCodigo(codigo);
        if (p != null) {
            System.out.println(p.mostrarInformacion());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private void actualizarStock() {
        System.out.print("Codigo del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Nuevo stock: ");
        int nuevoStock = leerEntero();
        if (inventario.actualizarStock(codigo, nuevoStock)) {
            System.out.println("Stock actualizado.");
        } else {
            System.out.println("No se pudo actualizar (verifica codigo o valor >= 0).");
        }
    }

    private void registrarVenta() {
        System.out.print("Codigo del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Cantidad a vender: ");
        int cantidad = leerEntero();
        if (inventario.registrarVenta(codigo, cantidad)) {
            System.out.println("Venta registrada.");
        } else {
            System.out.println("No se pudo realizar la venta (revisa codigo o stock disponible).");
        }
    }

    private int leerEntero() {
        while (!scanner.hasNextInt()) {
            System.out.print("Ingresa un numero entero valido: ");
            scanner.nextLine();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private double leerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Ingresa un numero decimal valido: ");
            scanner.nextLine();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}