package buscador;

import java.time.LocalDateTime;

import Circuitos.Viaje;
import terminal.Terminal;

public class FechaSalida extends Filtro {
	private LocalDateTime fecSalida;

	public FechaSalida(LocalDateTime fecSalida) {
		this.fecSalida = fecSalida;
	}

	

	@Override
	protected boolean cumpleCondicion(Viaje v, Terminal terminal) {
		return v.getFecInicio().equals(fecSalida);
	}

}
