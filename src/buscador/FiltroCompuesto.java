package buscador;

import java.util.ArrayList;
import java.util.List;

import Circuitos.Tramo;
import Circuitos.Viaje;
import terminal.*;

public class FiltroCompuesto extends Filtro{
	
	private Operador op;
	private Filtro f1;
	private Filtro f2;
	
	public FiltroCompuesto(Operador op, Filtro f1, Filtro f2) {
		this.op = op;
		this.f1 = f1;
		this.f2 = f2;
	}
	


	@Override
	public List<List<Tramo>> buscar(Terminal terminal) {
		List<List<Tramo>> result = new ArrayList<>();
		
		for(Viaje v : terminal.getViajes()) {
			if(op.cumpleCondicion(v, terminal, f1, f2)) {
				
				result.add(v.getCircutio().getTramos());
			}
		}
		return result;
	}


	
	@Override
	public boolean cumpleCondicion(Viaje v, Terminal terminal) {
		return op.cumpleCondicion(v, terminal, f1, f2);

	}

	
	
	
}
