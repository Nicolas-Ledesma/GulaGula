package com.gulagula.gulagula.controladores;

import com.gulagula.gulagula.entidades.Plato;
import com.gulagula.gulagula.servicios.PlatoServicio;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/plato")
public class PlatoControlador {

    private final PlatoServicio platoServicio;

    public PlatoControlador(PlatoServicio platoServicio) {
        this.platoServicio = platoServicio;
    }

    @GetMapping
    public String listarPlato(ModelMap model) {
        List<Plato> plato = platoServicio.listarPlato();
        model.addAttribute("plato", plato);
        return "plato/lista-plato";
    }

    @GetMapping("/form")
    public String mostrarFormulario(ModelMap model) {
        model.addAttribute("plato", new Plato());
        return "plato/plato-form";
    }

    @PostMapping("/form")
    public String procesarFormulario(@ModelAttribute Plato plato, List<String> ingredientes, ModelMap model) {
        try {
            platoServicio.guardarPlato(plato);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "plato/plato-form";
        }
        return "redirect:/plato";

    }

    @GetMapping("/editar")
    public String modificar(@RequestParam(value = "id") String id, ModelMap model) {
        try {
            model.put("plato", platoServicio.listarPlato());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "plato/editar-plato";
        }
        return "plato/editar-plato";
    }

    @PostMapping("/editar")
    public String modificarFormulario(@ModelAttribute Plato plato, ModelMap model) {
        try {
            platoServicio.editarPlato(plato);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "plato/editar-plato";
        }
        return "redirect:/plato";
    }
}
