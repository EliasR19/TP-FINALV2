package buscadorMejorCircuito;

import naviera.CircuitoMaritimo;
import terminal.Terminal;

public class MenorCantidadTerminales implements BuscadorMejorC {

	@Override
	public CircuitoMaritimo buscarMejorC(Terminal terminal, Terminal destino) {
		return terminal.getCircuitos().stream().min((c1, c2)-> 
		Double.compare(c1.terminalesEntre(terminal, destino),  c2.terminalesEntre(terminal, destino))).get();
	}

}
