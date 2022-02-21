package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

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
			throw new NullPointerException("ERROR: El nombre del profesor no puede ser nulo.");
		}
		if (nombre.isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre del profesor no puede estar vacío.");
		}
		this.nombre = formateaNombre(nombre);
	}
	private String formateaNombre(String nombre) {
		// damos el formato inicial llevando todos los caracteres a minúsculas y reemplazando todos los espacios en blanco para que el nombre de salid esté todo junto
		nombre = nombre.replace("\\s+", "").trim().toLowerCase();
		String nombreLargo = "";
		//iniciamos la variable de salida que se llamará nombreLargo
		nombreLargo += nombre.substring(0, 1).toUpperCase();
		
		// ciclo for que recorre la cadena String
		for (int i=0;i< nombre.length();i++ ) {
			//condicion para que transforme a mayúscula el caracter siguiente en caso de que encuentre un espacio vacío
			if (nombre.charAt(i-1) == ' '){
				nombreLargo += nombre.substring(i, i+1).toUpperCase();
			} else {
				nombreLargo += nombre.substring(i, i+1);
			}
		}
		nombre= nombreLargo.trim();
		return nombreLargo;
	}
	//validación correo
	public void setCorreo(String correo) {
		if (correo== null) {
			throw new NullPointerException("ERROR: El correo del profesor no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del profesor no es válido.");
		}
		this.correo = correo;
	}
	//validación teléfono
	public void setTelefono(String telefono) {
		if (telefono== null || !telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del profesor no es válido.");
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
		Profesor other = (Profesor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		//condición de que pueda salir o no el teléfono. 
		String cadenaTelefono=(getTelefono()== null)? "": ", telefono=" +getTelefono();
		return "nombre=" + getNombre() + ", correo=" + getCorreo() + cadenaTelefono ;
	}
	
}
