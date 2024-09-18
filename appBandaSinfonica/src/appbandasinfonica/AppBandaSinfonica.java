
package appbandasinfonica;

import javax.swing.JOptionPane;

public class AppBandaSinfonica {

    public static void main(String[] args) {
      SistemaGestionPrestamos sistema = new SistemaGestionPrestamos();
        boolean continuar = true;

        while (continuar) {
            String[] opciones = {
                    "Gestión de Instrumentos",
                    "Préstamo de Instrumentos",
                    "Devolución de Instrumentos",
                    "Generación de Reportes",
                    "Salir"
            };
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:",
                    "Sistema de Gestión de Préstamos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    gestionarInstrumentos(sistema);
                    break;
                case 1:
                    sistema.solicitarPrestamo();
                    break;
                case 2:
                    sistema.registrarDevolucion();
                    break;
                case 3:
                    sistema.generarReportes();
                    break;
                case 4:
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                    break;
                default:
                    continuar = false;
                    break;
            }
        }
    }

    private static void gestionarInstrumentos(SistemaGestionPrestamos sistema) {
        String[] opcionesInstrumento = {
                "Agregar Instrumento",
                "Eliminar Instrumento",
                "Actualizar Instrumento",
                "Volver"
        };
        int seleccionInstrumento = JOptionPane.showOptionDialog(null, "Seleccione una opción:",
                "Gestión de Instrumentos",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesInstrumento, opcionesInstrumento[0]);

        switch (seleccionInstrumento) {
            case 0:
                sistema.agregarInstrumentos();
                break;
            case 1:
                sistema.eliminarInstrumento();
                break;
            case 2:
                sistema.actualizarInstrumento();
                break;
            case 3:
                return;
            default:
                break;
        }
    }
    
}
