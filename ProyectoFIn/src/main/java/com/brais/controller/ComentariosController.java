package com.brais.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.brais.model.Comentarios;
import com.brais.model.Respuesta;
import com.brais.model.Usuario;
import com.brais.model.Videojuegos;
import com.brais.service.IComentariosService;
import com.brais.service.IRespuestaService;
import com.brais.service.IVideojuegosService;
import com.brais.service.db.ComentariosServiceJpa;

@Controller
@RequestMapping("/comentarios")
public class ComentariosController {
	
	@Autowired
	private IRespuestaService serviceRespuesta;
	@Autowired
	private IComentariosService serviceComentarios;
	@Autowired
	private IVideojuegosService serviceVideojuegos;
	
/**
 * Metodo que guarda los comentarios en base de datos
 * @param idVideojuegos
 * @param comentarios
 * @param session
 * @param model
 * @param page
 * @return
 */
	@PostMapping("/save/{id}")
	public String guardarComentario (@PathVariable("id") int idVideojuegos,Comentarios comentarios, HttpSession session, Model model,Pageable page) {
		Comentarios com= new Comentarios();
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fecha=dtf.format(LocalDateTime.now());
		System.out.println(fecha);
		Videojuegos video=serviceVideojuegos.buscarPorId(idVideojuegos);
		Usuario usuario=(Usuario) session.getAttribute("usuario");
		com.setDescripcion(comentarios.getDescripcion());
		com.setIdUsuario(usuario.getId());
		com.setIdVideojuegos(idVideojuegos);
		com.setUsername(usuario.getUsername());
		com.setFechaCreacion(fecha);
		com.setNombreVideojuegos(video.getNombre());
		serviceComentarios.guardar(com);
		
		Videojuegos videojuegos = serviceVideojuegos.buscarPorId(idVideojuegos);
		model.addAttribute("videojuegos", videojuegos);
		//model.addAttribute("comentarios",comentarios);
		model.addAttribute("listacomentarios", serviceComentarios.buscarPorIdVideojugos(idVideojuegos, page));
//		model.addAttribute("listarespuestas", serviceRespuesta.buscarPorIdVideojuegos(idVideojuegos, page));
		return "detalle";
		
	}
	
	 /**
	 * Metodo que muestra la lista de comentarios con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Comentarios> lista = serviceComentarios.buscarTodas(page);
		model.addAttribute("comentarios", lista);
		return "comentarios/listComentarios";
}
	
	/**
	 * Método para eliminar un comentario
	 * @param idSolicitud
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idComentario, RedirectAttributes attributes) {
		
		// Eliminamos las comentarios.
		serviceComentarios.eliminar(idComentario);
			
		attributes.addFlashAttribute("msg", "El comentario fue eliminado!.");
		return "redirect:/comentarios/indexPaginate";
}
}
