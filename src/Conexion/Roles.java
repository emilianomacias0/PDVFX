package Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 * Created by fknrk on 7/9/2017.
 */
public class Roles extends Conexion {

    public Vector getRoles() throws SQLException {
        Vector roles = new Vector();
        Statement st = con.createStatement();
        String Consulta = "Select * FROM roles";
        ResultSet rs = null;
        rs = st.executeQuery(Consulta);
        while(rs.next()){
            roles.add(rs.getString("rol"));
        }


        st.close();
        return roles;
    }

    public int getRolId(String rol) throws SQLException {

        int idRole = 0;
        Statement st = con.createStatement();
        String Consulta = "Select idrole FROM roles WHERE rol = '" + rol + "';";
        ResultSet rs = null;
        rs = st.executeQuery(Consulta);
        while(rs.next()){
            idRole = rs.getInt("idrole");
        }
        return idRole;
    }

   /* public static void main(String[] args) throws SQLException {
        Roles roles = new Roles();
        System.out.println(roles.getRolId("SU"));
    }*/
}
