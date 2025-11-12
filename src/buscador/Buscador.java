package buscador;

import java.util.ArrayList;
import java.util.List;

import circuitos.*;
import terminal.Terminal;
	
public class Buscador {
	private Filtro filtro;
	private Terminal terminal; // Terminal de Origen. Desde donde se busca.
	
	
	public Buscador(Terminal terminal) {
		this.terminal = terminal;
	}
	
	public List<List<Tramo>> buscar(){
		List<List<Tramo>> ts = new ArrayList<>();
			ts.addAll(filtro.buscar(terminal));
		return ts;
	}

	


	public void agregarFiltro(Filtro f) {
		filtro = f;
	}
}
