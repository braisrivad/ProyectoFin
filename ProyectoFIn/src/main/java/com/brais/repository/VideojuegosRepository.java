package com.brais.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.brais.model.Videojuegos;

public interface VideojuegosRepository extends JpaRepository<Videojuegos, Integer> {
	
	List<Videojuegos> findByEstatus(String estatus);
	
	List<Videojuegos> findByDestacadoOrderByIdDesc(int destacado);
	
	List<Videojuegos> findByPrecioBetweenOrderByPrecioDesc(double p1, double p2);
	
	List<Videojuegos> findByEstatusIn(String[] estatus);

}
