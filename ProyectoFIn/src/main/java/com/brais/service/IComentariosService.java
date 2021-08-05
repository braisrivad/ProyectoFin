package com.brais.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brais.model.Comentarios;

public interface IComentariosService {
	void guardar(Comentarios comentarios);
	void eliminar(Integer idComentarios);
	List<Comentarios> buscarTodas();
	Comentarios buscarPorId(Integer idComentarios);
	Page<Comentarios> buscarTodas(Pageable page);
	Page<Comentarios> buscarPorIdVideojugos(Integer idVideojuegos, Pageable page);
}
