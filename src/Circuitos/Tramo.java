package Circuitos;

import terminal.Terminal;

public class Tramo {
	private Terminal origen;
	private Terminal destino;
	private double duracion; // expresado en MINUTOS
	
	public Tramo(Terminal origen, Terminal destino, double duracion) {
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
	}
	
	public Terminal getOrigen() {
		return origen;
	}
	public Terminal getDestino() {
		return destino;
	}
	
	public double getDuracion() { //En minutos
		return duracion;
	}

	
}
