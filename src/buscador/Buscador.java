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
		this.terminal= terminal; 
		viajes = new ArrayList<>();
		this.agregarViajes();
	}
	
	private void agregarViajes() {
		for(Viaje v : terminal.getViajes()) {
			viajes.add(v);
		}
	}

	public List<CircuitoMaritimo> buscar(){
		return filtro.buscar(viajes);
	}

	
	public void agregarFiltro(Filtro f) {
		filtro = f;
	}
	
	public List<Viaje> getViajes(){
		return viajes;
	}
}
