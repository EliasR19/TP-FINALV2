package buscadorMejorCircuito;

import naviera.CircuitoMaritimo;
import terminal.Terminal;

public class MenorPrecioTotal implements BuscadorMejorC{

	@Override
	public CircuitoMaritimo buscarMejorC(Terminal terminal, Terminal destino) {
		return terminal.getCircuitos().stream().min((c1, c2)-> 
		Double.compare(c1.precioTotalEntre(terminal, destino),  c2.precioTotalEntre(terminal, destino))).get();
	}

}
