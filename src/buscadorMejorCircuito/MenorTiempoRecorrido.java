package buscadorMejorCircuito;



import naviera.CircuitoMaritimo;
import terminal.Terminal;

public class MenorTiempoRecorrido implements BuscadorMejorC {

	@Override
	public CircuitoMaritimo buscarMejorC(Terminal terminal, Terminal destino) {
		return terminal.getCircuitos().stream().min((c1, c2)-> 
		Double.compare(c1.tiempoRecorridoEntre(terminal, destino),  c2.tiempoRecorridoEntre(terminal, destino))).get();
	}

}
