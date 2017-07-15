package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by fknrk on 7/9/2017.
 */
public class RolesUsuario extends Conexion {

    public Boolean insertaRolesUsuario(int idUsuario,int idRole) throws SQLException {
        Boolean insert = false;
        PreparedStatement ps = con.prepareStatement("INSERT INTO rolesusuarios (idUsuario,idRol) VALUES (?,?)");
        ps.setInt(1, idUsuario);
        ps.setInt(2, idRole);
        int res = ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();

        if (res > 0) {
            System.out.println("Rol insertado");
            insert = true;
        }
        return  insert;
    }


   /* public static void main(String[] args) throws SQLException {
        RolesUsuario ru = new RolesUsuario();
        ru.insertaRolesUsuario(1,3);
    }*/


}
