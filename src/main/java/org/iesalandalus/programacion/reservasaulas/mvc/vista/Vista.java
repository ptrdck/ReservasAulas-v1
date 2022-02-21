package org.iesalandalus.programacion.reservasaulas.MVC.vista;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.MVC.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Tramo;

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
		
		String [] aulas= controlador.representarAulas();
		if (aulas.length> 0) {
			for (String aula: aulas) {
				System.out.println(aula);
			}
		}else {
			System.out.println(ERROR + "No existen aulas para listar. Ingrese un aula en el sistema.");
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
			System.out.println("Profesor insertado con éxito.");
			
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
		
		String[] profesores= controlador.representarProfesores();
		if (profesores.length> 0) {
			for (String profesor: profesores) {
				System.out.println(profesor);
			}
		}else {
			System.out.println(ERROR + "No existen profesores en la lista. Ingrese un profesor en el sistema.");
		}
	}
	
	//opción que invoca método de insertar reserva
	public void realizarReserva() {
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
		String[] profesores= controlador.representarProfesores();
		String[] aulas= controlador.representarAulas();
		String correoProfesor= new String();
		String correoProfesorCl= new String();
		
		//inicialización de variables
		Reserva reserva= null;
		Aula aula= null;
		Permanencia permanencia= null;
		boolean aulaRegistrado= false;
		boolean profesorRegistrado= false;
		
		try {
			nombreProfesor= Consola.leerNombreProfesor();
			nombreAula= Consola.leerNombreAula();
			
			//recorrer array y mostrar infrormación de profesores. Guarda información en cada array como parámetro
			for (int i= 0; i< profesores.length; i++) {
				String infoProfesores= profesores[i].toString();
				
				//sentencia condicional para comparar el nombre del profesor introducido con
				//el resultado de profesores con toString.
				//Validación a través del método indexof
				
				if (nombreProfesor.equals(infoProfesores.substring(infoProfesores.indexOf('=') +1, infoProfesores.indexOf(',') ))){
					profesorRegistrado= true;
				
				//A través del mismo método se obtiene información como correo especificando la posición de la cadena donde se encuentra correo
				
					correoProfesor= infoProfesores.substring(infoProfesores.indexOf('=') +1, infoProfesores.lastIndexOf(',') );
					
					//se obvian los datos no importantes. Pasamos correo como parámetro
					
					correoProfesorCl= correoProfesor.replace(nombreProfesor + ", correo=","");
				}	
			}
		//validación de aula existente. 
			//Replace para obtener la cadena que se necesita
			for (int j= 0; j< aulas.length; j++) {
				if (aulas[j].toString().replace("Aula=" , "").equals(nombreAula)) {
					aula= new Aula(nombreAula);
					aulaRegistrado= true;
				}
			}
		//condicional de errores	
			if(!profesorRegistrado) {
				System.out.println(ERROR + " El profesor " + nombreProfesor + " no está registrado en el sistema");
			}
			if(!aulaRegistrado) {
				System.out.println(ERROR + " El aula " + nombreAula + " no está registrado en el sistema.");
			}
			//asignación 
			permanencia= new Permanencia(Consola.leerDia(), Consola.leerTramo());
			Profesor leerProfesor= new Profesor(nombreProfesor, correoProfesorCl);
			
			reserva= new Reserva(leerProfesor, aula, permanencia);
			//captura de excepciones
		}catch (NullPointerException | IllegalArgumentException e) {
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
			
		String[] reservas= controlador.representarReservas();
		if (reservas.length> 0) {
			for (String reserva : reservas) {
				System.out.println(reserva);	
			}
		}else {
			System.out.println("No existen reservas en la lista. Ingrese una reserva.");
		}
	}
	//método para listar reservas con el parámetro aulas invocado en Reservas
	public void listarReservasAula() {
		Consola.mostrarCabecera("Lista de reservas por aula");
		Reserva[] reservas= controlador.getReservasAula(Consola.leerAula());
		if (reservas[0]!= null) {
			for(Reserva reserva: reservas) {
				if (reserva!= null) 
					System.out.println(reserva);
				}
		}else {
			System.out.println("No existen reservas para este aula.");
		}
	}
	//invocar método listar reservas por profesor en Reservas
	public void listarReservasProfesor() {
		Consola.mostrarCabecera("Lista de reservas por profesor");
		Reserva[] reservas= controlador.getReservasProfesor(Consola.leerProfesor());
		if (reservas[0]!= null) {
			for (Reserva reserva: reservas) {
				if (reserva== null)
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
		Reserva[] reservas= controlador.getReservasPermanencia(permanencia);
		if (reservas[0]!= null) {
			for (Reserva reserva: reservas) {
				if (reserva!= null)
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
		String[] aulas= controlador.representarAulas();
		LocalDate dia;
		Tramo tramo;
		//booleando para recorrer array
		boolean aulaRegistrada= false;
		
		try {
			nombreAula= Consola.leerNombreAula();
			
			//for para recorrer array
			// dentro de for, metodo replace para obtener la cadena que se busca
			for(int i= 0; i< aulas.length; i++) {
				if(aulas[i].toString().replace("nombre aula=", "").equals(nombreAula)) {
					
					aulaRegistrada= true;
					
					Aula consultaAula= new Aula(nombreAula);
					dia= Consola.leerDia();
					tramo= Consola.leerTramo();
					Permanencia permanencia= new Permanencia(dia, tramo);
					
					if (controlador.consultarDisponibilidad(consultaAula, permanencia)== true) {
						System.out.println("El aula " + nombreAula + " está disponible para el día " + dia + "durante el tramo de " + tramo + ".");
					}else {
						System.out.println("El aula "+ nombreAula + " no está disponible para el día " + dia + "durante el tramo de " +tramo+ ".");
					}
				}
					
			}
			if(!aulaRegistrada) {
				System.out.println(ERROR + "El aula " + nombreAula + " no se encuentra registrada en el sistema.");
			}
		}catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
