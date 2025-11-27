package buscador;

import java.util.ArrayList;
import java.util.List;

import buscadorMejorCircuito.ConceptoBusquedaDestino;
import buscadorMejorCircuito.MenorCantidadTerminales;
import circuitos.*;
import naviera.CircuitoMaritimo;
import terminal.Terminal;
	
public class Buscador  {
	private Filtro filtro;
	private ConceptoBusquedaDestino mejorCirMaritimo;
	//private List<Viaje> viajes;
	//private Terminal terminal;  // Terminal de Origen. Desde donde se busca.

	
	public Buscador(Terminal terminal) {
		//this.terminal= terminal; 
		//viajes = new ArrayList<>();
		//this.agregarViajes();
		filtro = new PuertoDestino(terminal);
		mejorCirMaritimo = new MenorCantidadTerminales();
	}
	
	public void agregarViajes(List<Viaje> viajes) {
		//Cuando se agrega una linea naviera a la temrinal, se agregan los viajes de esa naviera.
			//this.viajes.addAll(viajes);

	}

	public List<CircuitoMaritimo> buscar(List<Viaje> viajes){
		return filtro.buscar(viajes);
	}

	
	public void agregarFiltro(Filtro f) {
		filtro = f;
	}

	public void setMejorBuscadorCirMaritimo(ConceptoBusquedaDestino mejorC) {
		mejorCirMaritimo = mejorC;
	}

	public CircuitoMaritimo buscarMejorCirMaritimo(List<Viaje> viajes,Terminal origen,Terminal destino) {
		return mejorCirMaritimo.buscarMejorC(viajes, origen, destino);
	}



	
	

}
