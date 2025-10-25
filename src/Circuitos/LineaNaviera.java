package Circuitos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LineaNaviera {
	private List<Buque> buques = new ArrayList<Buque>();
	private List<CircuitoMaritimo> circuitos = new ArrayList<CircuitoMaritimo>();
	
	
	public void agregarBuque(Buque b) {
		buques.add(b);
	}
	public void agregarCircuito(CircuitoMaritimo c) {
		circuitos.add(c);
	}
	
	
	public void establecerSalida(Buque b, LocalDateTime fecSalida) {
		b.setFecSalida(fecSalida);
	}
	
	
	public void recorridos() {
		for(Buque b : buques) {
			System.out.println("Buque: " + b);
			b.cronograma();
			System.out.println("\n");
		}
	}
	
	
}
