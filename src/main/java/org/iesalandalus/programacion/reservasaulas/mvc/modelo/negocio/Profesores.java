package org.iesalandalus.programacion.reservasaulas.MVC.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Profesor;

public class Profesores {
	
	//(0,1)
	private int tamano;
	private int capacidad;
	private Profesor[] coleccionProfesores;
	
	//Constructor con sus parámetros
	public Profesores(int capacidad) {
		if (capacidad<= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser superior a 0.");
		}
		this.capacidad= capacidad;
		coleccionProfesores= new Profesor[capacidad];
		
	}

	public Profesor[] get() {
		return copiaProfundaProfesores();
	}
	
	//Constructor copia en array
	private Profesor[] copiaProfundaProfesores() {
		Profesor[] copiaProfesores= new Profesor[capacidad];
		for (int i=0; !tamanoSuperado(i); i++) {
			copiaProfesores[i]= new Profesor(coleccionProfesores[i]);
		}
		return copiaProfesores;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	//método insertar profesor
	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		
		if (profesor== null) {
			throw new NullPointerException("ERROR: El profesor a insertar no puede ser nulo.");
		}
		
		//comparación de tamaño y capacidad para comprobar que tamano no exceda a capacidad
		if(capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No hay espacio para más profesores.");
		}
		int indice= buscarIndice(profesor);
		//Si hay inserción, tamaño se incrementa
		if (tamanoSuperado(indice)) {
			coleccionProfesores[tamano]= new Profesor(profesor);
			tamano++;
		}else {
			throw new OperationNotSupportedException("ERROR: No puede insertar un profesor ya existente.");
		}
	}
	
	//Método índice para buscar la posición donde se encuentra profesor
	private int buscarIndice(Profesor profesor) {
		int indice=0;
		boolean encontrado= false;
		while (!tamanoSuperado(indice) && !encontrado) {
			if (coleccionProfesores[indice].equals(profesor)) {
				encontrado= true;
			} else {
				indice++;
			}
		}
		return indice;
	}
	
	//método que compara si se ha excedido el tamaño del array
	//devolerá true si indice es igual o superior que tamano
	private boolean tamanoSuperado(int indice) {
		return indice>= tamano;
	}
	
	//método que compara si se ha excedido la capacidad del array
	//De vuelve true si la condición se cumple
	private boolean capacidadSuperada(int indice) {
		return indice>= capacidad;
	}
	
	//método para buscar un profesor en colección profesores
	public Profesor buscar(Profesor profesor) {
		int indice= buscarIndice(profesor);
		
		if (profesor== null) {
			throw new NullPointerException("ERROR: Profesor no puede ser nulo.");
		}
		
		if (!tamanoSuperado(indice)) {
			return new Profesor(coleccionProfesores[indice]);
		} else {
			return null;
		}
	}
	
	//método para borrar profesor a través de desplazamiento de elementos en array
	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		if (profesor== null) {
			throw new NullPointerException("ERROR: El profesor a borrar no puede ser nulo.");
		}
		int indice= buscarIndice(profesor);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
			//decremento de tamaño
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No hay coincidencias en profesor con el nombre ingresado.");
		}
	}
	
	//Método desplazar elementos del array desde el primer elemento al último
	//Ascendente
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i= indice; i< coleccionProfesores.length -1; i++) {
			coleccionProfesores[i]= coleccionProfesores[i+1];
		}
	}
	
	//Método para recorrer todo el array y mostrar sus elementos
	public String[] representar() {
		
		String[] representacion= new String[tamano];
		
		for (int i=0; !tamanoSuperado(i); i++) {
			representacion[i]= coleccionProfesores[i].toString();
		}
		return representacion;
	}
}