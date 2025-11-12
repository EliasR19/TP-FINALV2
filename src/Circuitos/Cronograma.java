package Circuitos;

import java.time.LocalDateTime;

import terminal.Terminal;

public class Cronograma {
	Terminal origen, destino;
	LocalDateTime salida, llegada;
	boolean llegoADestino;
	
	public Cronograma(Terminal origen, Terminal destino, LocalDateTime salida, LocalDateTime llegada){
		this.destino = destino;
		this.origen = origen;
		this.salida = salida;
		this.llegada = llegada;
		this.llegoADestino = false;
		
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
		System.out.println(origen.getNombre() + " --> " + destino.getNombre() + " | salida: " + salida +" | llegada: " + llegada);
	}

	public boolean getLlegoADestino() {
		return llegoADestino;
	}
	
	public void confirmarLlegada() {
		this.llegoADestino = true;
	}
	
}
