
package com.gulagula.gulagula.servicios;



import com.gulagula.gulagula.entidades.Ingrediente;
import com.gulagula.gulagula.repositorios.IngredienteRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteServicio {

    private IngredienteRepositorio ingredienteRepositorio;

    @Autowired
    public void IngredienteRepositorio(IngredienteRepositorio ingredienteRepositorio) {
        this.ingredienteRepositorio = ingredienteRepositorio;
    }

    @Transactional
    public Ingrediente guardarIngrediente(Ingrediente ingrediente) throws Exception {
        buscarIngrediente(ingrediente.getId());
        validacion(ingrediente);
        ingredienteRepositorio.save(ingrediente);
        return ingrediente;
    }

    @Transactional
    public Ingrediente editarIngrediente(Ingrediente ingrediente) throws Exception {
        validacion(ingrediente);
        ingredienteRepositorio.findById(ingrediente.getId());
        ingredienteRepositorio.save(ingrediente);
        return ingrediente;
    }

    @Transactional
    public List<Ingrediente> listarIngredientes() {
        return ingredienteRepositorio.findAll();
    }

    @Transactional
    public Ingrediente buscarIngrediente(String id) throws Exception {

        Optional<Ingrediente> resp = ingredienteRepositorio.findById(id);
        if (resp.isPresent()) {
            return resp.get();
        } else {
            throw new Exception("El Ingrediente no se encuentra");
        }
    }

    @Transactional
    public void validacion(Ingrediente ingrediente) throws Exception {

        if (ingrediente == null ) {
            throw new Exception("los ingredientes no pueden estar vacios");
        }
//        if (ingrediente.getTemperatura() == null) {
//            throw new Exception("La temperatura del ingrediente no puede estar vacia");
//        }
//        if (ingrediente.getSabor() == null) {
//            throw new Exception("El sabor del ingrediente no puede estar vacio");
//        }
        if (ingrediente.getNombre() == null || ingrediente.getNombre().isEmpty()) {
            throw new Exception("El nombre del ingrediente no puede estar vacio");
        }
        if (ingrediente.getCategoria() == null) {
            throw new Exception("La categoria del ingrediente no puede estar vacia");
        }
//        if (ingrediente.getTipo() == null) {
//            throw new Exception("El tipo de la ingrediente no puede estar vacia");
//        }
//        if (ingrediente.() == null || ingrediente.getTiempoDeCoccion().isEmpty()) {
//            throw new Exception("El tiempo de cocción de la ingrediente no puede estar vacia");
//        }
    }
}


