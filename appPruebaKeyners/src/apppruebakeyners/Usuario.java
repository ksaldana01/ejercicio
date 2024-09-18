
package apppruebakeyners;

import java.util.List;

public record Usuario(String nombre, TipoUsuario tipoUsuario, List<Libro> listaLibrosPrestados) {
    public enum TipoUsuario { NORMAL, PREMIUM }
}
