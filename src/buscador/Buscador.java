package buscador;

import java.util.ArrayList;
import java.util.List;

import buscadorMejorCircuito.BuscadorMejorC;
import buscadorMejorCircuito.MenorCantidadTerminales;
import circuitos.*;
import naviera.CircuitoMaritimo;
import terminal.Terminal;
	
public class Buscador {
	private Filtro filtro;
	private List<Viaje> viajes;
	private Terminal terminal;  // Terminal de Origen. Desde donde se busca.
	private BuscadorMejorC mejorCirMaritimo;

	
	public Buscador(Terminal terminal) {
		this.terminal= terminal; 
		viajes = new ArrayList<>();
		//this.agregarViajes();
		filtro = new PuertoDestino(terminal);
		mejorCirMaritimo = new MenorCantidadTerminales();
	}
	
	public void agregarViajes(List<Viaje> viajes) {
		//for(Viaje v : viajes) {
			this.viajes.addAll(viajes);
		//}
	}

	public List<CircuitoMaritimo> buscar(){
		return filtro.buscar(viajes);
	}

	
	public void agregarFiltro(Filtro f) {
		filtro = f;
	}

	public void setMejorBuscadorCirMaritimo(BuscadorMejorC mejorC) {
		mejorCirMaritimo = mejorC;
	}

	public CircuitoMaritimo buscarMejorCirMaritimo(Terminal origen, Terminal destino) {
		return mejorCirMaritimo.buscarMejorC(origen, destino);
	}

	
	

}
