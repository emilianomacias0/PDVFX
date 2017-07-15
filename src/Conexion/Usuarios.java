package Conexion;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Usuarios extends Conexion{

    public int InsertaUsuario(String nombre,String apellidoP,String apellidoM,String usuario, String contrasena) throws SQLException {
        int userId = 0;
        String generatedColumns[] = { "ID" };
        PreparedStatement ps = con.prepareStatement("INSERT INTO usuarios (nombre,apellidoP,apellidoM,usuario,contrasena) VALUES (?,?,?,?,?)",generatedColumns);
        ps.setString(1, nombre);
        ps.setString(2, apellidoP);
        ps.setString(3, apellidoM);
        ps.setString(4, usuario);
        ps.setString(5, contrasena);
        int res = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {
            System.out.println("Usuario registrado");
            long id = rs.getLong(1);
            System.out.println("Inserted ID -" + id); // display inserted record
            return (int)id;
        }

        return userId;
    }

    public int editaUsuario(String nombre,String apellidoP,String apellidoM,String usuario, String contrasena) throws SQLException {
        int userId = 0;
        String generatedColumns[] = { "ID" };
        PreparedStatement ps = con.prepareStatement("UPDATE");

        int res = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if (rs.next()) {

        }

        return userId;
    }



    public Boolean validaUsuario(String usuario,String contrasena) throws SQLException {
        Boolean exists = false;
        Statement st = con.createStatement();
        String Consulta = "Select * FROM usuarios WHERE usuario= '" + usuario +"' AND contrasena = '" + contrasena + "';";
        ResultSet rs = null;
        rs = st.executeQuery(Consulta);
        while(rs.next()){
            exists = true;
        }
        st.close();
        return exists;
    }

    public Map<String, String> getUser(String idUsuario) throws SQLException {
        Boolean exists = false;
        Map<String,String> datos =  new HashMap<String, String>();
        Statement st = con.createStatement();
        String Consulta = "SELECT pdv.usuarios.*,pdv.roles.rol FROM pdv.usuarios,pdv.rolesusuarios,pdv.roles \n" +
                "WHERE pdv.usuarios.idUsuario = pdv.rolesusuarios.idUsuario \n" +
                "AND  pdv.rolesusuarios.idRol =  pdv.roles.idRole " +
                "AND pdv.usuarios.idUsuario ='"+idUsuario+"';";
        ResultSet rs = null;
        rs = st.executeQuery(Consulta);
        while(rs.next()){
            datos.put("Nombre",rs.getString("nombre"));
            datos.put("ApellidoP",rs.getString("apellidoP"));
            datos.put("ApellidoM",rs.getString("apellidoM"));
            datos.put("Usuario",rs.getString("usuario"));
            datos.put("Rol",rs.getString("rol"));
        }
        st.close();
        return datos;
    }

   /* public static void main(String[] args) throws SQLException {
        Usuarios user = new Usuarios();
        Map<String,String> datos = user.getUser("2");
        System.out.println(datos.get("Nombre")+ " " +datos.get("ApellidoP"));
    }*/
}