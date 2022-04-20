package com.gulagula.gulagula.controladores;

import com.gulagula.gulagula.entidades.Ingrediente;
import com.gulagula.gulagula.entidades.Receta;
import com.gulagula.gulagula.servicios.RecetaServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/receta")
public class RecetaControlador {
    
    private final RecetaServicio recetaServicio;

    public RecetaControlador(RecetaServicio recetaServicio) {
        this.recetaServicio = recetaServicio;
    }
    
     @GetMapping
    public String listarReceta(ModelMap model) {
        List<Receta> receta = recetaServicio.listarRecetas();
        model.addAttribute("receta", receta);
        return "receta/lista-receta";
    }

    @GetMapping("/form")
    public String mostrarFormulario(ModelMap model) {
        model.addAttribute("receta", new Receta());
        return "receta/receta-form";
    }

    @PostMapping("/form")
    public String procesarFormulario(@ModelAttribute Receta receta, ModelMap model, @RequestParam(required = false) List<Ingrediente> ingredientesId) {
        try {
            System.out.println(receta.toString());
            receta.getIngredientes().forEach(ingrediente -> System.out.println("ing:" + ingrediente.toString()));
            recetaServicio.guardarReceta(receta);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "receta/receta-form";
        }
        return "redirect:/receta";
    }

    
    @GetMapping("/editar")
    public String modificar(@RequestParam(value = "id") String id, ModelMap model) {
        try {
            model.put("receta", recetaServicio.buscarReceta(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "receta/editar-receta";
        }
        return "receta/editar-receta";
    }
    
    @PostMapping("/editar")
    public String modificarFormulario(@ModelAttribute Receta receta, ModelMap model) {
        try {
            recetaServicio.editarReceta(receta);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "receta/editar-receta";
        }
        return "redirect:/receta";
    }
}
