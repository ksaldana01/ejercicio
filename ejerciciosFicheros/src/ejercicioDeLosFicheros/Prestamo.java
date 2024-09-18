
package ejercicioDeLosFicheros;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Prestamo {
    
    private Libro libro;
    private Usuario usuario;
    private String fechaInicio;
    private String fechaDevolucion;
    private String estado;

    public Prestamo(Libro libro, Usuario usuario, String fechaInicio, String fechaDevolucion, String estado) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaInicio = fechaInicio;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = "pendiente";
    }

    Prestamo(Libro libro, Usuario usuario, String nombre, String nuevaFechaDevolucion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void devolver(){
        this.estado = "devuelto";
     
    }

     public String toFileString() {
        return libro.getISBN() + "," + usuario.getNombre() + "," + fechaInicio + "," + fechaDevolucion + "," + estado;
    }

    // Método para crear un préstamo desde una línea de texto de archivo
    public static Prestamo fromFileString(String fileString, List<Libro> libros, List<Usuario> usuarios) {
        String[] parts = fileString.split(",");
        Libro libro = libros.stream().filter(l -> l.getISBN().equals(parts[0])).findFirst().orElse(null);
        Usuario usuario = usuarios.stream().filter(u -> u.getNombre().equals(parts[1])).findFirst().orElse(null);
        return new Prestamo(libro, usuario, parts[2], parts[3]);
    }
    
    
}
