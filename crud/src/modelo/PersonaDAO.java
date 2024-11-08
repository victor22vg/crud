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
        String sql = "SELECT * FROM persona";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));         // Primer columna: ID
                p.setNom(rs.getString(2));      // Segunda columna: Nombre
                p.setCorreo(rs.getString(3));   // Tercera columna: Correo
                p.setTel(rs.getString(4));      // Cuarta columna: Teléfono
                datos.add(p);  // Añadir el objeto Persona a la lista
            }
        } catch (Exception e) {
            e.printStackTrace();  // Imprimir el error en la consola
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return datos;
    }
    
    public int agregar(Persona p) {
        String sql = "INSERT INTO persona (Nombres, Correo, Telefono) VALUES (?, ?, ?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTel());
            return ps.executeUpdate(); // Retornar el resultado de la ejecución
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir el error en la consola
            return 0; // Retornar 0 en caso de error
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int Actualizar(Persona p) {
        String sql = "UPDATE persona SET Nombres=?, Correo=?, Telefono=? WHERE Id=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getCorreo());
            ps.setString(3, p.getTel());
            ps.setInt(4, p.getId()); // Cambiar el índice a 4
            return ps.executeUpdate(); // Retornar el resultado de la ejecución
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir el error en la consola
            return 0; // Retornar 0 en caso de error
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }  
    }
            public void delete(int id){
                String sql="delete from persona where Id="+id;
                try {
                    con=conectar.getConnection();
                    ps=con.prepareStatement(sql);
                    ps.executeUpdate();
                } catch (Exception e) {
                }
                
        }
    
}
