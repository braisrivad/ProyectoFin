package com.brais.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brais.model.Comentarios;
import com.brais.repository.ComentariosRepository;
import com.brais.service.IComentariosService;

@Service
@Primary
public class ComentariosServiceJpa implements IComentariosService {

	@Autowired
	private ComentariosRepository comentariosRepo;
	
	@Override
	public void guardar(Comentarios comentarios) {
		comentariosRepo.save(comentarios);
	}

	@Override
	public void eliminar(Integer idComentarios) {
		comentariosRepo.deleteById(idComentarios);
	}

	@Override
	public List<Comentarios> buscarTodas() {
		return comentariosRepo.findAll();
	}

	@Override
	public Comentarios buscarPorId(Integer idComentarios) {
		Optional<Comentarios> optional = comentariosRepo.findById(idComentarios);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Page<Comentarios> buscarTodas(Pageable page) {
		return comentariosRepo.findAll(page);
	}

	@Override
	public Page<Comentarios> buscarPorIdVideojugos(Integer idVideojuegos, Pageable page) {
		
		return comentariosRepo.findByIdVideojuegos(idVideojuegos, page);
	}





}
