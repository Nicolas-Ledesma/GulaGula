package com.gulagula.gulagula.entidades;

import com.gulagula.gulagula.enumeradores.Estado;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Plato {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
//    private Estado estado;
    @OneToOne
    private Receta receta;
    @OneToOne
    private Imagen imagen;

    public Plato() {
    }

    public Plato(String id, String nombre, Receta receta, Imagen imagen) {
        this.id = id;
        this.nombre = nombre;
        this.receta = receta;
        this.imagen = imagen;
    }

<<<<<<< HEAD
=======

>>>>>>> aef7f7533f74311ec0ba9981f981ef89e7ea172a
    //public Estado getEstado() {
    //return estado;
    //}
//    public void setEstado(Estado estado) {
//        this.estado = estado;
//    }
<<<<<<< HEAD
=======


>>>>>>> aef7f7533f74311ec0ba9981f981ef89e7ea172a
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

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Plato{" + "id=" + id + ", nombre=" + nombre + ", receta=" + receta + ", imagen=" + imagen + '}';
    }

}
