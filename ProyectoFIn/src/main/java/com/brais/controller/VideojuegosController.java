package com.brais.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.brais.model.*;
import com.brais.service.ICategoriasService;
import com.brais.service.IComentariosService;
import com.brais.service.IRespuestaService;
import com.brais.service.IVideojuegosService;
import com.brais.service.db.ComentariosServiceJpa;
import com.brais.util.Utileria;

@Controller
@RequestMapping(value="/videojuegos")
public class VideojuegosController {
	
	@Value("${videojuegos.ruta.imagenes}")
	private String ruta;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private IVideojuegosService serviceVideojuegos;
    @Autowired
	private IRespuestaService serviceRespuesta;
    
    @Autowired
	private IComentariosService serviceComentarios;
    
    @Autowired
   	private ICategoriasService serviceCategorias;
    
	  
    @GetMapping("/index")
	public String mostrarIndex(Model model) {
    	List<Videojuegos> lista = serviceVideojuegos.buscarTodas();
    	model.addAttribute("videojuegos", lista);
		return "videojuegos/listVideojuegos";
	}
    
    /**
	 * Metodo que muestra la lista de videojuegos con paginacion
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Videojuegos> lista = serviceVideojuegos.buscarTodas(page);
		model.addAttribute("videojuegos", lista);
		return "videojuegos/listVideojuegos";
	}
    
	/**
	 * Método que muestra el formulario para crear una nuevo videojuego
	 * @param videojuegos
	 * @return
	 */
	@GetMapping("/create")
	public String crear(@ModelAttribute Videojuegos videojuegos) {		
		return "videojuegos/formVideojuegos";
	}
	
	/**
	 * Método que guarda el videojuego en la base de datos
	 * @param videojuegos
	 * @param result
	 * @param model
	 * @param multiPart
	 * @param attributes
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute("videojuegos") Videojuegos videojuegos, BindingResult result, Model model,
			@RequestParam("archivoImagen") MultipartFile multiPart, RedirectAttributes attributes ) {	
		
		if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "videojuegos/formVideojuegos";
		}	
		
		String nombreImagen = "";
		
		if (!multiPart.isEmpty()) {
			//String ruta = videojuegos.ruta.imagenes=F:/empleos/img-videojuegos // Windows
			 nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen!=null){ // La imagen si se subio				
				videojuegos.setImagen(nombreImagen); // Asignamos el nombre de la imagen
			}
			
		}
		// Guadamos el objeto videojuegos en la bd
				serviceVideojuegos.guardar(videojuegos);
				attributes.addFlashAttribute("msg", "Los datos del videojuego fueron guardados! ");
		//return "redirect:/videojuegos/index";
		return "redirect:/videojuegos/indexPaginate";		
	}
	
	/**
	 * Método para renderizar la vista de los Detalles para un  determinado videojuego
	 * @param idVideojuegos
	 * @param model
	 * @return
	 */
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVideojuegos, Model model,Comentarios comentariosLista,Pageable page) {		
		Comentarios comentarios= new Comentarios();
		Videojuegos videojuegos = serviceVideojuegos.buscarPorId(idVideojuegos);			
		model.addAttribute("videojuegos", videojuegos);
		model.addAttribute("comentarios", comentarios);
		model.addAttribute("listacomentarios", serviceComentarios.buscarPorIdVideojugos(idVideojuegos, page));
		model.addAttribute("listarespuestas", serviceRespuesta.buscarPorIdVideojuegos(idVideojuegos, page));
		return "detalle";
	}
	
	/**
	 * Método que renderiza el formulario HTML para editar un videojuego
	 * @param idVideojuego
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idVideojuegos, Model model) {		
		Videojuegos videoJuegos = serviceVideojuegos.buscarPorId(idVideojuegos);			
		model.addAttribute("videojuegos", videoJuegos);
		return "videojuegos/formVideojuegos";
	}
	
	/**
	 * Método que eliminar un videojuego de la base de datos
	 * @param idVideojuegos
	 * @param attributes
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idVideojuegos, RedirectAttributes attributes) {
		
		// Eliminamos el videojuego.
		serviceVideojuegos.eliminar(idVideojuegos);
			
		attributes.addFlashAttribute("msg", "La vacante fue eliminada!.");
		//return "redirect:/videojuegos/index";
		return "redirect:/videojuegos/indexPaginate";
	}
	
	/**
	 * Agregamos al Model la lista de Categorias: De esta forma nos evitamos agregarlos en los metodos
	 * crear y editar. 
	 * @return
	 */	
	@ModelAttribute
	public void setGenericos(Model model){
		model.addAttribute("categorias", serviceCategorias.buscarTodas());	
	}
	
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
		
}
