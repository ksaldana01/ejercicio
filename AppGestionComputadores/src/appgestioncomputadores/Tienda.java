
package appgestioncomputadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tienda {
    private List<Producto> productos;
    private List<Fabricante> fabricantes;
    private List<Venta> ventas;

    public Tienda() {
        this.productos = new ArrayList<>();
        this.fabricantes = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void registrarFabricante(Fabricante fabricante) {
        fabricantes.add(fabricante);
    }

    public void realizarVenta(Producto producto, int cantidad) {
        Venta venta = new Venta(producto, cantidad, LocalDate.now());
        ventas.add(venta);
        System.out.println("Venta realizada: " + venta);
    }

    public double calcularTotalVentas(LocalDate inicio, LocalDate fin) {
        return ventas.stream()
                .filter(venta -> venta.getFecha().isAfter(inicio.minusDays(1)) && venta.getFecha().isBefore(fin.plusDays(1)))
                .mapToDouble(Venta::getTotal)
                .sum();
    }
}

