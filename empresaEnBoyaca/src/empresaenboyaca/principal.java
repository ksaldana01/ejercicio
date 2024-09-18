
package empresaenboyaca;

public class principal {
    public static void main(String[] args) {
        Empleado empleado1 = new Empleado();
        Empleado empleado2 = new Empleado();

        // Crear departamento y asignar empleados
        Departamento departamento = new Departamento();
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);
        departamento.mostrarInfoEmpleado();

        // Crear proyecto y asignar empleados
        Proyecto proyecto = new Proyecto();
        proyecto.asignarEmpleado(empleado1);
        proyecto.asignarEmpleado(empleado2);
        proyecto.mostrarEmpleadosAsignados();
    }
}
