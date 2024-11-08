package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    Connection conectar = null;
    String usuario ="admin";
    String password ="Victor2202";
    String bd ="crud_db";
    String ip ="crud-database.cnwwkoq0cbja.us-east-1.rds.amazonaws.com";
    String puerto = "3306";
   
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
   
    public Connection conectar(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar=DriverManager.getConnection(cadena, usuario, password);
            JOptionPane.showMessageDialog(null, "Se conectó a la base de datos");
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conectó a la base de datos, error"+e.toString());
        }
     return conectar;
 }

    Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}