package Circuitos;

import java.time.LocalTime;
import java.time.Period;

public class Tramo {
	private TerminalPrueba origen;
	private TerminalPrueba destino;
	
	private double recorrido; // expresado en HORAS
	
	
	public Tramo(TerminalPrueba origen, TerminalPrueba destino, double recorrido) {
		this.origen = origen;
		this.destino = destino;
		this.recorrido = recorrido;
	}
	
	public TerminalPrueba getOrigen() {
		return origen;
	}
	public TerminalPrueba getDestino() {
		return destino;
	}
	
	public double getRecorrido() { //En horas
		return recorrido;
	}

	
}
