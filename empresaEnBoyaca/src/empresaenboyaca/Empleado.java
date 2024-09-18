
package empresaenboyaca;

import javax.swing.JOptionPane;


   class Empleado {

    public String nombre;
    public int edad;
    public double salario;
    public String puesto;
    private Proyecto Proyecto;

    public Empleado() {
        this.nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado:");
        this.edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del empleado:"));
        this.salario = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario del empleado:"));
        this.puesto = JOptionPane.showInputDialog("Ingrese el puesto del empleado:");
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    

    public void asignarProyecto(Proyecto proyecto) {
        this.Proyecto = proyecto;
        JOptionPane.showMessageDialog(null, "Empleado " + nombre + " asignado al proyecto " + proyecto.getNombreProyecto());
    }

    void add(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
