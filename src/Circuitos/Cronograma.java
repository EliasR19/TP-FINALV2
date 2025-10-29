package Circuitos;

import java.time.LocalDateTime;

public class Cronograma {
	TerminalPrueba origen, destino;
	LocalDateTime salida, llegada;
	
	public Cronograma(TerminalPrueba origen, TerminalPrueba destino, LocalDateTime salida, LocalDateTime llegada){
		this.destino = destino;
		this.origen = origen;
		this.salida = salida;
		this.llegada = llegada;
		
	}

	public TerminalPrueba getOrigen() {
		return origen;
	}

	public TerminalPrueba getDestino() {
		return destino;
	}

	public LocalDateTime getSalida() {
		return salida;
	}

	public LocalDateTime getLlegada() {
		return llegada;
	}

	public void getEntero() {
		System.out.println(origen.getName() + " --> " + destino.getName() + " | salida: " + salida +" | llegada: " + llegada);
	}
	
	
}
