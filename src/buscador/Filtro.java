package buscador;

import java.util.List;
import java.util.stream.Collectors;

import circuitos.*;
import terminal.Terminal;

public abstract class Filtro {
	
	public List<List<Tramo>> buscar(Terminal terminal) {
		return terminal.getViajes().stream().filter(v -> this.cumpleCondicion(v, terminal)).
				map(v -> v.getCircutio().getTramos()).collect(Collectors.toList());
	}
	
	protected abstract boolean cumpleCondicion(Viaje v, Terminal terminal);
}
