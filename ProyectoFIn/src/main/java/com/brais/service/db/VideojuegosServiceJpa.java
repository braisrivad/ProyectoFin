package com.brais.service.db;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.brais.model.Videojuegos;
import com.brais.repository.VideojuegosRepository;
import com.brais.service.IVideojuegosService;

@Service
/**
 * 	Hay 2 clases que implementan la interfaz IVacantesService:
 *		- VideojuegosServiceImpl -> Guardamos las Vacantes en una LinkedList
 *		- VideojuegosServiceJpa  -> Guardamos las Vacantes en bd
 *	Spring no puede decidir cual Inyectar. Nosotros tenemos que decidir.
 *	Por lo tanto, con la anotación @Primary le decimos que esta es la implementación por Defecto.
 *
 */
@Primary
public class VideojuegosServiceJpa implements IVideojuegosService{

	@Autowired
	private VideojuegosRepository videojuegossRepo;

	@Override
	public void guardar(Videojuegos videojuego) {
		videojuegossRepo.save(videojuego);
	}

	@Override
	public void eliminar(Integer idVideojuego) {
		videojuegossRepo.deleteById(idVideojuego);
	}

	@Override
	public List<Videojuegos> buscarTodas() {		
		return videojuegossRepo.findAll();
	}

	@Override
	public Videojuegos buscarPorId(Integer idVideojuego) {
		Optional<Videojuegos> optional = videojuegossRepo.findById(idVideojuego);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Videojuegos> buscarDestacadas() {
		return videojuegossRepo.findByDestacadoOrderByIdDesc(1);
	}

	@Override
	public Page<Videojuegos> buscarTodas(Pageable page) {		
		return videojuegossRepo.findAll(page);
	}

	@Override
	public List<Videojuegos> buscarByExample(Example<Videojuegos> example) {
		return videojuegossRepo.findAll(example);
	}
		
}
