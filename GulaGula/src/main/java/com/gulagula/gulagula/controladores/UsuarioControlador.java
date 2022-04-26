package com.gulagula.gulagula.controladores;

import com.gulagula.gulagula.entidades.Usuario;
import com.gulagula.gulagula.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping()
    public String listarUsuarios(ModelMap model) {
        List<Usuario> usuarios = usuarioServicio.listaUs();
        model.addAttribute("usuarios", usuarios);
        return "usuario/lista-usuario";
    }

    @GetMapping("/form")
    public String mostrarFormulario(ModelMap model, String id, RedirectAttributes attr) {
        if (id == null) {
            model.addAttribute("usuario", new Usuario());
            return "usuario/usuario-form";
        } else {
            try {
                Usuario usuario = usuarioServicio.buscarUsId(id);
                model.addAttribute("usuario", usuario);
                return "usuario/usuario-form";
            } catch (Exception e) {
                attr.addFlashAttribute("errorMsj", e.getMessage());
                return "redirect:/usuario";
            }
        }
    }

    @PostMapping("/form")
    public String procesarFormulario(@ModelAttribute Usuario usuario, ModelMap model) {
        try {
            usuarioServicio.guardarUsuario(usuario);
        } catch (Exception e) {
            System.out.println("error form");
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            return "usuario/usuario-form";
        }
        return "redirect:/usuario";
    }

    @GetMapping("/alta")
    public String alta(@RequestParam(value = "id") String id, ModelMap model) {
        try {
            usuarioServicio.activarUs(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "usuario";
        }
        return "redirect:/usuario";
    }

    @GetMapping("/baja")
    public String baja(@RequestParam(value = "id") String id, ModelMap model) {
        try {
            usuarioServicio.desactivarUs(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "usuario";
        }
        return "redirect:/usuario";
    }

}
