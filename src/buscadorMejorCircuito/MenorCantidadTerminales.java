package buscadorMejorCircuito;

import java.util.List;

import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import terminal.Terminal;

public class MenorCantidadTerminales extends ConceptoBusquedaDestino {

//	@Override
//	public CircuitoMaritimo buscarMejorC(Terminal terminal, Terminal destino) {
//		return terminal.getCircuitos().stream().min((c1, c2)-> 
//		Double.compare(c1.terminalesEntre(terminal, destino),  c2.terminalesEntre(terminal, destino))).get();
//	}

//	@Override
//	public CircuitoMaritimo buscarMejorC(List<Viaje> viajes, Terminal terminal, Terminal destino) {
//		return viajes.stream().min((v1, v2)-> 
//		Double.compare(v1.terminalesEntre(terminal, destino),  v2.terminalesEntre(terminal, destino))).get().getCircuito();
//	}

	@Override
	public double buscar(Viaje v, Terminal origen, Terminal destino) {
		return v.terminalesEntre(origen, destino);
	}

}
