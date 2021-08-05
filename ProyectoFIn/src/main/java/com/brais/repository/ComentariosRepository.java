package com.brais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brais.model.Comentarios;

public interface ComentariosRepository extends JpaRepository<Comentarios, Integer> {

	/**
	 * metodo para encontrar todos los comentarios
	 */
	List<Comentarios> findAll();
	
	/**
	 * metodo para encontrar los comentarios de un videojuego
	 * @param idVideojuegos
	 * @param page
	 * @return
	 */
	@Query(value="select * from comentarios c where c.idVideojuegos = :idVideojuegos order by id desc ",nativeQuery=true,
			countQuery = "SELECT count(*) FROM comentarios c where c.idVideojuegos = :idVideojuegos")
	Page<Comentarios> findByIdVideojuegos(Integer idVideojuegos,Pageable page);
	
}
