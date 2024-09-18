
package ejercicioDeLosFicheros;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

   class Biblioteca {
    
    private List<Usuario> usuarios;
    private List<Libro> catalogo;
    private List<Prestamo> prestamos;
    
    public Biblioteca(){
        
        usuarios = new ArrayList<>();
        catalogo = new ArrayList<>();
        prestamos =  new ArrayList<>();
        cargarDatosIniciales();
        
    }
    
    private void cargarDatosIniciales(){
        
        usuarios.add(new Usuario("Carlos","001","bibliotecario","carlos@biblioteca.com","1234"));
        usuarios.add(new Usuario("keyners","002","bibliotecario","keyners@biblioteca.com","0108"));
        
        catalogo.add(new Libro("La Divina Comedia", "Dante Alegeri", "18923", "accion",6 , 5));
        catalogo.add(new Libro("IT", "Stephen King", "18923", "Terror",10 , 6));

    }
    
    private void registrarUsuario(){
        
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
        String identificacion = JOptionPane.showInputDialog("Ingrese la identificación:");
        String tipoUsuario = JOptionPane.showInputDialog("Ingrese el tipo de usuario (lector o bibliotecario):");
        String correoElectronico = JOptionPane.showInputDialog("Ingrese el correo electrónico:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");

        usuarios.add(new Usuario(nombre, identificacion, tipoUsuario, correoElectronico, contrasena));
        JOptionPane.showMessageDialog(null, "Usuario registrado con éxito");
        
    }
    
    public Usuario login(){
        
        String nombre = JOptionPane.showInputDialog("ingrese su nombre: ");
        String contraseña = JOptionPane.showInputDialog("ingrese su contraseña:");
        
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre) && u.autenticacion(contraseña)) {
                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                return u;
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        return null;
    }
    
    public void menuBibliotecario(Usuario usuario){
        
        boolean continuar = true;
        
        while(continuar){
        if (usuario.getTipoUsuario().equals("bibliotecario")) {
            
            String[] opciones = {
                "Registrar Usuarios",
                "Gestionar Catalogo",
                "Gestionar Prestamos",
                "Generar Reportes",  
                "Salir"
            };
            int seleccion = JOptionPane.showOptionDialog(null, "Menú Bibliotecario", "Opciones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            
            
            switch (seleccion) {
                case 0:
                    registrarUsuario();

                    return;
                case 1:
                    gestionarCatalogo();
                break;
                case 2: 
                    gestionarPrestamos();
                break;
                case 3: 
                {
                    Reporte reporte = new Reporte();
                    reporte.generarReporteDeLibrosMasPrestados(prestamos);
                }
                break;
                case 4: 
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                break;
                default : JOptionPane.showMessageDialog(null, "error cometido en el proceso");
            }
            
            
        }else{
            
           String[] opciones = {
                "Consultar Catalogo",
                "ver Prestamos",
                "Salir"
            };
            int seleccion = JOptionPane.showOptionDialog(null, "Menú lector", "Opciones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]); 
            
            
            
            
            switch (seleccion) {
                case 0:
                    consultarCatalogo();
                    break;
                case 1:
                    verPrestamos(usuario);
                    break;
                case 2:
                    continuar = false;
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    continuar = false;
                }
            }  
        }
    }
    
    public void gestionarCatalogo(){
         String[] options = {"Agregar libro",
             "Modificar libro",
             "Eliminar libro",
             "Ver catálogo"
             };
         
        int seleccion = JOptionPane.showOptionDialog(null, "Gestionar Catálogo", "Opciones",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        

        switch (seleccion) {
            case 0:
                agregarLibro();
                gestionarCatalogo();
                break;
            case 1:
                modificarLibro();
                gestionarCatalogo();
                break;
            case 2:
                eliminarLibro();
                gestionarCatalogo();
                break;
            case 3:
                consultarCatalogo();
                gestionarCatalogo();
                break;
            
        }
    }
    
    public void agregarLibro() {
        String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
        String autor = JOptionPane.showInputDialog("Ingrese el autor:");
        String ISBN = JOptionPane.showInputDialog("Ingrese el ISBN:");
        String genero = JOptionPane.showInputDialog("Ingrese el género:");
        int numCopias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de copias:"));

        catalogo.add(new Libro(titulo, autor, ISBN, genero, numCopias, numCopias));
        JOptionPane.showMessageDialog(null, "Libro agregado con éxito");
        return;
    }
    
    public void modificarLibro(){
        
        String ISBN = JOptionPane.showInputDialog("ingrese el isbn del libro a modificar:");
        for (Libro libro : catalogo) {
            if (libro.getISBN().equals(ISBN)) {
                String nuevoTitulo = JOptionPane.showInputDialog("Ingrese el nuevo título:", libro.getTitulo());
                String nuevoAutor = JOptionPane.showInputDialog("Ingrese el nuevo autor:", libro.getAutor());
                String nuevoGenero = JOptionPane.showInputDialog("Ingrese el nuevo género:", libro.getGenero());
                int nuevasCopias = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número de copias:", libro.getNumeroCopias()));
            
                libro = new Libro(nuevoTitulo, nuevoAutor, ISBN, nuevoGenero, nuevasCopias, nuevasCopias);
                JOptionPane.showMessageDialog(null, "Libro modificado con éxito");
                return;
            }
        }
       JOptionPane.showMessageDialog(null, "Libro no encontrado"); 
    }
    
    public void eliminarLibro(){
        String ISBN = JOptionPane.showInputDialog("Ingrese el ISBN del libro a eliminar:");
        catalogo.removeIf(libro -> libro.getISBN().equals(ISBN));
        JOptionPane.showMessageDialog(null, "Libro eliminado con éxito");
    }
    
    public void consultarCatalogo(){
        
        StringBuilder SB = new StringBuilder("Catalogo de libros:\n");
        for (Libro libroo : catalogo) {
            SB.append("Titulo: ").append(libroo.getTitulo())
                    .append(", Autor: ").append(libroo.getAutor())
                    .append(", ISBN: ").append(libroo.getISBN())
                    .append(", Copias disponibles: ").append(libroo.getCopiasDisponibles())
                    .append("\n");
        }
        JOptionPane.showMessageDialog(null, SB.toString());
        
    }
    
     public void gestionarPrestamos() {

         String[] opciones = {
             "Aprobar Prestamo",
             "Devolver libro",
             "Renovar Prestamo",
             "Ver Prestamo"
         };
         int seleccion = JOptionPane.showOptionDialog(null, "Gestionar Préstamos", "Opciones",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

         
         switch (seleccion) {
             case 0:
                 aprobarPrestamo();
                 break;
             case 1:
                 devolverLibro();
                 break;
             case 2:
                 renovarPretamo();
                 break;
             case 3:
                 verTodosPrestamos();
                 break;
             default:
                 throw new AssertionError();
         }
         
     }
     
     private void aprobarPrestamo(){
         
       String ISBN = JOptionPane.showInputDialog("ingrese el ISBN del Libro a aprobar el prestamo:");
       Libro libroSolicitado = buscarLibroPorISBN(ISBN);
         
       if (libroSolicitado != null && libroSolicitado.getCopiasDisponibles() > 0){
            String nombreUsuario = JOptionPane.showInputDialog("Ingrese el nombre del usuario solicitante:");
             Usuario usuario = buscarUsuarioPorNombre(nombreUsuario);
             
             if (usuarios != null){
            String fechaInicio = JOptionPane.showInputDialog("Ingrese la fecha de inicio del préstamo (dd/mm/aaaa):");
            String fechaDevolucion = JOptionPane.showInputDialog("Ingrese la fecha de devolución (dd/mm/aaaa):");
            
            Prestamo prestamo = new Prestamo(libroSolicitado, usuario, fechaInicio, fechaDevolucion, ISBN);
            prestamos.add(prestamo);
            
            libroSolicitado.prestarLibro();
            JOptionPane.showConfirmDialog(null, "libro prestado con exito");
         }else{
             JOptionPane.showConfirmDialog(null, "usuario no encontrado");
         } 
       }else{
             JOptionPane.showMessageDialog(null, "No se encontró un préstamo pendiente para ese libro");
       } 
     }
     
     private void devolverLibro(){
         
         String ISBN = JOptionPane.showInputDialog("ingrese el ISBN del Libro a aprobar el prestamo:");
         Prestamo prestamoEncontrado = buscarPrestamoPorISBN(ISBN);
         
         if (prestamoEncontrado != null && prestamoEncontrado.getEstado().equals("pendiente")){
             prestamoEncontrado.devolver();
             JOptionPane.showMessageDialog(null, "Libro devuelto con éxito");
         }else{
             JOptionPane.showMessageDialog(null, "no se encontro el libro");
         }
         
     }
     
     private void renovarPretamo(){
         
         String ISBN = JOptionPane.showInputDialog("ingrese el ISBN del Libro a aprobar el prestamo:");
         Prestamo prestamoEncontrado = buscarPrestamoPorISBN(ISBN);
         
        if (prestamoEncontrado != null && prestamoEncontrado.getEstado().equals("pendiente")){
             
             String nuevaFechaDevolucion = JOptionPane.showInputDialog("ingrese la nueva fecha de devolucion (dd/mm/aaaa):");
             prestamoEncontrado = new Prestamo(prestamoEncontrado.getLibro(),prestamoEncontrado.getUsuario(),
             prestamoEncontrado.getUsuario().getNombre(), nuevaFechaDevolucion);
             
             JOptionPane.showMessageDialog(null, "Préstamo renovado con éxito");
        } else {
             JOptionPane.showMessageDialog(null, "No se encontró un préstamo pendiente para ese libro");
    }
         
     }
     
     private void verTodosPrestamos(){
          StringBuilder prestamosStr = new StringBuilder("Préstamos en el sistema:\n");
            for (Prestamo prestamo : prestamos) {
                prestamosStr.append("Libro: ").append(prestamo.getLibro().getTitulo())
                       .append(", Usuario: ").append(prestamo.getUsuario().getNombre())
                       .append(", Estado: ").append(prestamo.getEstado())
                       .append(", Fecha de devolución: ").append(prestamo.getEstado().equals("devuelto") ? "Devuelto" : prestamo.getFechaDevolucion())
                       .append("\n");
            }
            JOptionPane.showMessageDialog(null, prestamosStr.toString());
     }
     
     private Libro buscarLibroPorISBN(String ISBN){
         for (Libro libro : catalogo) {
             if (libro.getISBN().equals(ISBN)){
                 return libro;
             }
         }
         return null;
     }
     
     private Usuario buscarUsuarioPorNombre(String nombre){
         for (Usuario usuario : usuarios) {
             if (usuario.getNombre().equals(nombre)){
                 return usuario;
             }
         }
         return null;
     }
     
     private Prestamo buscarPrestamoPorISBN(String ISBN){
         for (Prestamo prestamo : prestamos) {
             if (prestamo.getLibro().getISBN().equals(ISBN) && prestamo.getEstado().equals("pendiente")){
                 return prestamo;
             }
         }
         return null;
     }
    
    public void verPrestamos(Usuario usuario) {
        StringBuilder prestamosStr = new StringBuilder("Préstamos del usuario:\n");
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().equals(usuario)) {
                prestamosStr.append("Libro: ").append(prestamo.getLibro().getTitulo())
                        .append(", Estado: ").append(prestamo.getEstado())
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, prestamosStr.toString());
    }
    
    
    public static void main(String[] args){
        Biblioteca b = new Biblioteca();
        b.registrarUsuario();
        
        Usuario u = b.login();
        if (u !=null) {
            b.menuBibliotecario(u);
        }
    }
    
}
