package buscadorMejorCircuito;



import java.util.List;

import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import terminal.Terminal;

public class MenorTiempoRecorrido extends ConceptoBusquedaDestino {

//	@Override
//	public CircuitoMaritimo buscarMejorC(Terminal terminal, Terminal destino) {
//		return terminal.getCircuitos().stream().min((c1, c2)-> 
//		Double.compare(c1.tiempoRecorridoEntre(terminal, destino),  c2.tiempoRecorridoEntre(terminal, destino))).get();
//	}
	
//	@Override
//	public CircuitoMaritimo buscarMejorC(List<Viaje> viajes, Terminal terminal, Terminal destino) {
//		return viajes.stream().min((v1, v2)-> 
//		Double.compare(v1.duracionEntre(terminal, destino),  v2.duracionEntre(terminal, destino))).get().getCircuito();
//	}
	
	@Override
	public double buscar(Viaje v, Terminal origen, Terminal destino) {
		return v.duracionEntre(origen, destino);
	}
}
