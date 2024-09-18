
package appgestioncomputadores;

import java.time.LocalDate;
import java.util.Objects;

public class Venta {
    private Producto producto;
    private int cantidad;
    private LocalDate fecha;
    private double total;

    public Venta(Producto producto, int cantidad, LocalDate fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = producto.getPrecio() * cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(producto, venta.producto) && Objects.equals(fecha, venta.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, fecha);
    }

    @Override
    public String toString() {
        return "Venta{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}

