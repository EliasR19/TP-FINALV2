package Circuitos;

import java.time.LocalTime;
import java.time.Period;

public class Tramo {
	private TerminalPrueba origen;
	private TerminalPrueba destino;
	
	private double duracion; // expresado en HORAS
	
	
	public Tramo(TerminalPrueba origen, TerminalPrueba destino, double duracion) {
		this.origen = origen;
		this.destino = destino;
		this.duracion = duracion;
	}
	
	public TerminalPrueba getOrigen() {
		return origen;
	}
	public TerminalPrueba getDestino() {
		return destino;
	}
	
	public double getDuracion() { //En horas
		return duracion;
	}

	
}
