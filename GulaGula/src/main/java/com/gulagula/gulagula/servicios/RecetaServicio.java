package com.gulagula.gulagula.servicios;

import com.gulagula.gulagula.entidades.Ingrediente;
import com.gulagula.gulagula.entidades.Receta;
import com.gulagula.gulagula.repositorios.RecetaRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaServicio {

    private RecetaRepositorio recetaRepositorio;

    @Autowired
    public void RecetaRepositorio(RecetaRepositorio recetaRepositorio) {
        this.recetaRepositorio = recetaRepositorio;
    }

    @Transactional
    public Receta guardarReceta(Receta receta) throws Exception {
        buscarReceta(receta.getId());
        validacion(receta);
        recetaRepositorio.save(receta);
        return receta;
    }

    @Transactional
    public Receta editarReceta(Receta receta) throws Exception {
        validacion(receta);
        recetaRepositorio.findById(receta.getId());
        recetaRepositorio.save(receta);
        return receta;
    }

    @Transactional
    public List<Receta> listarRecetas() {
        return recetaRepositorio.findAll();
    }

//    @Transactional
//    public List<Receta> listarRecetasPorIngredientes(List<Ingrediente> ingredientes) {
//        List<Receta> receta1 = recetaRepositorio.findAll();
//        
//        for (int i = 0; i < receta1.size(); i++) {
//            if (receta1.get(i).getIngredientes().equals(ingredientes)) {
//                
//            }
//        }
//        
//        
//        
//        return receta1;
//    }

    @Transactional
    public Receta buscarReceta(String id) throws Exception {

        Optional<Receta> resp = recetaRepositorio.findById(id);
        if (resp.isPresent()) {
            return resp.get();
        } else {
            throw new Exception("El Libro no se encuentra");
        }
    }

    @Transactional
    public void validacion(Receta receta) throws Exception {

        if (receta.getIngredientes() == null || receta.getIngredientes().isEmpty()) {
            throw new Exception("los ingredientes de la receta no pueden estar vacios");
        }
        if (receta.getTemp() == null) {
            throw new Exception("La temperatura de la receta no puede estar vacia");
        }
        if (receta.getSabor() == null) {
            throw new Exception("El sabor de la receta no puede estar vacio");
        }
        if (receta.getNombre() == null || receta.getNombre().isEmpty()) {
            throw new Exception("El nombre de la receta no puede estar vacio");
        }
        if (receta.getCategoria() == null) {
            throw new Exception("La categoria de la receta no puede estar vacia");
        }
        if (receta.getTipo() == null) {
            throw new Exception("El tipo de la receta no puede estar vacia");
        }
        if (receta.getTiempoDeCoccion() == null || receta.getTiempoDeCoccion().isEmpty()) {
            throw new Exception("El tiempo de cocción de la receta no puede estar vacia");
        }
    }
}
