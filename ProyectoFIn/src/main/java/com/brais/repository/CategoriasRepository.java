package com.brais.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brais.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
	
}
