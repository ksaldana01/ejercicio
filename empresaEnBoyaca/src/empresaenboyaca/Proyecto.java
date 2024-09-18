
package empresaenboyaca;

import java.util.ArrayList;
import javax.swing.JOptionPane;

 class Proyecto {
    
    private String nombreProyecto;
    private double presupuesto;
    private String fechaInicio;
    private String fechaFin;
    private ArrayList<Empleado> empleadosAsignados;
    
    public Proyecto() {
        this.nombreProyecto = JOptionPane.showInputDialog("Ingrese el nombre del proyecto:");
        this.presupuesto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el presupuesto del proyecto:"));
        this.fechaInicio = JOptionPane.showInputDialog("Ingrese la fecha de inicio del proyecto:");
        this.fechaFin = JOptionPane.showInputDialog("Ingrese la fecha de fin del proyecto:");
        this.empleadosAsignados = new ArrayList<>();
    }
    
    public String getNombreProyecto() { return nombreProyecto; }
    public void setNombreProyecto(String nombreProyecto) { this.nombreProyecto = nombreProyecto; }

    public double getPresupuesto() { return presupuesto; }
    public void setPresupuesto(double presupuesto) { this.presupuesto = presupuesto; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public void asignarEmpleado(Empleado empleado) {
        empleadosAsignados.add(empleado);
        JOptionPane.showMessageDialog(null, "Empleado " + empleado.getNombre() + " asignado al proyecto " + nombreProyecto);
    }
    
    public void mostrarEmpleadosAsignados() {
        StringBuilder lista = new StringBuilder("Empleados asignados al proyecto " + nombreProyecto + ":\n");
        for (Empleado empleado : empleadosAsignados) {
            lista.append(empleado.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, lista.toString());
    }
    
}
