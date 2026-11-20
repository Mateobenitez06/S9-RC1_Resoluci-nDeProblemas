package modelo;

public class Productonoperecedero extends Producto {
    private int mesesGarantia;

    public Productonoperecedero(String codigo, String nombre, double precio, int stock, int mesesGarantia) {
        super(codigo, nombre, precio, stock);
        setMesesGarantia(mesesGarantia);
    }

    public int getMesesGarantia() {
        return mesesGarantia;
    }

    public void setMesesGarantia(int mesesGarantia) {
        if (mesesGarantia >= 0) {
            this.mesesGarantia = mesesGarantia;
        }
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + " | Categoria: No Perecedero | Garantia: " + mesesGarantia + " meses";
    }
}