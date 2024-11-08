package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Persona> listar() {
        List<Persona> datos = new ArrayList<>();
        String sql = "SELECT * FROM usuarios"; // SQL para listar todos los usuarios
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt("id"));         
                p.setNom(rs.getString("nombre"));  
                p.setCorreo(rs.getString("correo")); 
                p.setTel(rs.getString("telefono")); 
                datos.add(p); 
            }
        } catch (Exception e) {
            e.printStackTrace(); // Mejora: log de errores
        } finally {
            cerrarConexion();
        }
        return datos;
    }
    
    public int agregar(Persona p) {
        String sql = "INSERT INTO usuarios (nombre, correo, telefono) VALUES (?, ?, ?)"; 
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTel());   
            return ps.executeUpdate(); // Retorna el número de filas afectadas
        } catch (Exception e) {
            e.printStackTrace(); // Mejora: log de errores
            return 0; // En caso de error, retorna 0
        } finally {
            cerrarConexion();
        }
    }

    public int Actualizar(Persona p) {
        String sql = "UPDATE usuarios SET nombre=?, correo=?, telefono=? WHERE id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom()); 
            ps.setString(2, p.getCorreo()); 
            ps.setString(3, p.getTel());    
            ps.setInt(4, p.getId()); // Establecemos el ID para actualizar el usuario correcto
            return ps.executeUpdate(); // Retorna el número de filas afectadas
        } catch (Exception e) {
            e.printStackTrace(); // Mejora: log de errores
            return 0; // En caso de error, retorna 0
        } finally {
            cerrarConexion();
        }  
    }
    
public void delete(int id) {
    if (id <= 0) {
        System.out.println("ID no válido: " + id);
        return; // Evita ejecutar el DELETE si el ID no es válido
    }

    String sql = "DELETE FROM usuarios WHERE id=?";
    try {
        con = conectar.getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);  // Establecemos el ID del usuario a eliminar
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Usuario con ID " + id + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró un usuario con ID: " + id);
        }
    } catch (Exception e) {
        e.printStackTrace(); // Imprime los errores si hay
    } finally {
        cerrarConexion(); // Cerrar la conexión
    }
}


    // Método para cerrar la conexión, PreparedStatement y ResultSet
    private void cerrarConexion() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace(); // Mejora: log de errores
        }
    }
}
