package com.gulagula.gulagula.servicios;

import com.gulagula.gulagula.entidades.Usuario;
import com.gulagula.gulagula.enumeradores.Rols;
import com.gulagula.gulagula.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio implements UserDetailsService {

    public UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Transactional
    public void guardarUsuario(Usuario usuario) throws Exception {
        validar(usuario);
        activateIfNew(usuario);
        usuario.setClave(new BCryptPasswordEncoder().encode(usuario.getClave()));
        usuarioRepositorio.save(usuario);
    }

    @Transactional
    public void modificar(Usuario usuario) throws Exception {
        validar(usuario);
        Optional<Usuario> respuesta = usuarioRepositorio.findById(usuario.getId());
        if (respuesta.isPresent()) {
            usuarioRepositorio.save(respuesta.get());
        } else {
            throw new Exception("No se encontró el usuario solicitado");
        }
    }

    @Transactional
    public Usuario buscarUsId(String id) throws Exception {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new Exception("Usuario no encontrado"));
    }

    @Transactional
    public List<Usuario> listaUs() {
        return usuarioRepositorio.findAll();
    }

    @Transactional
    public void activarUs(String id) throws Exception {
        Usuario usuario = this.buscarUsId(id);
        usuario.setAlta(true);
    }

    @Transactional
    public void desactivarUs(String id) throws Exception {
        Usuario usuario = this.buscarUsId(id);
        usuario.setAlta(false);
    }

    @Transactional
    public void cambiarRol(String id) throws Exception {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            if (usuario.getRole().equals(Rols.USER)) {
                usuario.setRole(Rols.ADMIN);
            } else if (usuario.getRole().equals(Rols.ADMIN)) {
                usuario.setRole(Rols.USER);
            }
        }
    }

    @Transactional
    public void validar(Usuario usuario) throws Exception {

        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new Exception("El email no puede estar vacio");
        }
        if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
            throw new Exception("La clave no puede estar vacia");
        }
    }

    private void activateIfNew(Usuario usuario) {
        if (usuario.getAlta() == null) {
            usuario.setAlta(true);
            usuario.setRole(Rols.USER);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String Dni) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarporDni(Dni);
        if (usuario == null) {
            throw new UnsupportedOperationException("Usuario no registrado");
        }
        List<GrantedAuthority> permisos = new ArrayList<>();
        GrantedAuthority permisosRol = new SimpleGrantedAuthority("rols_" + usuario.getRole().toString());
        permisos.add(permisosRol);
        return new User(usuario.getEmail(), usuario.getClave(), permisos);
    }

}
