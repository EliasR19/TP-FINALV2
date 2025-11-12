package buscador;

import circuitos.Viaje;
import terminal.Terminal;

public abstract class Operador {


	protected abstract boolean cumpleCondicion(Viaje v, Terminal terminal, Filtro f1, Filtro f2);

}
