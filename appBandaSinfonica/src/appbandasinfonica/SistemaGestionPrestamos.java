
package appbandasinfonica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestionPrestamos {
    
    private List<Instrumentos> instrumentos;
    private List<Prestamo> prestamos;

    public SistemaGestionPrestamos() {
        instrumentos = new ArrayList<>();
        prestamos = new ArrayList<>();
    }
    
    public void agregarInstrumentos(){
        Instrumentos instrumento = new Instrumentos();   
        instrumentos.add(instrumento);
        JOptionPane.showMessageDialog(null, "Instrumento agregado exitosamente:\n" + instrumento.toString());
    }
    public void eliminarInstrumento(){
       if (instrumentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay instrumentos para eliminar.");
            return;
        }
        mostrarInstrumentos();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del instrumento a eliminar:"));
        Instrumentos instrumento = buscarInstrumentoPorId(id);
        if (instrumento != null) {
            instrumentos.remove(instrumento);
            JOptionPane.showMessageDialog(null, "Instrumento eliminado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Instrumento no encontrado.");
        }
    }
    
    public void actualizarInstrumento() {
        if (instrumentos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay instrumentos para actualizar.");
            return;
        }
        mostrarInstrumentos();
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del instrumento a actualizar:"));
        Instrumentos instrumento = buscarInstrumentoPorId(id);
        if (instrumento != null) {
            String[] opciones = {"Actualizar Estado", "Actualizar Cantidad Disponible"};
            int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción para actualizar:",
                    "Actualizar Instrumento",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0:
                    String nuevoEstado = JOptionPane.showInputDialog("Ingrese el nuevo estado (nuevo, usado, en reparación):");
                    instrumento.actualizarEstado(nuevoEstado);
                    JOptionPane.showMessageDialog(null, "Estado actualizado exitosamente.");
                    break;
                case 1:
                    int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad disponible:"));
                    instrumento.setCantidadDisponible(nuevaCantidad);
                    JOptionPane.showMessageDialog(null, "Cantidad disponible actualizada exitosamente.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Instrumento no encontrado.");
        }
    }
    
    private Instrumentos buscarInstrumentoPorId(int id) {
        for (Instrumentos instrumento : instrumentos) {
            if (instrumento.getId() == id) {
                return instrumento;
            }
        }
        return null;
    }
    
    private void mostrarInstrumentos() {
        StringBuilder lista = new StringBuilder("Instrumentos Disponibles:\n");
        for (Instrumentos instrumento : instrumentos) {
            lista.append(instrumento.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
    
    public void solicitarPrestamo() {
        String miembro = JOptionPane.showInputDialog("Ingrese el nombre del miembro que solicita el préstamo:");
        // Seleccionar Grupo
        String[] grupos = {"Cuerdas", "Cuerdas Frotadas", "Vientos o Maderas", "Metales", "Percusión"};
        int seleccionGrupo = JOptionPane.showOptionDialog(null, "Seleccione el grupo del instrumento:",
                "Grupo de Instrumento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, grupos, grupos[0]);
        String grupoSeleccionado = grupos[seleccionGrupo];

        // Filtrar instrumentos por grupo y disponibilidad
        ArrayList<Instrumentos> disponibles = new ArrayList<>();
        for (Instrumentos instrumento : instrumentos) {
            if (instrumento.getGrupo().equals(grupoSeleccionado) && instrumento.getCantidadDisponible() > 0 && !instrumento.getEstado().equalsIgnoreCase("en reparación")) {
                disponibles.add(instrumento);
            }
        }

        if (disponibles.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay instrumentos disponibles en el grupo seleccionado.");
            return;
        }

        // Seleccionar Instrumento
        String[] nombresInstrumentos = new String[disponibles.size()];
        for (int i = 0; i < disponibles.size(); i++) {
            nombresInstrumentos[i] = disponibles.get(i).getNombre() + " (ID: " + disponibles.get(i).getId() + ")";
        }
        int seleccionInstrumento = JOptionPane.showOptionDialog(null, "Seleccione el instrumento a prestar:",
                "Seleccionar Instrumento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, nombresInstrumentos, nombresInstrumentos[0]);
        Instrumentos instrumentoSeleccionado = disponibles.get(seleccionInstrumento);

        // Ingresar fechas
        String fechaInicio = JOptionPane.showInputDialog("Ingrese la fecha de inicio del préstamo (dd/mm/aaaa):");
        String fechaDevolucion = JOptionPane.showInputDialog("Ingrese la fecha de devolución programada (dd/mm/aaaa):");

        // Crear Prestamo
        Prestamo prestamo = new Prestamo(miembro, instrumentoSeleccionado, fechaInicio, fechaDevolucion);
        prestamos.add(prestamo);
        instrumentoSeleccionado.setCantidadDisponible(instrumentoSeleccionado.getCantidadDisponible() - 1);
        JOptionPane.showMessageDialog(null, "Préstamo registrado exitosamente:\n" + prestamo.toString());
    }
    
    public void registrarDevolucion() {
        if (prestamos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay préstamos registrados.");
            return;
        }
        mostrarPrestamos();
        int idPrestamo = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del préstamo a registrar la devolución:"));
        Prestamo prestamo = buscarPrestamoPorId(idPrestamo);
        if (prestamo != null && !prestamo.isDevuelto()) {
            prestamo.registrarDevolucion();
        } else {
            JOptionPane.showMessageDialog(null, "Préstamo no encontrado o ya devuelto.");
        }
    }

    private Prestamo buscarPrestamoPorId(int id) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getId() == id) {
                return prestamo;
            }
        }
        return null;
    }
    
    private void mostrarPrestamos() {
        StringBuilder lista = new StringBuilder("Préstamos Registrados:\n");
        for (Prestamo prestamo : prestamos) {
            lista.append(prestamo.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
    
     public void generarReportes() {
        String[] opciones = {
                "Listado de Instrumentos Disponibles",
                "Historial de Préstamos",
                "Instrumentos No Devueltos a Tiempo",
                "Uso por Grupo de Instrumentos"
        };
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el tipo de reporte a generar:",
                "Generar Reporte",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

        switch (seleccion) {
            case 0:
                generarListadoInstrumentos();
                break;
            case 1:
                generarHistorialPrestamos();
                break;
            case 2:
                generarInstrumentosNoDevueltos();
                break;
            case 3:
                generarUsoPorGrupo();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
        }
    }

    private void generarListadoInstrumentos() {
        StringBuilder lista = new StringBuilder("Listado de Instrumentos Disponibles por Grupo:\n");
        for (String grupo : new String[]{"Cuerdas", "Cuerdas Frotadas", "Vientos o Maderas", "Metales", "Percusión"}) {
            lista.append("\nGrupo: ").append(grupo).append("\n");
            for (Instrumentos instrumento : instrumentos) {
                if (instrumento.getGrupo().equals(grupo)) {
                    lista.append(instrumento.toString()).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
    
    private void generarHistorialPrestamos() {
        if (prestamos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay historial de préstamos.");
            return;
        }
        StringBuilder historial = new StringBuilder("Historial de Préstamos:\n");
        for (Prestamo prestamo : prestamos) {
            historial.append(prestamo.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, historial.toString());
    }
    
    private void generarInstrumentosNoDevueltos() {
        StringBuilder alerta = new StringBuilder("Instrumentos No Devueltos a Tiempo:\n");
        boolean hayAlertas = false;
        for (Prestamo prestamo : prestamos) {
            if (!prestamo.isDevuelto()) {
                alerta.append(prestamo.toString()).append("\n");
                hayAlertas = true;
            }
        }
        if (hayAlertas) {
            JOptionPane.showMessageDialog(null, alerta.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay instrumentos no devueltos a tiempo.");
        }
    }
    
    private void generarUsoPorGrupo() {
        StringBuilder uso = new StringBuilder("Uso de Instrumentos por Grupo:\n");
        for (String grupo : new String[]{"Cuerdas", "Cuerdas Frotadas", "Vientos o Maderas", "Metales", "Percusión"}) {
            int count = 0;
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getInstrumento().getGrupo().equals(grupo)) {
                    count++;
                }
            }
            uso.append("Grupo: ").append(grupo).append(" - Préstamos: ").append(count).append("\n");
        }
        JOptionPane.showMessageDialog(null, uso.toString());
    }
    
    }
