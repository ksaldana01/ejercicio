
package appbandasinfonica;

import java.time.LocalDate;
import javax.swing.JOptionPane;


public class Prestamo {
    
    private static int contador = 1;
    private int id;
    private Instrumentos instrumento;
    private String miembro;
    private String fechaInicio;
    private String fechaDevolucion;
    private boolean devuelto;
    
    public Prestamo(String miembro, Instrumentos instrumento, String fechaInicio, String fechaDevolucion) {
        this.id = contador++;
        this.miembro = miembro;
        this.instrumento = instrumento;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = false;
    }

    public int getId() { return id; }

    public String getMiembro() { return miembro; }
    public void setMiembro(String miembro) { this.miembro = miembro; }

    public Instrumentos getInstrumento() { return instrumento; }
    public void setInstrumento(Instrumentos instrumento) { this.instrumento = instrumento; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(String fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public boolean isDevuelto() { return devuelto; }
    public void setDevuelto(boolean devuelto) { this.devuelto = devuelto; }

    public void registrarDevolucion() {
        this.devuelto = true;
        instrumento.setCantidadDisponible(instrumento.getCantidadDisponible() + 1);
        JOptionPane.showMessageDialog(null, "Instrumento devuelto exitosamente.");
    }

    @Override
    public String toString() {
        return "ID Prestamo: " + id + " | Miembro: " + miembro + " | Instrumento: " + instrumento.getNombre() +
                " | Fecha Inicio: " + fechaInicio + " | Fecha Devolución: " + fechaDevolucion + " | Devuelto: " + (devuelto ? "Sí" : "No");
    }
    
    
}
