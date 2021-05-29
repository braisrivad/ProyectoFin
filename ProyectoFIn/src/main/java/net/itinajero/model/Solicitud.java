/**
 * Esta clase representa una entidad (un registro) en la tabla de Solicitudes de la base de datos
 */
package net.itinajero.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private Date fecha; // Fecha en que aplico el usuario para esta oferta de trabajo
	private String comentarios;
	private String archivo; // El nombre del archivo PDF, DOCX del CV.

	@OneToOne
	@JoinColumn(name = "idVideojuegos") // foreignKey en la tabla de solicitudes
	private Videojuegos videojuegos;

	@OneToOne
	@JoinColumn(name = "idUsuario") // foreignKey en la tabla de usuarios
	private Usuario usuario;

	public Solicitud() {

	}

	public Solicitud(Date fecha) {
		this.fecha = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public Videojuegos getVideojuegos() {
		return videojuegos;
	}

	public void setVacante(Videojuegos videojuegos) {
		this.videojuegos = videojuegos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Solicitud [id=" + id + ", fecha=" + fecha + ", comentarios=" + comentarios + ", archivo=" + archivo
				+ ", vacante=" + videojuegos + ", usuario=" + usuario + "]";
	}

}
