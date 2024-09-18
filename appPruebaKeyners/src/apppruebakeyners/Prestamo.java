
package apppruebakeyners;

import java.util.Date;

public record Prestamo(int id, Libro libro, Usuario usuario, Date fechaPrestamo, Date fechaDevolucion) {
    
}
