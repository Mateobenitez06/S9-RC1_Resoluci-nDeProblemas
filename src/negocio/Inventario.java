package negocio;

import modelo.Producto;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> listaProductos;

    public Inventario(int capacidadMaxima) {
        this.listaProductos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto p) {
        if (p == null) {
            return false;
        }
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()) {
            return false;
        }
        if (p.getPrecio() <= 0) {
            return false;
        }
        if (p.getStock() < 0) {
            return false;
        }
        if (buscarPorCodigo(p.getCodigo()) != null) { // no permitir codigos repetidos
            return false;
        }
        listaProductos.add(p);
        return true;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return listaProductos.get(i);
            }
        }
        return null;
    }

    public String listarProductos() {
        if (listaProductos.isEmpty()) {
            return "No hay productos registrados en el inventario.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaProductos.size(); i++) {
            sb.append(i + 1).append(". ").append(listaProductos.get(i).mostrarInformacion()).append("\n");
        }
        return sb.toString();
    }

    public String listarAlertasStock() {
        StringBuilder sb = new StringBuilder();
        boolean hayAlertas = false;
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto p = listaProductos.get(i);
            if (p.getStock() < 3) {
                sb.append("- ").append(p.verificarAlertaStock())
                        .append(" (Codigo: ").append(p.getCodigo()).append(")\n");
                hayAlertas = true;
            }
        }
        if (!hayAlertas) {
            return "No hay productos con stock bajo (menor a 3 unidades).";
        }
        return sb.toString();
    }

    public boolean actualizarStock(String codigo, int nuevaCantidad) {
        Producto encontrado = buscarPorCodigo(codigo);
        if (encontrado == null || nuevaCantidad < 0) {
            return false;
        }
        encontrado.setStock(nuevaCantidad);
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

    public boolean eliminarProducto(String codigo) {
        Producto encontrado = buscarPorCodigo(codigo);
        if (encontrado == null) {
            return false;
        }
        listaProductos.remove(encontrado);
        return true;
    }
}