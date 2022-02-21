package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;

public class Vista{
	
	// incicialización de parámetros de validación de entradas
	private static final String ERROR= "ERROR: ";
	private static final String NOMBRE_VALIDO= "Nombre válido";
	private static final String CORREO_VALIDO= "Correo válido";
	private Controlador controlador;
	
	//Constructor
	public Vista() {
		Opcion.setVista(this);
	}
	//Consulta a controlador
	public void setControlador(Controlador controlador) {
		this.controlador= controlador;
	}	
	
	//Invoca la opcion comenzar de Opcion
	public void comenzar()
	{
		int ordinalOpcion;
		do 
		{
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	//método para salir de Opcion
	public void salir() {
		controlador.terminar();
	}
	//Invoca método insertar de Aula
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
		
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada con éxito.");
			
			//iniciar un catch para capturar las excepciones de la clase aula
			// así también como para el método insertar
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	
	}
	//opcion que invoca al méotodo borrar de Aula
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			controlador.borrarAula(Consola.leerAula());
			System.out.println("Aula borrada con éxito.");
			
			//iniciar un catch para capturar excepciones de la clase Aula como del método borrar
		}catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	//invoca opción buscar de aulas
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula;
		
		try {
			aula= controlador.buscarAula(Consola.leerAula());
			String mensaje= (aula!= null) ? aula.toString(): "El aula indicado no se encuentra en el sistema";
		
		//Captura de excepciones de la clase y su método
		}catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	//Opcion que invoca método de mostrar lista de aulas en Aulas
	public void listarAulas() {
		Consola.mostrarCabecera("Lista de aulas");
		List<String> aulas= controlador.representarAulas();
		if (aulas== null) {
			System.out.println(ERROR + "No existen aulas para listar. Ingrese un aula en el sistema.");
		}else {	
			
			Iterator<String> iteradorAulas= aulas.iterator();
			while (iteradorAulas.hasNext()) {
				System.out.println(iteradorAulas.next().toString());
			}
		}
			
	}
	//opción que invoca la inserción de profesores
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado con éxito.");
			
			//Captura de excepciones de clase y método
		}catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	//opción que invoca método boorar de profesores
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor borrado con éxito.");
			
			//capturamos excepciones en clase y método
		}catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//opción que invoca método buscar profesor en profesores
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor;
		try {
			profesor= controlador.buscarProfesor(Consola.leerProfesor());
			String mensaje= (profesor!= null) ? profesor.toString() : ERROR+ "El profesor ingresado no está registrado en el sistema.";
			System.out.println(mensaje);
			
			//capturamos excepciones en clase y método
		}catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//opción que invoca método de ver listas de profesores en Profesores
	public void listarProfesores() {
		Consola.mostrarCabecera("Lista de profesores");
		
		List<String> profesores= controlador.representarProfesores();
		if (profesores == null) {
			System.out.println("No hay profesores que mostrar");
		} else {
			Iterator<String> iteradorProf = profesores.iterator();
			while (iteradorProf.hasNext()) {
				System.out.println(iteradorProf.next().toString());
			}
		}
	}
	
	//opción que invoca método de insertar reserva
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar reserva");
		try {
			Profesor profesor= null;
			controlador.realizarReserva(leerReserva(profesor));
			System.out.println("Reserva insertada con éxito, " + NOMBRE_VALIDO + "/" + CORREO_VALIDO);
			
			//Captura de excepciones
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	// método de Consola "leerNombreProfesor.
	//método utilizado para ahorrar información no necesaria
	private Reserva leerReserva(Profesor profesor) {
		Consola.mostrarCabecera("Realizar reserva");
		
		String nombreAula;
		String nombreProfesor;
		List<String> profesores= controlador.representarProfesores();
		List<String> aulas= controlador.representarAulas();
		String correoProfesorCt= new String();
		String correoProfesorSt= new String();
		String telefonoProf= new String();
		String datosProf= null;
		
		//inicialización de variables
		Reserva reserva= null;
		Aula aula= null;
		Permanencia permanencia= null;
		boolean aulaRegistrado= false;
		boolean profesorRegistrado= false;
		boolean telefonoRegistrado= false;
		
		try {
			nombreProfesor= Consola.leerNombreProfesor();
			nombreAula= Consola.leerNombreAula();
			
			//recorrer array y mostrar infrormación de profesores. Guarda información en cada array como parámetro
			for (Iterator<String> iteradorLeerRes= profesores.iterator(); iteradorLeerRes.hasNext();) {
				String infoProfesores= iteradorLeerRes.next();
				
				//sentencia condicional para comparar el nombre del profesor introducido con
				//el resultado de profesores con toString.
				//Validación a través del método indexof
				
				if (nombreProfesor.equalsIgnoreCase(infoProfesores.substring(infoProfesores.indexOf('=') +1, infoProfesores.indexOf(',') ))){
					datosProf= infoProfesores;
					profesorRegistrado= true;
				}
			}
			if (datosProf != null) {
				if (datosProf.contains("telefono")) {
					telefonoRegistrado= true;
				}
				if(telefonoRegistrado) {
				
				
				//A través del mismo método se obtiene información como correo especificando la posición de la cadena donde se encuentra correo
				
						correoProfesorCt= datosProf.substring(datosProf.indexOf('=') +1, datosProf.lastIndexOf(',') );
						telefonoProf=datosProf.substring(datosProf.lastIndexOf('=') +1);		
				}else {
				correoProfesorSt=datosProf.substring(datosProf.lastIndexOf('=')+1);
			}
		}
		for (Iterator<String> iteradorLeerRes= aulas.iterator(); iteradorLeerRes.hasNext();) {
			String nombreAulas= iteradorLeerRes.next();
			if(nombreAulas.toString().replace("nombre Aula=", "").equals(nombreAula)) {
				aula= new Aula(nombreAula);
				aulaRegistrado= true;
				}
		}
		
		if (!aulaRegistrado) {
			System.out.println(ERROR + "No se ha podido encontrar el profesor en el sistema.");
		}

		if (!aulaRegistrado) {
			System.out.println(ERROR + "No se ha podido encontrar el aula en el sistema.");

		} else if (profesorRegistrado && aulaRegistrado && !telefonoRegistrado) {
			permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

			Profesor profesorALeer = new Profesor(nombreProfesor, correoProfesorSt);

			reserva = new Reserva(profesorALeer, aula, permanencia);
			
		} else if (profesorRegistrado && aulaRegistrado && telefonoRegistrado) {
			permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());

			Profesor profesorALeer = new Profesor(nombreProfesor, correoProfesorCt);

			reserva = new Reserva(profesorALeer, aula, permanencia);
		}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		return reserva;
	}		
	
	//opción para borrar reserva
	public void anularReserva() {
		Consola.mostrarCabecera("Anular Reserva");
		
		try {
			Profesor profesor= null;
			controlador.anularReserva(leerReserva(profesor));
			System.out.println("Reserva anula con éxito, " + NOMBRE_VALIDO + CORREO_VALIDO + ".");
			//Captura de excepciones
		}catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}	
	
	//listar reservas a través del método listar en Reservas
	public void listarReservas() {
		Consola.mostrarCabecera("Listas de Reservas");
			
		List<String> reservas= controlador.representarReservas();
		if (reservas.size()> 0) {
			for (Iterator<String> iteradorLr = reservas.iterator(); iteradorLr.hasNext();) {
				String reserva = iteradorLr.next();
				System.out.println(reserva);
			}
		}else {
			System.out.println("No existen reservas en la lista. Ingrese una reserva.");
		}
	}
	//método para listar reservas con el parámetro aulas invocado en Reservas
	public void listarReservasAula() {
		Consola.mostrarCabecera("Lista de reservas por aula");
		List<Reserva> reservas= controlador.getReservasAula(Consola.leerAula());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> iteradorLrA = reservas.iterator(); iteradorLrA.hasNext();) {
				Reserva reserva = iteradorLrA.next();

				System.out.println(reserva);
			}
		}else {
			System.out.println("No existen reservas para este aula.");
		}
	}
	//invocar método listar reservas por profesor en Reservas
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Lista de reservas por profesor");
		List<Reserva> reservas= controlador.getReservasProfesor(Consola.leerProfesor());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> iteradorLrP = reservas.iterator(); iteradorLrP.hasNext();) {
				Reserva reserva = iteradorLrP.next();

				System.out.println(reserva);
			}
		}else {
			System.out.println("No existen reservas para este profesor");
		}
	}
	
	//invocar método listar reservar por permanencia en Reservas
	public void listarReservasPermanencia() {
		Consola.mostrarCabecera("Lista de reservas por permanencia");
		Permanencia permanencia= new Permanencia(Consola.leerDia(), Consola.leerTramo());
		List<Reserva> reservas= controlador.getReservasPermanencia(permanencia);
		if (reservas.size() > 0) {
			for (Iterator<Reserva> iteradorLrPe = reservas.iterator(); iteradorLrPe.hasNext();) {
				Reserva reserva = iteradorLrPe.next();

				System.out.println(reserva);
			}
		}else {
			System.out.println("No existen reservas para esta permanencia");
		}
	}
	
	//invocación de método consultar disponibilidad en Reservas
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar disponibilidad");
		String nombreAula;
		String nombreAulas;
		List<String> aulas= controlador.representarAulas();
		LocalDate dia;
		Tramo tramo;
		//bandera
		boolean aulaRegistrada= false;
		
		try {
			nombreAula= Consola.leerNombreAula();
			
			for (Iterator<String> iteradorCD = aulas.iterator(); iteradorCD.hasNext();) {
				nombreAulas = iteradorCD.next();
					aulaRegistrada= true;
					
					if (nombreAulas.toString().replace("nombre Aula=", "").equals(nombreAula)) {
						aulaRegistrada = true;

						Aula aulaAConsultar = new Aula(nombreAula);
						dia = Consola.leerDia();
						tramo = Consola.leerTramo();
						Permanencia permanencia = new Permanencia(dia, tramo);

						if (controlador.consultarDisponibilidad(aulaAConsultar, permanencia) == true) {
							System.out.println("Disponible el aula " + nombreAula + " para reservar el día " + dia
									+ " en el tramo de " + tramo + ".");
						} else {
							System.out.println("No Disponible el aula " + nombreAula + " para reservar el día " + dia
									+ " en el tramo de " + tramo + ".");
						}

					}
				}

				if (!aulaRegistrada) {
					System.out.println(ERROR + "No está registrada el aula " + nombreAula + " en el sistema.");

				}
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
}

