package com.gulagula.gulagula.servicios;

import com.gulagula.gulagula.entidades.Plato;
import com.gulagula.gulagula.entidades.Receta;
import com.gulagula.gulagula.repositorios.PlatoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatoServicio {

    private PlatoRepositorio platoRepositorio;

    private RecetaServicio recetaServicio;

    public PlatoServicio(RecetaServicio recetaServicio) {
        this.recetaServicio = recetaServicio;
    }

    @Autowired
    public PlatoServicio(PlatoRepositorio platoRepositorio) {
        this.platoRepositorio = platoRepositorio;
    }

    @Transactional
    public Plato guardarPlato(Plato plato) throws Exception {
        validarGuardarPlato(plato);
        return platoRepositorio.save(plato);
    }

    @Transactional
    public Plato editarPlato(Plato plato) throws Exception {
        validaEditarPlato(plato);
        return platoRepositorio.save(plato);
    }
// Valida los plato guardados 

    private void validarGuardarPlato(Plato plato) throws Exception {
        if (plato.getNombre() == null || plato.getNombre().isEmpty()) {
            throw new Exception("El nombre del Plato es nulo o vacio");
        } else if (plato.getReceta() == null) {
            throw new Exception("La receta no tiene valor");
        }

    }
// Valida los platos 

    private void validaEditarPlato(Plato platoAModificar) throws Exception {
        // los datos que llegan por parametro son los nuevos o ingresados. No son los que estan en base
        Optional<Plato> optionalPlatoGuardado = platoRepositorio.findById(platoAModificar.getId());

        if (optionalPlatoGuardado.isPresent()) {
            Plato platoGuardado = optionalPlatoGuardado.get();
            hayCambios(platoGuardado, platoAModificar);
        } else {
            throw new Exception("No se encontro plato a editar");
        }

    }

    private void hayCambios(Plato platoGuardado, Plato platoAModificar) throws Exception {
        if (platoAModificar.getNombre() == null || platoAModificar.getNombre().isEmpty()) {
            throw new Exception("El nombre del plato no puede estar vacio");
        }
        recetaServicio.editarReceta(platoAModificar.getReceta());
    }

    @Transactional
    public List<Plato> listarPlatoPorReceta(List<Receta> recetas) {
        List<Plato> platoBd = platoRepositorio.findAll();
        int contadorPlato = 0;
        List<Plato> platoMas3 = new ArrayList<Plato>();
        do {
            for (int i = 0; i < platoBd.size(); i++) { // Ingresa con cada una de las platos de la base de datos
                contadorPlato++;

                for (int j = 0; j < recetas.size(); j++) { // Ingresa a iterar cada una de los recetas ingresados en la pagina

                    if (platoBd.get(i).getReceta() == recetas.get(j)) { //Compara recetas ingresado 1 con cada uno de los platos de la recetas de arriba si es igual cuenta
                        platoMas3.add(platoBd.get(i));

                    } //Cierra for recetas de plato

                } //Cierra for ingredientes ingresados por parametro
            } //Cierra for receta "Investigar streams"

        } while (contadorPlato < platoBd.size());//poner un contador y que de vuelta hasta el tamaño de recetas
        return platoMas3;
    }

    @Transactional
    public List<Plato> listarPlato() {
        return platoRepositorio.findAll();
    }
}
