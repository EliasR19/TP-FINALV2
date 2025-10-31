package Circuitos;

import java.time.LocalDateTime;

import Terminal.Terminal;

public class Cronograma {
	Terminal origen, destino;
	LocalDateTime salida, llegada;
	
	public Cronograma(Terminal origen, Terminal destino, LocalDateTime salida, LocalDateTime llegada){
		this.destino = destino;
		this.origen = origen;
		this.salida = salida;
		this.llegada = llegada;
		
	}

	public Terminal getOrigen() {
		return origen;
	}

	public Terminal getDestino() {
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
