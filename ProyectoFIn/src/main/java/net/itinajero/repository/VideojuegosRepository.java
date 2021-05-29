package net.itinajero.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.itinajero.model.Videojuegos;

public interface VideojuegosRepository extends JpaRepository<Videojuegos, Integer> {
	
	List<Videojuegos> findByEstatus(String estatus);
	
	List<Videojuegos> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	
	List<Videojuegos> findByPrecioBetweenOrderByPrecioDesc(double p1, double p2);
	
	List<Videojuegos> findByEstatusIn(String[] estatus);

}
