package interfaz;

import modelo.Producto;
import modelo.Productoperecedero;
import modelo.Productonoperecedero;
import negocio.Inventario;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventario inventario = new Inventario(100);

        // Precarga de 3 productos de ejemplo para pruebas inmediatas
        inventario.agregarProducto(new Productoperecedero("P01", "Leche Entera 1L", 1.25, 2, "2026-12-05"));
        inventario.agregarProducto(new Productoperecedero("P02", "Yogurt Natural", 2.10, 5, "2026-12-10"));
        inventario.agregarProducto(new Productonoperecedero("NP01", "Arroz Blanco 2kg", 3.50, 1, 24));

        int opcion = -1;

        do {
            System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
            System.out.println("1. Registrar producto perecedero");
            System.out.println("2. Registrar producto no perecedero");
            System.out.println("3. Mostrar inventario y alertas de stock (< 3)");
            System.out.println("4. Actualizar stock de un producto");
            System.out.println("5. Eliminar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Registrar Producto Perecedero ---");
                    System.out.print("Código: ");
                    String codP = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nomP = sc.nextLine();
                    System.out.print("Precio: ");
                    double precioP = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock inicial: ");
                    int stockP = Integer.parseInt(sc.nextLine());
                    System.out.print("Fecha de caducidad (YYYY-MM-DD): ");
                    String fechaCad = sc.nextLine();

                    Producto prodPerecedero = new Productoperecedero(codP, nomP, precioP, stockP, fechaCad);
                    if (inventario.agregarProducto(prodPerecedero)) {
                        System.out.println("Producto perecedero registrado correctamente.");
                    } else {
                        System.out.println("Error: No se pudo registrar (verifique que el código no esté repetido y los valores sean válidos).");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Registrar Producto No Perecedero ---");
                    System.out.print("Código: ");
                    String codNP = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nomNP = sc.nextLine();
                    System.out.print("Precio: ");
                    double precioNP = Double.parseDouble(sc.nextLine());
                    System.out.print("Stock inicial: ");
                    int stockNP = Integer.parseInt(sc.nextLine());
                    System.out.print("Meses de garantía / vida útil: ");
                    int garantia = Integer.parseInt(sc.nextLine());

                    Producto prodNoPerecedero = new Productonoperecedero(codNP, nomNP, precioNP, stockNP, garantia);
                    if (inventario.agregarProducto(prodNoPerecedero)) {
                        System.out.println("Producto no perecedero registrado correctamente.");
                    } else {
                        System.out.println("Error: No se pudo registrar (verifique que el código no esté repetido y los valores sean válidos).");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Inventario Actual ---");
                    System.out.println(inventario.listarProductos());
                    System.out.println("--- Alertas de Stock Bajo (< 3) ---");
                    System.out.println(inventario.listarAlertasStock());
                    break;

                case 4:
                    System.out.println("\n--- Actualizar Stock ---");
                    System.out.print("Ingrese el código del producto: ");
                    String codAct = sc.nextLine();
                    System.out.print("Ingrese el nuevo stock: ");
                    int nuevoStock = Integer.parseInt(sc.nextLine());

                    if (inventario.actualizarStock(codAct, nuevoStock)) {
                        System.out.println("Stock actualizado con éxito.");
                    } else {
                        System.out.println("Producto no encontrado o cantidad inválida.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Eliminar Producto ---");
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    String codElim = sc.nextLine();

                    if (inventario.eliminarProducto(codElim)) {
                        System.out.println("Producto eliminado del inventario.");
                    } else {
                        System.out.println("No se encontró ningún producto con ese código.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}