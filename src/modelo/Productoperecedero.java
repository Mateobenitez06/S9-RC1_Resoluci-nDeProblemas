package modelo;

public class Productoperecedero extends Producto {

    private String fechaCaducidad;

    public Productoperecedero(String codigo, String nombre, double precio, int stock, String fechaCaducidad) {
        super(codigo, nombre, precio, stock);
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Categoria: Perecedero | Caduca: " + fechaCaducidad;
    }
}
