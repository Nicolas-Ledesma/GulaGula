package com.gulagula.gulagula.entidades;

import com.gulagula.gulagula.enumeradores.Categoria;
import com.gulagula.gulagula.enumeradores.Sabor;
import com.gulagula.gulagula.enumeradores.Temperatura;
import com.gulagula.gulagula.enumeradores.Tipo;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Receta {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private List<String> instrucciones;

    @OneToMany
    private List<Ingrediente> ingredientes;
    private Temperatura temp;
    private Sabor sabor;
    private String nombre;
    private Categoria categoria;
    private Tipo tipo;
    private String tiempoDeCoccion;

}
