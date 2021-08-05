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
import com.brais.service.db.RespuestaServiceJPA;

import antlr.collections.List;

@Controller
@RequestMapping("/respuesta")
public class RespuestasController {
	
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
	@PostMapping("/save/{idCom}&{idVideo}")
	public String guardarRespuestas (@PathVariable("idCom")  int idComentarios, @PathVariable("idVideo") int idVideojuegos,Respuesta respuesta, HttpSession session, Model model,Pageable page) {
		Respuesta resp= new Respuesta();
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			String fecha=dtf.format(LocalDateTime.now());
		System.out.println(fecha);
		
		Usuario usuario=(Usuario) session.getAttribute("usuario");
		resp.setRespuestaDescripcion(respuesta.getRespuestaDescripcion());
		resp.setIdUsuario(usuario.getId());
		resp.setIdComentarios(idComentarios);
		resp.setUsername(usuario.getUsername());
		resp.setFechaCreacion(fecha);
		resp.setIdVideojuegos(idVideojuegos);
		serviceRespuesta.guardar(resp);
		
//		Videojuegos videojuegos = serviceVideojuegos.buscarPorId(idComentarios);
		model.addAttribute("videojuegos", serviceVideojuegos.buscarPorId(idVideojuegos));
		model.addAttribute("listacomentarios", serviceComentarios.buscarPorIdVideojugos(idVideojuegos, page));
		model.addAttribute("listarespuestas", serviceRespuesta.buscarPorIdVideojuegos(idVideojuegos, page));
		
		return "detalle";
		
	}
	
	 /**
	 * Metodo que muestra la lista de comentarios con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("lista/{id}")
	public String mostrarIndexPaginado(@PathVariable("id") int idComentario,Model model, Pageable page,RedirectAttributes attributes ) {
		Page<Respuesta> lista = serviceRespuesta.buscarPorIdComentarios(idComentario,page);
		model.addAttribute("respuestas", lista);
		if(lista.isEmpty()) {
			Page<Comentarios> listas = serviceComentarios.buscarTodas(page);
			model.addAttribute("comentarios", listas);
			model.addAttribute("err", "Este comentario no tiene ninguna respuesta");
			return "comentarios/listComentarios";
			
			
		}else {
			
			return "respuestas/listRespuestas";
			
		}
		
}
	
	/**
	 * Método para eliminar un comentario
	 * @param idSolicitud
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{idResp}&{id}")
	public String eliminar(@PathVariable("idResp") int idRespuesta,@PathVariable("id") int idComentario, RedirectAttributes attributes,Pageable page,Model model) {
		
		// Eliminamos las comentarios.
		serviceRespuesta.eliminar(idRespuesta);
		Page<Respuesta> lista = serviceRespuesta.buscarPorIdComentarios(idComentario,page);
		model.addAttribute("respuestas", lista);
		if(lista.isEmpty()) {
			Page<Comentarios> listas = serviceComentarios.buscarTodas(page);
			model.addAttribute("comentarios", listas);
			model.addAttribute("err", "Se borraron todas la respuestas del comentario");
			return "comentarios/listComentarios";
			
			
		}else {
			model.addAttribute("msg1", "La Respuesta fue eliminada!.");
			return "respuestas/listRespuestas";
			
		}
}
}
