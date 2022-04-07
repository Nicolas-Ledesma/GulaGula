package com.gulagula.gulagula.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Ingrediente {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private Enum sabor;
    private Enum tipo;
    private Enum categoria;
    private Enum temperatura;

    public Ingrediente() {
    }

    public Ingrediente(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the sabor
     */
    public Enum getSabor() {
        return sabor;
    }

    /**
     * @param sabor the sabor to set
     */
    public void setSabor(Enum sabor) {
        this.sabor = sabor;
    }

    /**
     * @return the tipo
     */
    public Enum getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Enum tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the categoria
     */
    public Enum getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Enum categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the temperatura
     */
    public Enum getTemperatura() {
        return temperatura;
    }

    /**
     * @param temperatura the temperatura to set
     */
    public void setTemperatura(Enum temperatura) {
        this.temperatura = temperatura;
    }

}
