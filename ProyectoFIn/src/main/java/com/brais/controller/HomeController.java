package com.brais.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.brais.model.Perfil;
import com.brais.model.Usuario;
import com.brais.model.Videojuegos;
import com.brais.service.ICategoriasService;
import com.brais.service.IUsuariosService;
import com.brais.service.IVideojuegosService;








@Controller
public class HomeController {
	
	@Autowired
	private ICategoriasService serviceCategorias;
	
	// Inyectamos una instancia desde nuestro ApplicationContext
    @Autowired
	private IVideojuegosService serviceVideojuegos;
    
    @Autowired
   	private IUsuariosService serviceUsuarios;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JavaMailSender mailSender;
    
  
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}
	
	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	
	/**
	 * Método que muestra el formulario para que se registren nuevos usuarios.
	 * @param usuario
	 * @return
	 */
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	/**
	 * Método que guarda en la base de datos el usuario registrado
	 * @param usuario
	 * @param attributes
	 * @return
	 */
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes,BindingResult result) {
	if (result.hasErrors()){
			
			System.out.println("Existieron errores");
			return "formLogin";
		}
		// Recuperamos el password en texto plano
		String pwdPlano = usuario.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		// Hacemos un set al atributo password (ya viene encriptado)
		
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 *
		 */
		Usuario basededatos= serviceUsuarios.buscarPorEmail(usuario.getEmail());
		System.out.println(serviceUsuarios.buscarPorEmail(usuario.getEmail()));
		
		if(serviceUsuarios.buscarPorEmail(usuario.getEmail())==null) {
			if(serviceUsuarios.buscarPorUsername(usuario.getUsername())==null) {
					usuario.setPassword(pwdEncriptado);	
						serviceUsuarios.guardar(usuario);
						attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");

						SimpleMailMessage email = new SimpleMailMessage();

				        email.setTo(usuario.getEmail());
				        email.setSubject(usuario.getNombre());
				        email.setText("Hola te has registrado en la pagina wweb Brai´sVideogames."
				        		+ "Pulsa en el link para iniciar sesion http://videojuegos-env.eba-prhfvfz5.eu-west-3.elasticbeanstalk.com/login");

				        mailSender.send(email);
			
			}else {
				attributes.addFlashAttribute("err", "El username ya esta registrado");
			}
		}else {
			attributes.addFlashAttribute("err", "El email ya esta registrado!");
				
		}
		
		   
		
		return "redirect:/signup";
	}
	
	/**
	 * Método para realizar búsquedas desde el formulario de búsqueda del HomePage
	 * @param videojuegos
	 * @param model
	 * @return
	 */
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Videojuegos videojuegos, Model model) {
		System.out.println("Buscamos por"+videojuegos);
		/**
		 * La busqueda de videojuegos desde el formulario debera de ser únicamente en videojuegos con estatus 
		 * "Aprobada". Entonces forzamos ese filtrado.
		 */
		//videojuegos.setEstatus("Aprobada");
		
		// Personalizamos el tipo de busqueda...
		ExampleMatcher matcher  = ExampleMatcher.matching().
			// and descripcion like '%?%'
			withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Videojuegos> example = Example.of(videojuegos, matcher);
		List<Videojuegos> lista = serviceVideojuegos.buscarByExample(example);
		model.addAttribute("videojuegos", lista);
		return "home";
	}
	
	/**
	 * Metodo que muestra la vista de la pagina de Acerca
	 * @return
	 */
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	/**
	 * Método que muestra el formulario de login personalizado.
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	/**
	 * Método personalizado para cerrar la sesión del usuario
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	/**
     * Utileria para encriptar texto con el algorito BCrypt
     * @param texto
     * @return
     */
    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
   	}
	
	/**
	 * Metodo que agrega al modelo datos genéricos para todo el controlador
	 * @param model
	 */
	@ModelAttribute
	public void setGenericos(Model model){
		Videojuegos videoJuegosSearch = new Videojuegos();
		videoJuegosSearch.reset();
		model.addAttribute("search", videoJuegosSearch);
		model.addAttribute("videojuegos", serviceVideojuegos.buscarDestacadas());	
		model.addAttribute("categorias", serviceCategorias.buscarTodas());	
	}
	
	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
