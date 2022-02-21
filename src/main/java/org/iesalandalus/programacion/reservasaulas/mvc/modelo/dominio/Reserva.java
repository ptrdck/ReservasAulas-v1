package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import java.util.Objects;

public class Reserva {
	
	//(0,1)
	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;
	
	//Constructor con parámetros
	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
		
		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);
	}
	
	//Constructor copia
	public Reserva(Reserva reserva) {
		if (reserva== null) {
			throw new NullPointerException("ERROR: no se puede copiar una reserva nula");
		}
		setProfesor(reserva.getProfesor());
		setAula(reserva.getAula());
		setPermanencia(reserva.getPermanencia());
	}
	
	//validador profesor
	private void setProfesor(Profesor profesor) {
		if (profesor== null) {
			throw new NullPointerException("ERROR: La reserva debe tener un nombre de profesor a cargo");
		}
		this.profesor= profesor;
		
	}
	public Profesor getProfesor() {
		return profesor;
	}
	
	//Validador aula
	private void setAula(Aula aula) {
		if (aula== null) {
			throw new NullPointerException("ERROR: La reserva no puede ser para un aula nula.");
		}
		this.aula= aula;
	}
	public Aula getAula() {
		return aula;
	}
	
	//validador permanencia
	private void setPermanencia(Permanencia permanencia) {
		if (permanencia== null) {
			throw new NullPointerException("ERROR: La reserva debe tener una permanencia no nula.");
			
		}
		this.permanencia= permanencia;
	}
	public Permanencia getPermanencia() {
		return permanencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(profesor, aula, permanencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(aula, other.aula) && Objects.equals(permanencia, other.permanencia)
				&& Objects.equals(profesor, other.profesor);
	}

	@Override
	public String toString() {
		return "Reserva [profesor=" + getProfesor() + ", aula=" + getAula() + ", permanencia=" + getPermanencia() + "]";
	}
	
	
}
