
package ejercicioDeLosFicheros;

public class Usuario {
    
    private String nombre;
    private String identificacion;
    private String tipoUsuario;
    private String correoElectronico;
    private String contraseña;

    public Usuario(String nombre, String identificacion, String tipoUsuario, String correoElectronico, String contraseña) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipoUsuario = tipoUsuario;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean autenticacion(String contraseña){
        return this.contraseña.equals(contraseña);
    }  

    public String toFileString() {
        return "Usuario{" + "nombre=" + nombre + ", identificacion=" + identificacion + ", tipoUsuario=" + tipoUsuario + ", correoElectronico=" + correoElectronico + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    public static Usuario fromFileString(String fileString){
        String[] partes = fileString.split(",");
        return new Usuario(partes[0], partes[1], partes[2], partes[3], partes[4]);
    }
    
    
    
    
    
}
