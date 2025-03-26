package com.devconnect.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vacantes")
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String empresa;

    @Column(nullable = false, length = 100)
    private String ubicacion;

    @Column(nullable = false)
    private double salario;

    @Column(name = "contrato", nullable = false, length = 50)
    private String contrato;

    @Column(length = 1000)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_vacante_usuario"))
    private Usuario usuario;

    // Constructores
    public Vacante() {
        // Constructor por defecto necesario para JPA
    }

    public Vacante(String titulo, String empresa, String ubicacion,
                   double salario, String contrato, String descripcion,
                   Usuario usuario) {
        this.titulo = titulo;
        this.empresa = empresa;
        this.ubicacion = ubicacion;
        this.salario = salario;
        this.contrato = contrato;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if(titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        this.titulo = titulo.trim();
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        if(empresa == null || empresa.trim().isEmpty()) {
            throw new IllegalArgumentException("La empresa no puede estar vacía");
        }
        this.empresa = empresa.trim();
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        if(ubicacion == null || ubicacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La ubicación no puede estar vacía");
        }
        this.ubicacion = ubicacion.trim();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if(salario <= 0) {
            throw new IllegalArgumentException("El salario debe ser mayor que cero");
        }
        this.salario = salario;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        if(contrato == null || contrato.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de contrato no puede estar vacío");
        }
        this.contrato = contrato.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion != null ? descripcion.trim() : null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        if(usuario == null) {
            throw new IllegalArgumentException("La vacante debe tener un usuario asociado");
        }
        this.usuario = usuario;
    }

    // equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacante)) return false;
        Vacante vacante = (Vacante) o;
        return Double.compare(vacante.salario, salario) == 0 &&
                Objects.equals(id, vacante.id) &&
                Objects.equals(titulo, vacante.titulo) &&
                Objects.equals(empresa, vacante.empresa) &&
                Objects.equals(ubicacion, vacante.ubicacion) &&
                Objects.equals(contrato, vacante.contrato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, empresa, ubicacion, salario, contrato);
    }

    // toString mejorado
    @Override
    public String toString() {
        return "Vacante{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", salario=" + salario +
                ", contrato='" + contrato + '\'' +
                ", usuario=" + (usuario != null ? usuario.getUsername() : "null") +
                '}';
    }
}