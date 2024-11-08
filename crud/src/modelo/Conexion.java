package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    // Variable para la conexión
    private Connection conectar = null;

    // Datos de conexión
    private final String usuario = "admin";
    private final String password = "Victor2202";
    private final String bd = "crud_db";
    private final String ip = "crud-database.cnwwkoq0cbja.us-east-1.rds.amazonaws.com";
    private final String puerto = "3306";  // Puerto MySQL por defecto

    // Cadena de conexión
    private final String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    // Método para conectar
    public Connection conectar() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            conectar = DriverManager.getConnection(cadena, usuario, password);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            // Error al cargar el driver
            JOptionPane.showMessageDialog(null, "Error al cargar el driver de MySQL: " + e.toString());
        } catch (SQLException e) {
            // Error al intentar conectarse a la base de datos
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.toString());
        }
        return conectar;
    }
    
       public Connection getConnection() {
        if (conectar == null) {
            conectar();
        }
        return conectar;
    }
}

