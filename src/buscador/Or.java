package buscador;

import java.util.List;

import Circuitos.Tramo;
import Circuitos.Viaje;
import terminal.Terminal;

public class Or extends Operador{


	@Override
	protected boolean cumpleCondicion(Viaje v, Terminal terminal, Filtro f1, Filtro f2) {
		return  f1.cumpleCondicion(v, terminal) || f2.cumpleCondicion(v, terminal);
	}


	

}
