
package apppruebakeyners;

public record Libro(int id, String titulo, String autor, Estado estado) 
{
    public enum Estado { DISPONIBLE, RESERVADO, PRESTADO } 
}
