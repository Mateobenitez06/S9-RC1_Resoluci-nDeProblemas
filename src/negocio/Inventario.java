package negocio;

import modelo.Producto;

public class Inventario {
    private Producto[] productos;
    private int cantidadActual;

    public Inventario(int capacidadMaxima) {
        if (capacidadMaxima > 0) {
            this.productos = new Producto[capacidadMaxima];
        } else {
            this.productos = new Producto[50];
        }
        this.cantidadActual = 0;
    }

    public boolean agregarProducto(Producto nuevo) {
        if (nuevo == null || cantidadActual >= productos.length) {
            return false;
        }
        if (buscarPorCodigo(nuevo.getCodigo()) != null) {
            return false;
        }
        productos[cantidadActual] = nuevo;
        cantidadActual++;
        return true;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (int i = 0; i < cantidadActual; i++) {
            if (productos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    public boolean actualizarStock(String codigo, int nuevoStock) {
        Producto encontrado = buscarPorCodigo(codigo);
        if (encontrado == null || nuevoStock < 0) {
            return false;
        }
        encontrado.setStock(nuevoStock);
        return true;
    }

    public boolean registrarVenta(String codigo, int cantidadVendida) {
        Producto encontrado = buscarPorCodigo(codigo);
        if (encontrado == null || cantidadVendida <= 0 || encontrado.getStock() < cantidadVendida) {
            return false;
        }
        encontrado.setStock(encontrado.getStock() - cantidadVendida);
        return true;
    }

    public String listarProductos() {
        if (cantidadActual == 0) {
            return "No hay productos registrados en el inventario.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidadActual; i++) {
            sb.append(i + 1).append(". ").append(productos[i].mostrarInformacion()).append("\n");
        }
        return sb.toString();
    }

    public String listarAlertasStock() {
        StringBuilder sb = new StringBuilder();
        boolean hayAlertas = false;
        for (int i = 0; i < cantidadActual; i++) {
            if (productos[i].getStock() < 3) {
                sb.append("- ").append(productos[i].verificarAlertaStock())
                        .append(" (Codigo: ").append(productos[i].getCodigo()).append(")\n");
                hayAlertas = true;
            }
        }
        if (!hayAlertas) {
            return "No hay productos con stock bajo (menor a 3 unidades).";
        }
        return sb.toString();
    }
}