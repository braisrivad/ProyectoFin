package com.brais.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.brais.model.Respuesta;
import com.brais.repository.RespuestaRepository;
import com.brais.service.IRespuestaService;

@Service
@Primary

public class RespuestaServiceJPA implements IRespuestaService {
	@Autowired
	private RespuestaRepository respuestaRepo;
	
	@Override
	public void guardar(Respuesta respuesta) {
		respuestaRepo.save(respuesta);
	}

	@Override
	public void eliminar(Integer idRespuesta) {
		respuestaRepo.deleteById(idRespuesta);
	}

	@Override
	public List<Respuesta> buscarTodas() {
		return respuestaRepo.findAll();
	}

	@Override
	public Respuesta buscarPorId(Integer idRespuesta) {
		Optional<Respuesta> optional = respuestaRepo.findById(idRespuesta);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Respuesta> buscarTodas(Pageable page) {
		return respuestaRepo.findAll(page);
	}

	@Override
	public Page<Respuesta> buscarPorIdVideojuegos(Integer idVideojuegos, Pageable page) {
		
		return respuestaRepo.findByIdVideojuegos(idVideojuegos, page);
	}
	
	@Override
	public Page<Respuesta> buscarPorIdComentarios(Integer idComentarios, Pageable page) {
		
		return respuestaRepo.findByIdComentarios(idComentarios, page);
	}
}
