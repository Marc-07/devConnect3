package com.devconnect.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String password;

    // 🔄 Relación uno-a-muchos con Vacante
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacante> vacantes = new ArrayList<>();

    // Constructores
    public Usuario() {}

    public Usuario(String nombre, String apellido, String correo, String username, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.username = username;
        this.password = password;
    }

    // 🔄 Método para manejar la relación bidireccional
    public void agregarVacante(Vacante vacante) {
        vacantes.add(vacante);
        vacante.setUsuario(this);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    // 🔄 Getter para vacantes
    public List<Vacante> getVacantes() { return vacantes; }

    // 🔄 Setter para vacantes
    public void setVacantes(List<Vacante> vacantes) { this.vacantes = vacantes; }
}


