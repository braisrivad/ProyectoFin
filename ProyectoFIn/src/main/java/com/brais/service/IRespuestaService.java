package com.brais.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brais.model.Respuesta;

public interface IRespuestaService {
	void guardar(Respuesta respuesta);
	void eliminar(Integer idRespuesta);
	List<Respuesta> buscarTodas();
	Respuesta buscarPorId(Integer idRespuesta);
	Page<Respuesta> buscarTodas(Pageable page);
	Page<Respuesta> buscarPorIdVideojuegos(Integer idVideojuegos, Pageable page);
	Page<Respuesta> buscarPorIdComentarios(Integer idComentarios, Pageable page);
	
}
