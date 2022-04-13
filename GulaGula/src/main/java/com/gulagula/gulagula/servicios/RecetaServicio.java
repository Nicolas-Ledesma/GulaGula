package com.gulagula.gulagula.servicios;

import com.gulagula.gulagula.entidades.Ingrediente;
import com.gulagula.gulagula.entidades.Receta;
import com.gulagula.gulagula.repositorios.RecetaRepositorio;
import java.util.ArrayList;
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
        if (recetaRepositorio.existsById(receta.getId())) {
            throw new Exception("la receta ya se encuentra cargada");
        }
        validacion(receta);
        recetaRepositorio.save(receta);
        return receta;
    }

    @Transactional
    public Receta editarReceta(Receta receta) throws Exception {
        validacion(receta);
        buscarReceta(receta.getId());
        recetaRepositorio.save(receta);
        return receta;
    }

    @Transactional
    public List<Receta> listarRecetas() {
        return recetaRepositorio.findAll();
    }

    /**
     * Este metodo recibe una lista de ingredientes, busca las recetas que
     * contengan esos ingredientes y devuelve una lista de recetas.-
     * @param ingredientes
     * @return 
     */
    @Transactional
    public List<Receta> listarRecetasPorIngredientes(List<Ingrediente> ingredientes) {
        List<Receta> recetaBd = recetaRepositorio.findAll();
        int contadorIngredientes = 0;
        int contadorIngredientesEncontrados = 0;
        List<Receta> recetasMas3 = new ArrayList<Receta>();
        do {
            for (int i = 0; i < ingredientes.size(); i++) { // Ingresa con cada uno de los ingredientes ingresados en la pag
                contadorIngredientes++;
                for (int j = 0; j < recetaBd.size(); j++) { // Ingresa a iterar cada una de las recetas

                    for (int k = 0; k < recetaBd.get(j).getIngredientes().size(); k++) { // Ingresa a cada uno de los ingredientes de cada receta

                        if (ingredientes.get(i) == recetaBd.get(j).getIngredientes().get(k)) { //Compara ingrediente ingresado 1 con cada uno de los ingredientes de la receta de arriba si es igual cuenta
                            contadorIngredientesEncontrados++;
                        }
                    } //Cierra for ingredientes
                    if (contadorIngredientesEncontrados >= 3) { //si hay 3 o mas ingredientes guarda en la lista a devolver
                        recetasMas3.add(recetaBd.get(j));
                    }
                } //Cierra for receta
            } //Cierra for ingredientes "Investigar streams"
        } while (contadorIngredientes < recetaBd.size());//poner un contador y que de vuelta hasta el tamaño de ingredientes
        return recetasMas3;
    }

    @Transactional
    public List<Receta> listarRecetasPorIngredientesMenos3(List<Ingrediente> ingredientes) {
        List<Receta> recetaBd = recetaRepositorio.findAll();
        int contadorIngredientes = 0;
        int contadorIngredientesEncontrados = 0;
        List<Receta> recetasMenos3 = new ArrayList<Receta>();
        do {
            for (int i = 0; i < ingredientes.size(); i++) { // Ingresa con cada uno de los ingredientes ingresados en la pag
                contadorIngredientes++;
                for (int j = 0; j < recetaBd.size(); j++) { // Ingresa a iterar cada una de las recetas

                    for (int k = 0; k < recetaBd.get(j).getIngredientes().size(); k++) { // Ingresa a cada uno de los ingredientes de cada receta

                        if (ingredientes.get(i) == recetaBd.get(j).getIngredientes().get(k)) { //Compara ingrediente ingresado 1 con cada uno de los ingredientes de la receta de arriba si es igual cuenta
                            contadorIngredientesEncontrados++;
                        }
                    } //Cierra for ingredientes
                    if (contadorIngredientesEncontrados > 0 && contadorIngredientesEncontrados < 3) { //si hay menos de tres pero mas de un ingrediente guarda en otra lista de receta
                        recetasMenos3.add(recetaBd.get(j));
                    }
                } //Cierra for receta
            } //Cierra for ingredientes
        } while (contadorIngredientes < recetaBd.size());//poner un contador y que de vuelta hasta el tamaño de recetas
        return recetasMenos3;
    }

    @Transactional
    public Receta buscarReceta(String id) throws Exception {

        Optional<Receta> resp = recetaRepositorio.findById(id);
        if (resp.isPresent()) {
            return resp.get();
        } else {
            throw new Exception("la receta no se encuentra");
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
