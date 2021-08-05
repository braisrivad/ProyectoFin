package com.brais.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brais.model.Usuario;

public interface IUsuariosService {
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
	Page<Usuario> buscarRegistrados(Pageable page);
	Usuario buscarPorId(Integer idUsuario);
	Usuario buscarPorUsername(String username);
	Usuario buscarPorEmail(String email);
	Usuario buscarPorPassword(String password);
	int bloquear(int idUsuario);
	int activar(int idUsuario);
}
