package Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author emi
 */
public class Consultas extends Conexion {

    public boolean Autenticacion(String user, String pass) throws SQLException{
        Statement st = con.createStatement();
        String Consulta = "Select * FROM usuarios";
        ResultSet rs = null;
        rs = st.executeQuery(Consulta);
        while(rs.next()){
            if (user.equals(rs.getString("usuario")) && pass.equals(rs.getString("contrasena"))) {
                return true;
            }
        }
        return false;
    }

    public String CreaTabla(String consulta){
        String res = "";

        try {
            Statement st = con.createStatement();
            st.execute(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
            res = e.getMessage();
        }
        return res;
    }

    public static void main(String[] args) throws SQLException {
        Consultas con = new Consultas();
        con.CreaTabla("Create Table ");
        //System.out.println(con.Autenticacion("emiliano", "1234"));
    }
}