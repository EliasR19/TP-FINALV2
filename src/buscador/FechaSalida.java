package buscador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Circuitos.Tramo;
import Circuitos.Viaje;
import terminal.Terminal;

public class FechaSalida extends Filtro {
	private LocalDateTime fecSalida;

	public FechaSalida(LocalDateTime fecSalida) {
		this.fecSalida = fecSalida;
	}

	/*
	 * @Override public List<List<Tramo>> buscar(Terminal terminal) { if
	 * (this.cumpleCondicion(terminal)) { return
	 * terminal.getViajes().stream().filter(v ->
	 * v.getFecInicio().equals(fecSalida)).map(v ->
	 * v.getCircutio().getTramos()).collect(Collectors.toList()); } for(Viaje v :
	 * terminal.getViajes()) { if ( cumpleCondicion(v, terminal)) { } } return
	 * terminal.getViajes().stream().filter(v -> this.cumpleCondicion(v, terminal)).
	 * map(v -> v.getCircutio().getTramos()).collect(Collectors.toList());
	 * 
	 * }
	 */

	@Override
	public boolean cumpleCondicion(Viaje v, Terminal terminal) {
		return v.getFecInicio().equals(fecSalida);
	}

}
