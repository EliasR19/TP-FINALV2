package buscador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import Circuitos.*;
import terminal.Terminal;
	
public class Buscador {
	private List<Filtro> filtros;
	private Terminal terminal; // Terminal de Origen. Desde donde se busca.
	
	
	public Buscador(Terminal terminal) {
		this.filtros = new ArrayList<>();
		this.terminal = terminal;
	}
	
	public List<List<Tramo>> buscar(){
		List<List<Tramo>> ts = new ArrayList<>();
		for(Filtro f: filtros) {
			ts.addAll(f.buscar(terminal));
		}
		return ts;
	}

	


	public void agregarFiltro(Filtro f) {
		filtros.add(f);
	}
}
