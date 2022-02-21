package org.iesalandalus.programacion.reservasaulas.MVC.modelo;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.negocio.Reservas;

public class Modelo {
	
	private static final int CAPACIDAD= 5;
	//(0,1)
	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;
	
	//Constructor
	public Modelo() {
		profesores= new Profesores(CAPACIDAD);
		aulas= new Aulas(CAPACIDAD);
		reservas= new Reservas(CAPACIDAD);
	}
	
	//método que invoca a la clase reservas
	public Aula[] getAulas() {
		return aulas.get();
		}
	
	//tamaño de array aulas
	public int getNumAulas() {
		
		return aulas.getTamano();
	}
	//devuelve información de aulas
	public String[] representarAulas() {
		return aulas.representar();
	}
	
	//invoca al método buscar aula
	public Aula buscarAula(Aula aula) {
		return aulas.buscar(aula);
	}
	//Invoca al méotodo insertar aula
	public void insertar(Aula aula) throws OperationNotSupportedException {
		aulas.insertar(aula);
	}
	//Invoca al método borrar aula
	public void borrar(Aula aula) throws OperationNotSupportedException {
		aulas.borrar(aula);
	}
	
	//invoca a los méotodos de profesor
	public Profesor[] getProfesores() {
		return profesores.get();
	}
	//devuelve el tamaño del array profesores
	public int getNumProfesores() {
		return profesores.getTamano();
	}
	//devuelve información de profesores
	public String[] representarProfesores() {
		return profesores.representar();
	}
	//invoca al método buscar profesor en profesor
	public Profesor buscarProfesor(Profesor profesor) {
		return profesores.buscar(profesor);
	}
	//invoca al método insertar profesor en profesor
	public void insertarProfesor(Profesor profesor) throws OperationNotSupportedException{
		profesores.insertar(profesor);
	}
	//invoca al método borrar en profesor
	public void borrarProfesor(Profesor profesor) throws OperationNotSupportedException{
		profesores.borrar(profesor);
	}
	//devuelve las reservas según su posición en el array
	public Reserva[] getReservas() {
		return reservas.get();
	}
	
	//devuelve tamaño del array reservas
	public int getNumReservas() {
		return reservas.getTamano();	
	}
	//devuelve información de cada reserva
	public String[] representarReservas() {
		return reservas.representar();
	}
	//Invoca a método buscar en reserva
	public Reserva buscarReserva(Reserva reserva) throws OperationNotSupportedException {
		return reservas.buscar(reserva);
	}
	//invoca al méotodo insertar en reserva
	public void realizarReserva(Reserva reserva) throws OperationNotSupportedException{
		reservas.insertar(reserva);
	}
	//invoca el método borrar en reserva
	public void anularReserva(Reserva reserva) throws OperationNotSupportedException{
		reservas.borrar(reserva);
	}
	//devuelve reservas por atríbuto aula.
	public Reserva[] getReservasAula(Aula aula) {
		return reservas.getReservasAulas(aula);
	}
	//devuelve reservas con atributo profesor
	public Reserva[] getReservasProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}
	//devuelve reservas con antributo permanencia
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}
	//invoca al método consultar disponibilidad en Reservas
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
	
}
