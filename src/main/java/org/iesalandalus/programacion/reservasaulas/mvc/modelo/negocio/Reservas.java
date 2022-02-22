package org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Reservas {
	
	//inicializacion coleccionReservas (0.....*)
	private List<Reserva> coleccionReservas;
	
	//Constructor 
	public Reservas() {
		coleccionReservas= new ArrayList<>();
	}
	
	//Constructor copia
	public Reservas(Reservas reservas) {
		if(reservas== null) {
			throw new NullPointerException("ERROR: No se pueden copiar reservas nulas.");
		}
		setReservas(reservas);
	}
	//Método setreservas. Convierte un objeto de reservas en un arraylist asignando a coleccion
	private void setReservas(Reservas reservas) {
		if(reservas== null) {
			throw new NullPointerException("ERROR: No se puede copiar una reserva nula.");
		}
		coleccionReservas= copiaProfundaReservas(reservas.coleccionReservas);	
	}
	//Método para crear una copia profunda del arraylist. 
	//solucion a Aliasing
	private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> copiaReservas= new ArrayList<>();
		Iterator<Reserva> iteradorReservas= reservas.iterator();
		while(iteradorReservas.hasNext()) {
			copiaReservas.add(new Reserva(iteradorReservas.next()));
		}
		return copiaReservas;
	}

	//Método Get
	public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}
	
	//método para retornar tamaño de arraylist de colección
	public int getNumReservas() {
		return coleccionReservas.size();
	}
	//método insertar. Comprobación de nulos. Recorre coleccionReservas buscando alguna existencia. 
	//Retorna excepción si Reserva está duplicada. 
	//Retorna una copia de Reserva como parámetro.
	public void insertar(Reserva reserva) throws OperationNotSupportedException {
		if (reserva== null) {
			throw new NullPointerException("ERROR: No se puede realizar una reserva nula.");
		}
		else if(buscar(reserva)== null){
			coleccionReservas.add(new Reserva(reserva));	
		}
		else {
			throw new OperationNotSupportedException("ERROR: La reserva ya existe.");
		}
	}
	
	//Método que valida mes de fecha de reserva sólo si la reserva se realiza con posterioridad al mes actual
	//método compare to que devolverá true si el mes de reserva es posterior 
	//al mes donde se quiere realizar la reserva
	private boolean esMesSiguienteOPosterior(Reserva reserva) {
		if (reserva== null) {
			throw new NullPointerException("ERROR La reserva no puede ser nula.");
		}
		//bandera
		boolean mesSiguiente= false;
		Month mes= reserva.getPermanencia().getDia().getMonth();
		Month mesHoy= LocalDate.now().getMonth();
		if (mes.compareTo(mesHoy)>0) {
			mesSiguiente=true;
		} else {
			mesSiguiente= false;
		}
		return mesSiguiente;
		
	}
	
	//Método que ingresa profesor y mes para retornar el mes de la reserva.
	//devolverá la fecha como mes
	//Extrae mes de reserva como también LocalDate como parámetro y comparará profesor de reserva
	//Compara meses. Si ambos coinciden entonces copia la reserva en la lista creada. 
	private List<Reserva> getReservasProfesorMes(Profesor profesor, LocalDate fecha){
		if (profesor== null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo");
		}
		else if (fecha== null){
			throw new NullPointerException("ERROR: La fecha no puede ser nula");
		}
		
		List<Reserva> reservasMes= new ArrayList<>();
		Iterator<Reserva> iteradorMes= coleccionReservas.iterator();
		while (iteradorMes.hasNext()) {
			Reserva reserva= iteradorMes.next();
			Month mesAL= reserva.getPermanencia().getDia().getMonth();
			Month mesFecha= fecha.getMonth();
			if( (profesor.equals(reserva.getProfesor())) && (mesAL.compareTo(mesFecha)== 0) ) {
				reservasMes.add(new Reserva(reserva));	
			}
		}
		return reservasMes;
	}
	
	//método análogo a ReservaProfesorMes
	public Reserva getReservaAulaDia(Aula aula, LocalDate fecha) {
		if (aula== null) {
			throw new NullPointerException("ERROR: El aula no puede ser nula");
		} else if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula");
		}
		Reserva reservaAD=null;
		Iterator<Reserva> iteradorReservas= coleccionReservas.iterator();
		while (iteradorReservas.hasNext()) {
			Reserva reserva= iteradorReservas.next();
			if ((aula.equals(reserva.getAula())) && (fecha.compareTo(reserva.getPermanencia().getDia())== 0)){
				reservaAD= new Reserva(reserva);
			}
		}
		return reservaAD;
	}
	
	//ACtualización de método buscar
	public Reserva buscar(Reserva reserva) {
		
		if (reserva== null) {
			throw new NullPointerException("ERROR: No se puede buscar un reserva nula.");
		}
		
		if (coleccionReservas.contains(reserva)) {
			return new Reserva(reserva);
		} else {
			return null;
		}
	}
	
	//Contructor borrar a través del desplazamiento de elementos de array.
	public void borrar(Reserva reserva) throws OperationNotSupportedException{
		
		if (reserva== null) {
			throw new NullPointerException("ERROR: No se puede anular una reserva nula.");
		}
		
		if (coleccionReservas.contains(reserva)) {
			coleccionReservas.remove(reserva);
		} else {
			throw new OperationNotSupportedException("ERROR: La reserva a anular no existe.");
		}
	}
	
	//Método para representación en forma de ArrayList
	public List<String> representar() {
		
		List<String> representacion= new ArrayList<>();
		Iterator<Reserva> iteradorReserva= coleccionReservas.iterator();
		while(iteradorReserva.hasNext()) {
			representacion.add(iteradorReserva.next().toString());
		}
		return representacion;
	}
	
	//método que crea listas de reservas
	public List<Reserva> getReservasProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede anula una reserva nula.");
		}
		List<Reserva> reservaListaProf= new ArrayList<>();
		Iterator<Reserva> iteradorReservas= coleccionReservas.iterator();
		while (iteradorReservas.hasNext()) {
			Reserva reservaIt= iteradorReservas.next();
			if(profesor.equals(reservaIt.getProfesor())) {
				reservaListaProf.add(new Reserva(reservaIt));
			}
		}
		return reservaListaProf;
	}
	//método que crea listas de reservas con el parámetro aula
		public List<Reserva> getReservasAulas(Aula aula) {
			if (aula == null) {
				throw new NullPointerException("ERROR: No se puede anula una reserva nula.");
			}
			List<Reserva> reservaListaAu= new ArrayList<>();
			Iterator<Reserva> iteradorReservas= coleccionReservas.iterator();
			while (iteradorReservas.hasNext()) {
				Reserva reservaIt= iteradorReservas.next();
				if(aula.equals(reservaIt.getAula())) {
					reservaListaAu.add(new Reserva(reservaIt));
				}
			}
			return reservaListaAu;
		}
		//método que crea listas de reservas
		public List<Reserva> getReservasPermanencia(Permanencia permanencia) {
			if (permanencia == null) {
				throw new NullPointerException("ERROR: No se puede anula una reserva nula.");
			}
			List<Reserva> reservaListaPer= new ArrayList<>();
			Iterator<Reserva> iteradorReservas= coleccionReservas.iterator();
			while (iteradorReservas.hasNext()) {
				Reserva reservaIt= iteradorReservas.next();
				if(permanencia.equals(reservaIt.getPermanencia())) {
					reservaListaPer.add(new Reserva(reservaIt));
				}
			}
			return reservaListaPer;
		}
	
	//méotodo para consulta de disponibilidad de reservas con los atributos de aula y permanencia
	public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
		
		if (aula == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de un aula nula.");
		} else if (permanencia == null) {
			throw new NullPointerException("ERROR: No se puede consultar la disponibilidad de una permanencia nula.");
		}
		//bandera
		
		boolean disponible= true;
		Iterator<Reserva> iteradorDisp= coleccionReservas.iterator();
		while(iteradorDisp.hasNext()) {
			Reserva reservaDisp= iteradorDisp.next();
			if ((permanencia.equals(reservaDisp.getPermanencia())) && (aula.equals(reservaDisp.getAula()))){
				disponible= true;
			}
		}
		return disponible;
		}
}
