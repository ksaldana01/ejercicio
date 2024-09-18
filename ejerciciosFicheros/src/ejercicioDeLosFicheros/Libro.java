
package ejercicioDeLosFicheros;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Libro {
    
    private String titulo;
    private String autor;
    private String ISBN;
    private String genero;
    private int numeroCopias;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String ISBN, String genero, int numeroCopias, int copiasDisponibles) {
        this.titulo = titulo;
        this.autor = autor;
        this.ISBN = ISBN;
        this.genero = genero;
        this.numeroCopias = numeroCopias;
        this.copiasDisponibles = copiasDisponibles;
    }

    private Libro(String part, String part0, String part1, String part2, int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
        
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public void setCopiasDisponibles(int copiasDisponibles) {
        this.copiasDisponibles = copiasDisponibles;
    }
    
    

    
    public void prestarLibro(){
        if (copiasDisponibles > 0){
            copiasDisponibles--;
        }
    }
    
    public void devolverLibro() {
        if (copiasDisponibles < numeroCopias) {
            copiasDisponibles++;
        }
    }
    
     public String toFileString() {
        return titulo + "," + autor + "," + ISBN + "," + genero + "," + numeroCopias + "," + copiasDisponibles;
    }

    // Método para crear un libro desde una línea de texto de archivo
    public static Libro fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        return new Libro(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
    }
    
}

class sistemaBiblioteca{
    
    private List<Libro> catalogo = new ArrayList<>();
    List<Prestamo> prestamos1 = new ArrayList<>();
    Reporte reporte = new Reporte();
        
    
    public void agregarLibro(){
        
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
        String autor = JOptionPane.showInputDialog("Ingrese el autor:");
        String ISBN = JOptionPane.showInputDialog("Ingrese el ISBN:");
        String genero = JOptionPane.showInputDialog("Ingrese el género:");
        int numeroCopias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de copias:"));

        catalogo.add(new Libro(titulo, autor, ISBN, genero, numeroCopias, numeroCopias));
        JOptionPane.showMessageDialog(null, "Libro agregado con éxito");
        
    }
    
    public void modificarLibro(){
        String ISBN = JOptionPane.showInputDialog("ingrese el ISBN del Libro: ");
        for (Libro libro : catalogo) {
            if (libro.getISBN().equals(ISBN)) {
                int numCopias = Integer.parseInt(JOptionPane.showInputDialog("ingrese el nuevo numero de copias: "));
           
                libro.setNumeroCopias(numCopias);
                JOptionPane.showMessageDialog(null, "Libro modificado con exito");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Libro no encontrado");
    }
    
    public void eliminarLibro(){
        
        String ISBN = JOptionPane.showInputDialog("ingrese el ISBN del libro a eliminar: ");
        catalogo.removeIf(libro -> libro.getISBN().equals(ISBN));
        JOptionPane.showMessageDialog(null, "libro eliminado con exito");
        
    }
   

   
}
