package org.iesalandalus.programacion.reservasaulas;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.Controlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.Modelo;
import org.iesalandalus.programacion.reservasaulas.mvc.vista.Vista;



/*
 * Tarea Online 7, Programación, Ciclo DAM 21-22, IES Al-Andalús, Almería
 * @author Pedro Patricio Cárdenas Figueroa
 * 
 */
public class MainApp {

	public static void main(String[] args) {
		System.out.println("Programa para la gestión de reservas de espacios del IES Al-Ándalus");
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);
		controlador.comenzar();
		
	}

}
