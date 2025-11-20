package buscador;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import circuitos.*;
import naviera.CircuitoMaritimo;
import terminal.Terminal;

public abstract class Filtro {
	
	public List<CircuitoMaritimo> buscar(List<Viaje> viajes) {
		return viajes.stream().filter(v -> this.cumpleCondicion(v)).map(v-> v.getCircutio()).collect(Collectors.toList());
	}
	
	protected abstract boolean cumpleCondicion(Viaje v);
	
	
	
}
