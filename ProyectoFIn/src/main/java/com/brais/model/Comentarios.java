/**
 * Esta clase representa una entidad (un registro) en la tabla de Comentarios de la base de datos
 */
package com.brais.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comentarios")
public class Comentarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private String username; //nombre del usuario que hizo el comentario
	private String nombreVideojuegos;// nombre del videojuego que comento
	private String descripcion;//contenido del comentario
	private Integer idVideojuegos;// id vidiojuego
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
	public String getNombreVideojuegos() {
		return nombreVideojuegos;
	}
	public void setNombreVideojuegos(String nombreVideojuegos) {
		this.nombreVideojuegos = nombreVideojuegos;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdVideojuegos() {
		return idVideojuegos;
	}
	public void setIdVideojuegos(Integer idVideojuegos) {
		this.idVideojuegos = idVideojuegos;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fecha) {
		this.fechaCreacion = fecha;
	}
	
	@Override
	public String toString() {
		return "Comentarios [id=" + id + ", username=" + username + ", nombreVideojuegos=" + nombreVideojuegos
				+ ", descripcion=" + descripcion + ", idVideojuegos=" + idVideojuegos + ", idUsuario=" + idUsuario
				+ ", fechaCreacion=" + fechaCreacion + "]";
	}

	
	
}
