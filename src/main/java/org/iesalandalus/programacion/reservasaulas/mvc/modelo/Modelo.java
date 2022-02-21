package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Aulas;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.Reservas;

public class Modelo {
	
	//(0,1)
	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;
	
	//Constructor
	public Modelo() {
		profesores= new Profesores();
		aulas= new Aulas();
		reservas= new Reservas();
	}
	
	//método que invoca a la clase reservas
	public List<Aula> getAulas() {
		return aulas.getAulas();
		}
	
	//tamaño de array aulas
	public int getNumAulas() {
		
		return aulas.getNumAulas();
	}
	//devuelve información de aulas
	public List<String> representarAulas() {
		return aulas.representar();
	}
	
	//invoca al método buscar aula
	public Aula buscar(Aula aula) {
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
	public List<Profesor> getProfesores() {
		return profesores.getProfesores();
	}
	//devuelve el tamaño del array profesores
	public int getNumProfesores() {
		return profesores.getNumProfesores();
	}
	//devuelve información de profesores
	public List<String> representarProfesores() {
		return profesores.representar();
	}
	//invoca al método buscar profesor en profesor
	public Profesor buscar(Profesor profesor) {
		return profesores.buscar(profesor);
	}
	//invoca al método insertar profesor en profesor
	public void insertar(Profesor profesor) throws OperationNotSupportedException{
		profesores.insertar(profesor);
	}
	//invoca al método borrar en profesor
	public void borrar(Profesor profesor) throws OperationNotSupportedException{
		profesores.borrar(profesor);
	}
	//devuelve las reservas según su posición en el array
	public List<Reserva> getReservas() {
		return reservas.getReservas();
	}
	
	//devuelve tamaño del array reservas
	public int getNumReservas() {
		return reservas.getNumReservas();	
	}
	//devuelve información de cada reserva
	public List<String> representarReservas() {
		return reservas.representar();
	}
	//Invoca a método buscar en reserva
	public Reserva buscar(Reserva reserva) throws OperationNotSupportedException {
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
	public List<Reserva> getReservasAula(Aula aula) {
		return reservas.getReservasAulas(aula);
	}
	//devuelve reservas con atributo profesor
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		return reservas.getReservasProfesor(profesor);
	}
	//devuelve reservas con antributo permanencia
	public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
		return reservas.getReservasPermanencia(permanencia);
	}
	//invoca al método consultar disponibilidad en Reservas
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		return reservas.consultarDisponibilidad(aula, permanencia);
	}
	
}
