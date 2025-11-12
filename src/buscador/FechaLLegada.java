package buscador;

import java.time.LocalDateTime;

import Circuitos.Viaje;
import terminal.Terminal;

public class FechaLLegada extends Filtro{
	private LocalDateTime fecLLegada;
	private Terminal destino;
	
	

	public FechaLLegada(LocalDateTime fecLLegada, Terminal destino) {
		this.fecLLegada = fecLLegada;
		this.destino = destino;
	}



	@Override
	protected boolean cumpleCondicion(Viaje v, Terminal terminal) {
		return v.tieneDestinoYLlegada(fecLLegada, destino);
	}


}
