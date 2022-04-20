
package com.gulagula.gulagula.repositorios;

import com.gulagula.gulagula.entidades.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

 @Repository
public interface IngredienteRepositorio extends JpaRepository<Ingrediente, String> {
    
      @Query("SELECT i FROM Ingrediente i WHERE i.nombre = : nombre")
    public Ingrediente buscarPorNombre(@Param("nombre") String nombre);

        //    @Query("SELECT i FROM Ingrediente i WHERE i.nombre LIKE :nombre")
//    public List<Ingrediente> BuscarPorNombre(@Param("id") String id);
}
