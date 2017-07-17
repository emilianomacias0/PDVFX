package Conexion;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static final String USERNAME = "root";
    public static final String PASSWORD = "macias";
    public static final String HOST ="localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "pdv";
    public static final String CLASSNAME ="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;

    public java.sql.Connection con;
    public Conexion(){
        try{
            Class.forName(CLASSNAME);
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch(ClassNotFoundException e){
            System.out.println("Error "+ e);
        }catch(SQLException e){
            System.out.println("Error" + e);
        }
    }

    /*public static void main(String[] args) throws SQLException {
        Usuarios con = new Usuarios();
        con.InsertaUsuario("Emiliano","Macias","Lucas","emi","macias");
        System.out.println();
    }*/
}