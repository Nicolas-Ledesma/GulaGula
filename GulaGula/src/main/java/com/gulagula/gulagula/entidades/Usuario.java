package com.gulagula.gulagula.entidades;

import com.gulagula.gulagula.enumeradores.Rols;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String clave;
    private String email;
    private Boolean alta;
    
//    @Enumerated(EnumType.STRING)
    private Rols role;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String clave, String email, Boolean alta, Rols role) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.email = email;
        this.alta = alta;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Rols getRole() {
        return role;
    }

    public void setRole(Rols role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", email=" + email + ", alta=" + alta + ", role=" + role + '}';
    }

    
}
