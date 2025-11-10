package buscador;

import java.util.List;

import Circuitos.Tramo;
import Circuitos.Viaje;
import terminal.Terminal;

public abstract class Operador {


	protected abstract boolean cumpleCondicion(Viaje v, Terminal terminal, Filtro f1, Filtro f2);

}
