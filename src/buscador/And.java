package buscador;

import circuitos.Viaje;
import terminal.Terminal;

public class And extends Operador{

	
	@Override
	protected boolean cumpleCondicion(Viaje v, Filtro f1, Filtro f2) {
		return f1.cumpleCondicion(v) && f2.cumpleCondicion(v);
	}

}
