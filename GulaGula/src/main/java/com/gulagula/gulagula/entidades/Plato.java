package com.gulagula.gulagula.entidades;

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
    private Enum sabor;
    private Enum temperatura;
    private Enum tipo;
    private Enum categoria;

    public Plato() {
    }

    public Plato(String id, String nombre, Receta receta, Imagen imagen) {
        this.id = id;
        this.nombre = nombre;
        this.receta = receta;
        this.imagen = imagen;
    }

    @OneToOne
    private Receta receta;
    private Imagen imagen;

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

    public Enum getSabor() {
        return sabor;
    }

    public void setSabor(Enum sabor) {
        this.sabor = sabor;
    }

    public Enum getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Enum temperatura) {
        this.temperatura = temperatura;
    }

    public Enum getTipo() {
        return tipo;
    }

    public void setTipo(Enum tipo) {
        this.tipo = tipo;
    }

    public Enum getCategoria() {
        return categoria;
    }

    public void setCategoria(Enum categoria) {
        this.categoria = categoria;
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

  
}
