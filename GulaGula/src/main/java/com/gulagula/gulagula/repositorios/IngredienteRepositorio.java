
package com.gulagula.gulagula.repositorios;

import com.gulagula.gulagula.entidades.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 @Repository
public interface IngredienteRepositorio extends JpaRepository<Ingrediente, String> {
    
}
