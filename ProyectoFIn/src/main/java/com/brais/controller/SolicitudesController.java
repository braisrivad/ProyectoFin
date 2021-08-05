package com.brais.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.brais.model.Solicitud;
import com.brais.model.Usuario;
import com.brais.model.Videojuegos;
import com.brais.service.IComentariosService;
import com.brais.service.IUsuariosService;
import com.brais.service.IVideojuegosService;
import com.brais.util.Utileria;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {
	
	/*@Value("${empleosapp.ruta.cv}")
	private String ruta;*/
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private IComentariosService serviceSolicitudes;

    // Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private IVideojuegosService serviceVideojuegos;
    
    @Autowired
   	private IUsuariosService serviceUsuarios;
    /**
	 * Metodo que muestra la lista de solicitudes sin paginacion
	 * @param model
	 * @param page
	 * @return
	 */
//    @GetMapping("/index")
//	public String mostrarIndex(Model model) {
//    	List<Solicitud> lista = serviceSolicitudes.buscarTodas();
//    	model.addAttribute("solicitudes", lista);
//		return "solicitudes/listSolicitudes";
//	}
//    
//    /**
//	 * Metodo que muestra la lista de solicitudes con paginacion
//	 * @param model
//	 * @param page
//	 * @return
//	 */
//	@GetMapping("/indexPaginate")
//	public String mostrarIndexPaginado(Model model, Pageable page) {
//		Page<Solicitud> lista = serviceSolicitudes.buscarTodas(page);
//		model.addAttribute("solicitudes", lista);
//		return "solicitudes/listSolicitudes";
//	}
//    
//	/**
//	 * Método para renderizar el formulario para aplicar para una Vacante
//	 * @param solicitud
//	 * @param idVacante
//	 * @param model
//	 * @return
//	 */
//	@GetMapping("/create/{idVideojuego}")
//	public String crear(Solicitud solicitud, @PathVariable Integer idVideojuego, Model model) {
//		// Traemos los detalles de la Vacante seleccionada para despues mostrarla en la vista
//		Videojuegos videojuegos = serviceVideojuegos.buscarPorId(idVideojuego);
//		model.addAttribute("videojuegos", videojuegos);
//		return "solicitudes/formSolicitud";
//	}
//	
//	/**
//	 * Método que guarda la solicitud enviada por el usuario en la base de datos
//	 * @param solicitud
//	 * @param result
//	 * @param model
//	 * @param session
//	 * @param multiPart
//	 * @param attributes
//	 * @return
//	 */
//	@PostMapping("/save")
//	public String guardar(Solicitud solicitud, BindingResult result, Model model, HttpSession session,
//			@RequestParam("archivoCV") MultipartFile multiPart, RedirectAttributes attributes, Authentication authentication) {	
//		
//		// Recuperamos el username que inicio sesión
//		String username = authentication.getName();
//		
//		if (result.hasErrors()){
//			
//			System.out.println("Existieron errores");
//			return "solicitudes/formSolicitud";
//		}	
//		
//		if (!multiPart.isEmpty()) {
//			//String ruta = "/empleos/files-cv/"; // Linux/MAC
//			//String ruta = "c:/empleos/files-cv/"; // Windows
//			//String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
//			//if (nombreArchivo!=null){ // El archivo (CV) si se subio				
//			//	solicitud.setArchivo(nombreArchivo); // Asignamos el nombre de la imagen
//			//}	
//		}
//
//		// Buscamos el objeto Usuario en BD	
//		Usuario usuario = serviceUsuarios.buscarPorUsername(username);		
//		
//		solicitud.setUsuario(usuario); // Referenciamos la solicitud con el usuario 
//		solicitud.setFecha(new Date());
//		// Guadamos el objeto solicitud en la bd
//		serviceSolicitudes.guardar(solicitud);
//		attributes.addFlashAttribute("msg", "Gracias por enviar tu CV!");
//			
//		//return "redirect:/solicitudes/index";
//		return "redirect:/";		
//	}
//	
//	/**
//	 * Método para eliminar una solicitud 
//	 * @param idSolicitud
//	 * @param attributes
//	 * @return
//	 */
//	@GetMapping("/delete/{id}")
//	public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes attributes) {
//		
//		// Eliminamos la solicitud.
//		serviceSolicitudes.eliminar(idSolicitud);
//			
//		attributes.addFlashAttribute("msg", "La solicitud fue eliminada!.");
//		//return "redirect:/solicitudes/index";
//		return "redirect:/solicitudes/indexPaginate";
//	}
//			
//	/**
//	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
//	 * @param webDataBinder
//	 */
//	@InitBinder
//	public void initBinder(WebDataBinder webDataBinder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//	}
}
