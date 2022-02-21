package org.iesalandalus.programacion.reservasaulas.MVC.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Reserva;

public class Reservas {
	
	//(0,1)
	private int tamano;
	private int capacidad;
	private Reserva[] coleccionReservas;
	
	//Constructor con parámetros
	public Reservas(int capacidad) {
		if (capacidad<= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser superior a 0.");
		}
		this.capacidad= capacidad;
		coleccionReservas= new Reserva[capacidad];
	}
	
	//Método Get
	public Reserva[] get() {
		return copiaProfundaReservas();
	}
	
	//Constructor copia en array
	private Reserva[] copiaProfundaReservas() {
		Reserva[] copiaReservas= new Reserva[capacidad];
		for (int i= 0; !tamanoSuperado(i); i++) {
			copiaReservas[i]= new Reserva(coleccionReservas[i]);
		}
		return copiaReservas;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	//método insertar
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva== null) {
			throw new NullPointerException("ERROR: El aula a insertar no puede ser nula.");
			
			//comparación entre tamaño y capacidad.
			//El tamaño no puede ser mayor a capacidad
		}
		if(capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No quedan más reservas.");
			}
		int indice= buscarIndice(reserva);
		if (!tamanoSuperado(indice)) {
			//inserción
			coleccionReservas[tamano]= new Reserva(reserva);
			//incremento
			tamano++;
		} /*else {
			throw new OperationNotSupportedException("ERROR: Reserva ya existe");
		}*/
	}
	
	//Método buscar indice
	private int buscarIndice(Reserva reserva) {
		int indice= 0;
		boolean encontrado= false;
		//booleano recorre array hasta encontrar el indice antes de llegar al final del tamaño de array
		while (!tamanoSuperado(indice) && !encontrado) {
			if (coleccionReservas[indice].equals(reserva)) {
				encontrado= true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean tamanoSuperado(int indice) {
		//si indice es igual o mayor a tamaño devolverá true
		return indice>= tamano;
	}
	
	private boolean capacidadSuperada(int indice) {
		//si indice es mayor o igual a capacidad devolverá true
		return indice>=capacidad;
	}
	
	//Constructor para realizar búsqueda de reserva
	public Reserva buscar(Reserva reserva) throws OperationNotSupportedException {
		
		int indice= buscarIndice(reserva);
		
		if (reserva== null) {
			throw new NullPointerException("ERROR: reserva no puede ser nula.");
		}
		
		
		if (!tamanoSuperado(indice)) {
			return new Reserva(coleccionReservas[indice]);
		} else {
			return null;
		}
	}
	
	//Contructor borrar a través del desplazamiento de elementos de array.
	public void borrar(Reserva reserva) throws OperationNotSupportedException{
		
		if (reserva== null) {
			throw new NullPointerException("ERROR: El aula a borrar no puede ser nula.");
		}
		
		int indice= buscarIndice(reserva);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			//después de desplazar, reducimos tamaño
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: Nombre de aula no encontrado.");
		}
	}
	//método de desplazamiento que se usa en constructor borrar
	//Desplazamiento hacia la izquierda: recorre desde el primero al último de forma ascendente
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i= indice; i< coleccionReservas.length -1; i++) {
			coleccionReservas[i]= coleccionReservas[i+1];
		}
	}
	
	//Para recorrer todo el array y representar sus elementos
	public String[] representar() {
		
		String[] representacion= new String[tamano];
		for (int i= 0; !tamanoSuperado(i); i++) {
			representacion[i]= coleccionReservas[i].toString();
		}
		return representacion;
	}
	
	//método que almacena reservas en un array con el parámetro profesor
	public Reserva[] getReservasProfesor(Profesor profesor) {
		
		Reserva[] reserva= new Reserva[capacidad];
		
		int j= 0;
		
		for (int i= 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getProfesor().equals(profesor)) {
				reserva[j++]= coleccionReservas[i];
			}
		}
		return reserva;
	}
	// método que almacena reservas en el array con el parámetro aula
	public Reserva[] getReservasAulas(Aula aula) {
		Reserva[] reserva= new Reserva[capacidad];
		
		int j= 0;
		
		for (int i= 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getAula().equals(aula)) {
				reserva[j++]= coleccionReservas[i];
			}
		}
		return reserva;
	}
	
	//método que almacena reservas en el array con el parámetro Permanencia
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		Reserva[] reserva= new Reserva[capacidad];
		
		int j= 0;
		for (int i= 0; !tamanoSuperado(i); i++) {
			if (coleccionReservas[i].getPermanencia().equals(permanencia)) {
				reserva[j++]= coleccionReservas[i];
			}
		}
		return reserva;
	}
	
	//méotodo para consulta de disponibilidad de reservas con los atributos de aula y permanencia
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		boolean disponible= true;
		
		if (aula== null) {
			throw new NullPointerException("ERROR: El aula a consultar disponibilidad no puede ser nula.");
		}
		if (permanencia== null) {
			throw new NullPointerException("ERROR: La permanencia no puede ser nula.");
		}
		for (int i= 0; !tamanoSuperado(i); i++) {
			if (aula.equals(coleccionReservas[i].getAula()) && permanencia.equals(coleccionReservas[i].getPermanencia())){
				disponible= false;
				}	
			}
		return disponible;
		}
}
