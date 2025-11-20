package buscador;

import java.util.ArrayList;
import java.util.List;

import circuitos.*;
import naviera.CircuitoMaritimo;
import terminal.Terminal;
	
public class Buscador {
	private Filtro filtro;
	private List<Viaje> viajes;
	private Terminal terminal;  // Terminal de Origen. Desde donde se busca.

	
	public Buscador(Terminal terminal) {
		viajes = terminal.getViajes();
		this.terminal= terminal; 
	}
	
	public List<CircuitoMaritimo> buscar(){
		return filtro.buscar(viajes);
	}

	


	public void agregarFiltro(Filtro f) {
		filtro = f;
	}
}
