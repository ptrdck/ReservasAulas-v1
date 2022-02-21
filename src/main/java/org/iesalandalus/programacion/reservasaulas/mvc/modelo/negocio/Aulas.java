package org.iesalandalus.programacion.reservasaulas.MVC.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio.Aula;

public class Aulas {

	//inicializacion de variables y array
	private int capacidad;
	private int tamano;
	//(0...*)
	private Aula[] coleccionAulas;
	
	//Constructor con parametro
	public Aulas(int capacidad) {
		if (capacidad<= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser superior a 0");
		}
		this.capacidad= capacidad;
		coleccionAulas= new Aula[capacidad];
	}
	public Aula[] get() {
		return copiaProfundaAulas();
	}
	
	//Constructor copia en array
	private Aula[] copiaProfundaAulas() {
		Aula[] copiaAulas= new Aula[capacidad];
		for (int i=0; !tamanoSuperado(i); i++) {
			copiaAulas[i] = new Aula(coleccionAulas[i]);
		}
		return copiaAulas;
	}
	
	public int getTamano() {
		return tamano;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	
	public void insertar (Aula aula) throws OperationNotSupportedException {
		if (aula== null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula.");
		}
		
		// Comparación entre tamaño y capacidad. condicion tamano <= capacidad
		
		if (capacidadSuperada(tamano)) {
			throw new OperationNotSupportedException("ERROR: No quedan más aulas disponibles.");
		}
		int indice= buscarIndice(aula);
		if (tamanoSuperado(indice)) {
			coleccionAulas[tamano] = new Aula(aula);
			// incremento en tamaño
			tamano++;
		} else {
			throw new OperationNotSupportedException("ERROR: Ese nombre ya existe para un aula.");
		
		}
	}
	//método que devuelve la posición de aula en el array
	private int buscarIndice(Aula aula) {
		int indice= 0;
		boolean encontrado= false;
		
		// booleano recorre el array y finaliza sin necesidad de recorrer todo el tamaño
		
		while(!tamanoSuperado(indice) && !encontrado){
			if (coleccionAulas[indice].equals(aula)) {
				encontrado= true;
			} else {
				indice++;
			}
		}
		return indice;
		}
	
	//validador de metodo tamañoSuperado
	private boolean tamanoSuperado(int indice) {
		//Si indice >= tamano devuelve true
		return indice>= tamano;
	}
	
	//validador de metodo capacidadSuperada
	private boolean capacidadSuperada(int indice) {
		//Si indice>= capacidad devuelve true
		return indice>= capacidad;
	}
	
	//Constructor con parámetro
	public Aula buscar(Aula aula) {
		int indice= buscarIndice(aula);
		
		if (aula== null) {
			throw new NullPointerException("ERROR: No existe un aula nula.");
		}
		if(!tamanoSuperado(indice)) {
			return new Aula(coleccionAulas[indice]);
		} else {
			return null;
		}
	}
	
	//Metodo borrar:
	//Localizar posición de aula en el array y desplazamos posición para borrar
	
	public void borrar(Aula aula) throws OperationNotSupportedException{
		if (aula== null) {
			throw new NullPointerException("ERROR: No se puede borrar un aula nula");
		}
		int indice= buscarIndice(aula);
		if (!tamanoSuperado(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		//reducir tamaño
			tamano--;
		} else {
			throw new OperationNotSupportedException("ERROR: No hay coincidencias con el nombre ingresado.");
		}
		
	}
	//metodo desplazamiento de elementos en array
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for(int i= indice; i< coleccionAulas.length -1; i++) {
			//Reordenamiento del array:
			//Recorre todo el array desplazando hacia la izquierda
			//Orden ascendente
			coleccionAulas[i]= coleccionAulas[i+1];
			
		}
	}
	
	//metodo para Representar elementos del array
	public String[] representar() {
		String[] representacion= new String[tamano];
		for (int i= 0; !tamanoSuperado(i); i++) {
			representacion[i]= coleccionAulas[i].toString();	
		}
		return representacion;
	}
}
