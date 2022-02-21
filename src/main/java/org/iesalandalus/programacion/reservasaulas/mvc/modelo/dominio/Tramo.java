package org.iesalandalus.programacion.reservasaulas.MVC.modelo.dominio;

public enum Tramo {
	MANANA("Mañana"), TARDE("Tarde");
	
	private String mostrarCadena;
	
	private Tramo(String mostrarCadena) {
		this.mostrarCadena= mostrarCadena;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

}
