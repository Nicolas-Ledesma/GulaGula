package com.gulagula.gulagula.repositorios;

import com.gulagula.gulagula.entidades.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepositorio extends JpaRepository<Plato, String> {
  //@Query("SELECT # FROM plato # WHERE p.nombre LIKE :nombre")
    // public List<Libro> BuscarPlatpPorNombre(@Param("nombre") String nombre);
}
