
package apppruebakeyners;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class AppPruebaKeyners { 
    
     private final Map<Integer, Libro> catalogo = new HashMap<>();
     private final Map<String, Usuario> usuarios = new HashMap<>();
     private final List<Prestamo> historialPrestamos = new ArrayList<>();
    
   public static void main(String[] args) {
        
      AppPruebaKeyners bilios = new AppPruebaKeyners();
     
         String[] opciones=
        {
            "1. Agregar Libro.",
            "2. Reservar Libro.",
            "3. Prestar Libro",
            "4. Devolver Libro",
            "5. Listar Libros",
            "6. Guardar el Historial de los Prestamos",
            "7. Cargar el Historial de los prestamos",
            "8. Salir"
        };
         
        int opcion;
        
        do
        {
            opcion = JOptionPane.showOptionDialog(null,"Seleccione una opcion","Sistema Biblioteca",
                    JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opciones,opciones[0]);
            try {
                switch (opcion) {
                case 0:
                    bilios.agregarLibro();
                    break;
                case 1:
                    bilios.reservarLibro();
                    break;
                case 2:
                    bilios.prestarLibro();
                    break;
                case 3:
                    bilios.devolverLibro();
                    break;
                case 4:
                    bilios.listarLibros().forEach(libro ->
                    JOptionPane.showMessageDialog(null, libro.toString()));
                    break;
                case 5:
                    bilios.guardarHistorialPrestamos("historial.dat");
                    break;
                case 6:
                    bilios.cargarHistorialPrestamos("historial.dat");
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null,  "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    break;
            }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } while (opcion !=7);
    }
     
    private  void agregarLibro() 
    {
        try {
            String titulo = JOptionPane.showInputDialog("Ingrese el nombre del libro que desea agregar:");
            String autor = JOptionPane.showInputDialog("Ingrese el autor del libro que desea agregar:");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro que desea agregar:"));
           // agregarLibro(titulo,autor,id);
           Libro libro = new Libro(id, titulo, autor, Libro.Estado.PRESTADO);
           agregarLibro(libro);
           JOptionPane.showMessageDialog(null, "El libro ha sido registrado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
    
    private  void reservarLibro()
    {
            try {
                
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro que desea reservar:"));
                String usuarioNombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
               // agregarLibro(titulo,autor,id);
               reservarLibro(id, usuarioNombre);
                JOptionPane.showMessageDialog(null, "El libro ha sido registrado con exito");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al reservar libro: " + e.getMessage());
            }
    }
    
    private  void prestarLibro()
    {
       try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro que desea prestar:"));
            String usuarioNombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
            prestarLibro(id, usuarioNombre);
            JOptionPane.showMessageDialog(null, "Libro prestado con éxito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al prestar libro: " + e.getMessage());
        }
    }
    
    public  void devolverLibro() {
         try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del libro que desea devolver:"));
            String usuarioNombre = JOptionPane.showInputDialog("Ingrese el nombre del usuario:");
            devolverLibro(id, usuarioNombre);
            JOptionPane.showMessageDialog(null, "Libro devuelto con éxito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al devolver libro: " + e.getMessage());
        }
    }
    
    public synchronized void agregarLibro(Libro libro) {
        catalogo.put(libro.id(), libro);
    }

    public synchronized void reservarLibro(int libroId, String usuarioNombre) throws Exception {
        Libro libro = catalogo.get(libroId);
        Usuario usuario = usuarios.get(usuarioNombre);
        if (libro == null || usuario == null) throw new Exception("Libro o usuario no encontrado");
        if (libro.estado() != Libro.Estado.DISPONIBLE) throw new Exception("Libro no disponible");
        catalogo.put(libroId, new Libro(libro.id(), libro.titulo(), libro.autor(), Libro.Estado.RESERVADO));
    }

    public synchronized void prestarLibro(int libroId, String usuarioNombre) throws Exception {
        Libro libro = catalogo.get(libroId);
        Usuario usuario = usuarios.get(usuarioNombre);
        if (libro == null || usuario == null) throw new Exception("Libro o usuario no encontrado");
        if (libro.estado() != Libro.Estado.RESERVADO) throw new Exception("Libro no reservado");
        catalogo.put(libroId, new Libro(libro.id(), libro.titulo(), libro.autor(), Libro.Estado.PRESTADO));
        usuario.listaLibrosPrestados().add(libro);
        historialPrestamos.add(new Prestamo(historialPrestamos.size() + 1, libro, usuario, new Date(), null));
    }

    public synchronized void devolverLibro(int libroId, String usuarioNombre) throws Exception {
        Libro libro = catalogo.get(libroId);
        Usuario usuario = usuarios.get(usuarioNombre);
        if (libro == null || usuario == null) throw new Exception("Libro o usuario no encontrado");
        if (libro.estado() != Libro.Estado.PRESTADO) throw new Exception("Libro no prestado");
        catalogo.put(libroId, new Libro(libro.id(), libro.titulo(), libro.autor(), Libro.Estado.DISPONIBLE));
        usuario.listaLibrosPrestados().remove(libro);
        for (Prestamo prestamo : historialPrestamos) {
            if (prestamo.libro().id() == libroId && prestamo.usuario().nombre().equals(usuarioNombre)) {
                historialPrestamos.set(historialPrestamos.indexOf(prestamo), 
                    new Prestamo(prestamo.id(), prestamo.libro(), prestamo.usuario(), prestamo.fechaPrestamo(), new Date()));
                break;
            }
        }
    }

    public synchronized List<Libro> listarLibros() {
        return new ArrayList<>(catalogo.values());
    }

    public synchronized void guardarHistorialPrestamos(String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(historialPrestamos);
        }
    }

    public synchronized void cargarHistorialPrestamos(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            List<Prestamo> prestamos = (List<Prestamo>) ois.readObject();
            historialPrestamos.clear();
            historialPrestamos.addAll(prestamos);
        }
    }
    
    
}
