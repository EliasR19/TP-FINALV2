package buscador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Circuitos.Tramo;
import Circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;

public class PuertoDestino extends Filtro{
	private Terminal destino;
	
	public PuertoDestino(Terminal destino) {
		this.destino = destino;
	}
	
	
	/*
	 * @Override public List<List<Tramo>> buscar(Terminal terminal) { return
	 * terminal.getViajes().stream().filter(v -> this.cumpleCondicion(v, terminal)).
	 * map(v -> v.getCircutio().getTramos()).collect(Collectors.toList());
	 * 
	 * }
	 */




	@Override
	public boolean cumpleCondicion(Viaje v, Terminal terminal) {
		return v.tieneDestino(destino);
	}


}
