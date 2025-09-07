package com.albertoandraul.arfit.model;
// Clase pojo que representa a un usuario en el sistema
import jakarta.persistence.*;  // Importamos las anotaciones de JPA

@Entity // Indica que esta clase es una entidad de JPA
@Table(name = "users") // Especifica el nombre de la tabla en la base de datos
public class User {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia de generación de la clave primaria
    private Long id;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
