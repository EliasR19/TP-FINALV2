package buscador;


import java.util.List;
import java.util.stream.Collectors;


import circuitos.Viaje;
import naviera.CircuitoMaritimo;


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
	public List<CircuitoMaritimo> buscar(List<Viaje> viajes) {
		return viajes.stream().filter(v -> this.cumpleCondicion(v)).map(v -> v.getCircutio()).collect(Collectors.toList());
	}



	@Override
	protected boolean cumpleCondicion(Viaje v) {
		return op.cumpleCondicion(v, f1, f2);
	}

	
	
	
}
