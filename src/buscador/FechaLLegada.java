package buscador;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import Circuitos.Tramo;
import Circuitos.Viaje;
import terminal.Terminal;

public class FechaLLegada extends Filtro{
	private LocalDateTime fecLLegada;
	private Terminal destino;
	
	

	public FechaLLegada(LocalDateTime fecLLegada, Terminal destino) {
		this.fecLLegada = fecLLegada;
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
		return v.tieneDestinoYLlegada(fecLLegada, destino);
	}


}
