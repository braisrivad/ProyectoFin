/**
 * Esta clase representa una entidad (un registro) en la tabla de Vacantes de la base de datos
 */
package com.brais.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videojuegos")
public class Videojuegos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private String nombre; // Nombre del vidiojuego.
	private String descripcion; // Descripcion corta del videojuego
	private Date fecha; // Fecha de publicacion del videojuego.
	private Double precio; // Salario aproximado que se ofrece por el trabajo.
	private String estatus; // Valores [Alpha,Beta, Released].
	private Integer destacado; // Valores [0, 1]. 0: No se muestra en la pag. principal | 1: Se muestra en la pagina principal.
	private String imagen="no-image.png"; // Nombre del archivo de la imagen del logotipo de la empresa que ofrece el trabajo.
	private String detalles; // Detalles de la oferta de trabajo.
	
	@OneToOne
	@JoinColumn(name = "idCategoria") // foreignKey en la tabla de Vacantes	
	private Categoria categoria; // Categoria a la que pertence la oferta de trabajo

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getDestacado() {
		return destacado;
	}

	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public void reset() {
		this.imagen=null;
	}

	@Override
	public String toString() {
		return "Vacante [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", precio=" + precio + ", estatus=" + estatus + ", destacado=" + destacado + ", imagen=" + imagen
				+ ", detalles=" + detalles + ", categoria=" + categoria + "]";
	}
	
	/**
	 * @NotNull(message="Introduce un nombre")
	@Size(min=4, max= 30, message="El nombre no tiene el tamaño adecuado")
	private String nombre; // Nombre de la oferta de trabajo.
	@NotNull(message="Introduce una descripcion")
	@Size(min=4, max= 150, message="La descripcion no tiene el tamaño adecuado")
	private String descripcion; // Descripcion corta del videojuego
	@NotNull(message="Introduce una fecha")
	private Date fecha; // Fecha de publicacion del videojuego.
	@NotNull(message="Introduce un precio")
	private Double precio; // Salario aproximado que se ofrece por el trabajo.
	@NotNull(message="Selecciona una opcion")
	private String estatus; // Valores [Alpha,Beta, Released].
	private Integer destacado; // Valores [0, 1]. 0: No se muestra en la pag. principal | 1: Se muestra en la pagina principal.
	@NotNull(message="Añade una imagen")
	private String imagen="no-image.png"; // Nombre del archivo de la imagen del logotipo de la empresa que ofrece el trabajo.
	
	private String detalles; // Detalles de la oferta de trabajo.*/

}
