package modelo;

public class Persona {
    
    private int id;          // Cambiar a private para encapsulamiento
    private String nom;      // Cambiar a private para encapsulamiento
    private String correo;   // Cambiar a private para encapsulamiento
    private String tel;      // Cambiar a private para encapsulamiento

    // Constructor por defecto
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(int id, String nom, String correo, String tel) {
        this.id = id;
        this.nom = nom;
        this.correo = correo;
        this.tel = tel;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
