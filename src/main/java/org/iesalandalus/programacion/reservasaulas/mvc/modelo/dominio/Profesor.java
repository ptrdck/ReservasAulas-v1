package org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio;

import java.util.Objects;

public class Profesor {
	
	private static final String ER_TELEFONO= "(9|6)[0-9]{8}";
	private static final String ER_CORREO= ".+@[a-zA-Z]+\\.[a-zA-z]+";
	
	private String nombre;
	private String correo;
	private String telefono;
	
	//Constructor con parámetros
	public Profesor (String nombre, String correo){
		setNombre(nombre);
		setCorreo(correo);
	
	}
	//Constructor con parámetros, array tridimensional
	public Profesor (String nombre, String correo, String telefono){
		setNombre(nombre);
		setCorreo(correo);
		setTelefono(telefono);
	
	}
	//Constructor copia
	public Profesor (Profesor profesor) {
		if (profesor== null) {
			throw new NullPointerException("ERROR: No se puede copiar un profesor nulo.");
		}
		setNombre(profesor.getNombre());
		setCorreo(profesor.getCorreo());
		setTelefono(profesor.getTelefono());
	}
	//validación nombre
	private void setNombre(String nombre) {
		if (nombre== null) {
			throw new NullPointerException("ERROR: El nombre de un profesor no puede ser nulo");
		}
		if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre de un profesor no puede ser vacío");
		}
		this.nombre = nombre;
	}
	//validación correo
	public void setCorreo(String correo) {
		if (correo== null) {
			throw new NullPointerException("ERROR: El correo de un profesor no puede ser nulo");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo indicado no es válido");
		}
		this.correo = correo;
	}
	//validación teléfono
	public void setTelefono(String telefono) {
		if (telefono== null || !telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR:El teléfono indicado no es válido.");
		}
		this.telefono = telefono;
	}
	

	public String getNombre() {
		return nombre;
	}



	public String getCorreo() {
		return correo;
	}

	

	public String getTelefono() {
		return telefono;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Profesor [nombre=" + getNombre() + ", correo=" + getCorreo() + ", telefono=" + getTelefono() + "]";
	}
	
}
