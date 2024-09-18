
package empresaenboyaca;

import java.util.ArrayList;
import javax.swing.JOptionPane;

 class Departamento {
    
    public ArrayList<Empleado> empleados;
    public String nombreDepartamento;
    public String ubicacion; //(Oficina física, ciudad o región, área dentro de la sede)

    public Departamento() {
        this.nombreDepartamento = JOptionPane.showInputDialog("Ingrese el nombre del departamento:");
        this.ubicacion = JOptionPane.showInputDialog("Ingrese la ubicación del departamento:");
        this.empleados = new ArrayList<>();
    }

    public String getNombreDepartamento() { return nombreDepartamento; }
    public void setNombreDepartamento(String nombreDepartamento) { this.nombreDepartamento = nombreDepartamento; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public void agregarEmpleado(Empleado empleado) {
        empleado.add(empleado);
        JOptionPane.showMessageDialog(null, "Empleado " + empleado.getNombre() + " agregado al departamento " + nombreDepartamento);
    }
    
    public void mostrarInfoEmpleado(){
        
        StringBuilder info = new StringBuilder("Empleados en el departamento " + nombreDepartamento + ":\n");
        
        for (Empleado empleado: empleados) {
            info.append("\n").append(empleado.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, info.toString());
        
    }
    
    
}
