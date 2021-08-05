/**
 * Esta clase representa una entidad (un registro) en la tabla de Comentarios de la base de datos
 */
package com.brais.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "respuesta")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private String username; //nombre del usuario que hizo el comentario
	private String respuestaDescripcion;//contenido del comentario
	private Integer idComentarios;//id vidiojuego
	private Integer idVideojuegos;
	private Integer idUsuario;//idususario
	private String fechaCreacion;//fecha del comentario
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getRespuestaDescripcion() {
		return respuestaDescripcion;
	}
	public void setRespuestaDescripcion(String respuestaDescripcion) {
		this.respuestaDescripcion = respuestaDescripcion;
	}
	public Integer getIdComentarios() {
		return idComentarios;
	}
	public void setIdComentarios(Integer idComentarios) {
		this.idComentarios = idComentarios;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdVideojuegos() {
		return idVideojuegos;
	}
	public void setIdVideojuegos(Integer idVideojuegos) {
		this.idVideojuegos = idVideojuegos;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", username=" + username + ", respuestaDescripcion=" + respuestaDescripcion
				+ ", idComentarios=" + idComentarios + ", idVideojuegos=" + idVideojuegos + ", idUsuario=" + idUsuario
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}
	
	
	

	
	
}
