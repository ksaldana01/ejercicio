
package ejercicioDeLosFicheros;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Reporte {
     
    
    public void generarReporteDeLibrosMasPrestados(List<Prestamo> prestamos){
        
        
        
       Map<String, Integer>conteoPrestamos = new HashMap<>();
       
       for(Prestamo prestamo : prestamos){
           String tituloLibro = prestamo.getLibro().getTitulo();
           conteoPrestamos.put(tituloLibro, conteoPrestamos.getOrDefault(tituloLibro, 0) + 1);
       }
       
       List<Map.Entry<String, Integer>> librosMasPrestados;
        librosMasPrestados = conteoPrestamos.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toList());
       
       StringBuilder reporte = new StringBuilder("Reporte de libros mas prestados:\n");
        for (Map.Entry<String, Integer> entry : librosMasPrestados) {
            reporte.append(entry.getKey()).append(": ").append(entry.getValue()).append(" prestamos\n");
        }
       JOptionPane.showMessageDialog(null, reporte.toString(), "Reporte de Libros Más Prestados", JOptionPane.INFORMATION_MESSAGE);
    }
    
 
    //////////////////////////////////////////////////////////////////
    
    
    public void exportarCSV(List<Prestamo> prestamos, String rutaArchivo){
        
        try (FileWriter escritor = new FileWriter(rutaArchivo)){
            
            escritor.append("Usuario,Libro,Fecha inicio,Fecha devolucion,Estado\n");
            
            for (Prestamo prestamo : prestamos) {
                escritor.append(prestamo.getUsuario().getNombre())
                        .append(',')   
                        .append(prestamo.getLibro().getTitulo())
                        .append(',')
                        .append((CharSequence) prestamo.getFechaInicio())
                        .append(',')
                        .append((CharSequence) prestamo.getFechaDevolucion())
                        .append(',')
                        .append(prestamo.getEstado())
                        .append('\n');      
            }
            
            JOptionPane.showMessageDialog(null, "Archivo CSV exportado correctamente" + rutaArchivo);
            
        } catch (IOException e) {
            
            JOptionPane.showMessageDialog(null, "Error al exportar el archivo" + e.getMessage());
            
        }
        
    }
    
}
