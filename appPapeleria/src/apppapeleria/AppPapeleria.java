
package apppapeleria;

import java.util.Scanner;

public class AppPapeleria {

    public static void main(String[] args) {
        


        Scanner scanner = new Scanner(System.in);

        int[] juego1 = {2, 3, 1, 2};  // lápices, cuadernos, borradores, reglas
        int[] juego2 = {6, 4, 2, 1};

        int[][] ventasJuegos = new int[12][2]; 
        double[][] preciosArticulos = new double[12][4]; // [mes][lápices, cuadernos, borradores, reglas]
        int[][] articulosVendidosMensuales = new int[12][4]; // [mes][lápices, cuadernos, borradores, reglas]
        double[][] totalVentasMensuales = new double[12][4]; // [mes][total lápices, total cuadernos, total borradores, total reglas]

        // Ingreso de ventas de juegos para cada mes
        for (int mes = 0; mes < 12; mes++) {
            System.out.println("Mes " + (mes + 1));
            System.out.print("Ingrese la cantidad de juegos tipo 1 vendidos: ");
            ventasJuegos[mes][0] = scanner.nextInt();
            System.out.print("Ingrese la cantidad de juegos tipo 2 vendidos: ");
            ventasJuegos[mes][1] = scanner.nextInt();
        }

        // Ingreso de precios de los artículos para cada mes
        for (int mes = 0; mes < 12; mes++) {
            System.out.println("Mes " + (mes + 1));
            System.out.print("Ingrese el precio de los lápices: ");
            preciosArticulos[mes][0] = scanner.nextDouble();
            System.out.print("Ingrese el precio de los cuadernos: ");
            preciosArticulos[mes][1] = scanner.nextDouble();
            System.out.print("Ingrese el precio de los borradores: ");
            preciosArticulos[mes][2] = scanner.nextDouble();
            System.out.print("Ingrese el precio de las reglas: ");
            preciosArticulos[mes][3] = scanner.nextDouble();
        }

        // Cálculo de artículos vendidos y total de ventas por mes
        for (int mes = 0; mes < 12; mes++) {
            for (int i = 0; i < 4; i++) {
                articulosVendidosMensuales[mes][i] = ventasJuegos[mes][0] * juego1[i] + ventasJuegos[mes][1] * juego2[i];
                totalVentasMensuales[mes][i] = articulosVendidosMensuales[mes][i] * preciosArticulos[mes][i];
            }
        }

        // Mostrar los resultados por mes
        double totalGeneralMensual;
        double[] totalAnualPorArticulo = new double[4]; // Total anual de cada artículo
        double totalAnualGeneral = 0;

        for (int mes = 0; mes < 12; mes++) {
            System.out.println("\nResultados del mes " + (mes + 1) + ":");

            // Número total de artículos vendidos en el mes
            System.out.println("Lápices vendidos: " + articulosVendidosMensuales[mes][0]);
            System.out.println("Cuadernos vendidos: " + articulosVendidosMensuales[mes][1]);
            System.out.println("Borradores vendidos: " + articulosVendidosMensuales[mes][2]);
            System.out.println("Reglas vendidas: " + articulosVendidosMensuales[mes][3]);

            // Total de ventas por artículo en el mes
            System.out.printf("Total ventas lápices: $%.2f\n", totalVentasMensuales[mes][0]);
            System.out.printf("Total ventas cuadernos: $%.2f\n", totalVentasMensuales[mes][1]);
            System.out.printf("Total ventas borradores: $%.2f\n", totalVentasMensuales[mes][2]);
            System.out.printf("Total ventas reglas: $%.2f\n", totalVentasMensuales[mes][3]);

            // Cálculo del total general del mes
            totalGeneralMensual = totalVentasMensuales[mes][0] + totalVentasMensuales[mes][1] +
                                  totalVentasMensuales[mes][2] + totalVentasMensuales[mes][3];
            System.out.printf("Total general de ventas del mes: $%.2f\n", totalGeneralMensual);

            // Acumulación para el total anual
            for (int i = 0; i < 4; i++) {
                totalAnualPorArticulo[i] += totalVentasMensuales[mes][i];
            }
            totalAnualGeneral += totalGeneralMensual;
        }

        // Mostrar los totales anuales
        System.out.println("\n--- Totales Anuales ---");
        System.out.printf("Total anual de ventas de lápices: $%.2f\n", totalAnualPorArticulo[0]);
        System.out.printf("Total anual de ventas de cuadernos: $%.2f\n", totalAnualPorArticulo[1]);
        System.out.printf("Total anual de ventas de borradores: $%.2f\n", totalAnualPorArticulo[2]);
        System.out.printf("Total anual de ventas de reglas: $%.2f\n", totalAnualPorArticulo[3]);
        System.out.printf("Total anual general de ventas: $%.2f\n", totalAnualGeneral);
    }
}

    
