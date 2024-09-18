
package appgestioncomputadores;

import java.time.LocalDate;
import java.util.Arrays;

public class AppGestionComputadores {

    public static void main(String[] args) {
        Fabricante fabricante1 = new Fabricante("Samsung", "Corea del Sur", 1938);
        Fabricante fabricante2 = new Fabricante("LG", "Corea del Sur", 1958);

        // Crear productos
        Producto producto1 = new Producto("Monitor", "P001", "Pantalla", 250.0, Arrays.asList(fabricante1, fabricante2));
        Producto producto2 = new Producto("Laptop", "P002", "Computadora", 1000.0, Arrays.asList(fabricante1));

        // Crear tienda
        Tienda tienda = new Tienda();

        // Registrar fabricantes y productos en la tienda
        tienda.registrarFabricante(fabricante1);
        tienda.registrarFabricante(fabricante2);
        tienda.agregarProducto(producto1);
        tienda.agregarProducto(producto2);

        // Realizar ventas
        tienda.realizarVenta(producto1, 2);
        tienda.realizarVenta(producto2, 1);

        // Calcular total de ventas en un periodo
        double total = tienda.calcularTotalVentas(LocalDate.now().minusDays(1), LocalDate.now());
        System.out.println("Total de ventas: $" + total);    }
    
}
