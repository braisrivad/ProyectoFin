package com.brais.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brais.model.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {

	/**
	 * metodo para encontrar todos los comentarios
	 */
	List<Respuesta> findAll();
	
	/**
	 * metodo para encontrar los comentarios de un videojuego
	 * @param idVideojuegos
	 * @param page
	 * @return
	 */
	@Query(value="select * from respuesta r where r.idVideojuegos = :idVideojuegos order by id desc ",nativeQuery=true,
			countQuery = "SELECT count(*) FROM respuesta r where r.idVideojuegos = :idVideojuegos")
	Page<Respuesta> findByIdVideojuegos(Integer idVideojuegos,Pageable page);
	
	
	@Query(value="select * from respuesta r where r.idComentarios = :idComentarios order by id desc ",nativeQuery=true,
			countQuery = "SELECT count(*) FROM respuesta r where r.idComentarios = :idComentarios")
	Page<Respuesta> findByIdComentarios(Integer idComentarios,Pageable page);
}
