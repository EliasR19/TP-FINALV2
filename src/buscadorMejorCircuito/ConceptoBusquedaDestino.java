package buscadorMejorCircuito;

import java.util.List;

import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import terminal.Terminal;

public abstract class ConceptoBusquedaDestino {

	//public CircuitoMaritimo buscarMejorC(List<Viaje> viajes, Terminal terminal, Terminal destino);
	
	public CircuitoMaritimo buscarMejorC(List<Viaje> viajes, Terminal terminal, Terminal destino) {
		return viajes.stream().min((v1, v2)-> 
		Double.compare(this.buscar(v1, terminal,destino),  this.buscar(v2, terminal,destino))).get().getCircuito();
	}
	
	public abstract double buscar(Viaje v, Terminal origen, Terminal destino);
}
