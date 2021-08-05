package com.brais.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${videojuegos.ruta.imagenes}")
	private String rutaImagenes;
	
	/*@Value("${empleosapp.ruta.cv}")
	private String rutaCv;*/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		// Configuración de los recursos estáticos (imagenes de los videojuegos) 
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-videojuegos/"); // Windows
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-videojuegos/"); // Linux
		registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes); 
		
	}
	
	@Bean
	   public MessageSource messageSource() {
	      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	      source.setBasename("messages");
	      return source;
	   }
	

}
