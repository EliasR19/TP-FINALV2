package Circuitos;

import java.time.LocalTime;
import java.time.Period;

import terminal.Terminal;

public class Tramo {
	private Terminal origen;
	private Terminal destino;
	
	private double duracion; // expresado en HORAS
	
	
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
	
	public double getDuracion() { //En horas
		return duracion;
	}

	
}
