package net.itinajero.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.itinajero.model.Videojuegos;

public interface IVideojuegosService {
	void guardar(Videojuegos videojuegos);
	void eliminar(Integer idVideojuegos);
	List<Videojuegos> buscarTodas();
	Videojuegos buscarPorId(Integer idVacante);
	List<Videojuegos> buscarDestacadas();
	Page<Videojuegos> buscarTodas(Pageable page);
	List<Videojuegos> buscarByExample(Example<Videojuegos> example);
}
