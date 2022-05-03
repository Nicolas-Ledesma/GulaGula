package com.gulagula.gulagula.controladores;

import com.gulagula.gulagula.entidades.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainControlador {

    @GetMapping
    public String home() {
        return "/index";
    }

    @GetMapping("/login")
    public String formLogin(@RequestParam(required = false) String error, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o Clave incorrecta");
        }
        return ("/login");
    }

    @GetMapping("/registro")
    public String formRegistro(ModelMap model) {
        model.addAttribute("usuario", new Usuario());
        return ("/usuario/usuario-form");
    }

}
