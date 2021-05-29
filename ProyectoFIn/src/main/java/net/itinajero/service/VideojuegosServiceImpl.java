package net.itinajero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.itinajero.model.Videojuegos;

@Service
public class VideojuegosServiceImpl implements IVideojuegosService {
	
	private List<Videojuegos> lista = null;
	
	public VideojuegosServiceImpl() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		lista = new LinkedList<Videojuegos>();
		try {
			// Creamos la oferta de Trabajo 1.
			Videojuegos vacante1 = new Videojuegos();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil"); // Titulo de la vacante
			vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal.");
			vacante1.setFecha(sdf.parse("08-02-2019"));
			vacante1.setPrecio(14000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("empresa1.png");
			vacante1.setEstatus("Aprobada");
						
			// Creamos la oferta de Trabajo 2.
			Videojuegos vacante2 = new Videojuegos();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa importante solicita contador con 5 años de experiencia titulado.");
			vacante2.setFecha(sdf.parse("09-02-2019"));
			vacante2.setPrecio(12000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("empresa2.png");
			vacante2.setEstatus("Creada");
			
			// Creamos la oferta de Trabajo 3.
			Videojuegos vacante3 = new Videojuegos();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalación eléctrica.");
			vacante3.setFecha(sdf.parse("10-02-2019"));
			vacante3.setPrecio(10500.0);
			vacante3.setDestacado(0);
			vacante3.setEstatus("Aprobada");
			
			// Creamos la oferta de Trabajo 4.
			Videojuegos vacante4 = new Videojuegos();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Solicitamos Diseñador Gráfico titulado para diseñar publicidad de la empresa.");
			vacante4.setFecha(sdf.parse("11-02-2019"));
			vacante4.setPrecio(7500.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("empresa3.png");
			vacante4.setEstatus("Eliminada");
			
			/**
			 * Agregamos los 4 objetos de tipo Vacante a la lista ...
			 */
			lista.add(vacante1);			
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}

	public List<Videojuegos> buscarTodas() {
		return lista;
	}

	public Videojuegos buscarPorId(Integer idVacante) {

		for (Videojuegos v : lista) {
			if (v.getId()==idVacante) {
				return v;
			}
		}
		
		return null;
	}

	public void guardar(Videojuegos vacante) {
		lista.add(vacante);
	}

	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub		
	}

	@Override
	public List<Videojuegos> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Videojuegos> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Videojuegos> buscarByExample(Example<Videojuegos> example) {
		// TODO Auto-generated method stub
		return null;
	}

}
