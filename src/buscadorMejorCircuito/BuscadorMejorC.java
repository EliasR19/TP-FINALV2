package buscadorMejorCircuito;

import naviera.CircuitoMaritimo;
import terminal.Terminal;

public interface BuscadorMejorC {

	public abstract CircuitoMaritimo buscarMejorC(Terminal terminal, Terminal destino);

}
