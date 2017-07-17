package Registrar;

/**
 * Created by fknrk on 7/12/2017.
 */
public class User {
    private int idUsuario;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String usuario;
    private String contrasena;
    private String creadoEl;

    public User(int idUsuario, String nombre, String apellidoP, String apellidoM, String usuario, String contrasena,String creadoEl) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.creadoEl = creadoEl;
    }



    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCreadoEl() {
        return creadoEl;
    }

    public void setCreadoEl(String creadoEl) {
        this.creadoEl = creadoEl;
    }
}
