
package appgestioncomputadores;
    import java.util.Objects;

public class Fabricante {


    private String nombre;
    private String pais;
    private int añoFundacion;

    // Constructor
    public Fabricante(String nombre, String pais, int añoFundacion) {
        this.nombre = nombre;
        this.pais = pais;
        this.añoFundacion = añoFundacion;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    // Implementación de equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fabricante that = (Fabricante) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(pais, that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, pais);
    }

    // Implementación de toString
    @Override
    public String toString() {
        return "Fabricante{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", añoFundacion=" + añoFundacion +
                '}';
    }
}


