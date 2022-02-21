package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Aula {
	
	private String nombre;
	
	//Constructor con parametro
	public Aula(String nombre) {
		setNombre(nombre);
		}
	//Constructor copia
	public Aula(Aula aula) {
		if (aula== null) {
			throw new NullPointerException("ERROR: No se puede copiar un aula nula.");
		}
		
		setNombre(aula.getNombre());
	}
	
	//validación nombre
	private void setNombre(String nombre) {
		if (nombre== null) {
			throw new NullPointerException("ERROR: El nombre del aula no puede ser nulo");
		}
		if (nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre del aula no puede ser vacío.");
		}
		this.nombre= nombre;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Aula=" + getNombre();
	}


	
	
	
	
	
	

}
