
package appbandasinfonica;

import javax.swing.JOptionPane;

public class Instrumentos {
    
    private static int contador = 1;
    private int id;
    private String nombre;
    private String grupo;
    private String estado;
    private int cantidadDisponible;

    public Instrumentos() {
        this.id = contador++;
        this.nombre = JOptionPane.showInputDialog("Ingrese el nombre del instrumento:");
        this.grupo = seleccionarGrupo();
        this.estado = JOptionPane.showInputDialog("Ingrese el estado del instrumento (nuevo, usado, en reparación):");
        this.cantidadDisponible = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad disponible:"));
    }
    
     private String seleccionarGrupo() {
        String[] grupos = {"Cuerdas", "Cuerdas Frotadas", "Vientos o Maderas", "Metales", "Percusión"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione el grupo del instrumento:",
                "Grupo de Instrumento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, grupos, grupos[0]);
        return grupos[seleccion];
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Grupo: " + grupo + " | Estado: " + estado + " | Disponible: " + cantidadDisponible;
    }
    
    
}
