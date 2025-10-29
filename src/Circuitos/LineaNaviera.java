package Circuitos;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
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
			System.out.println("Buque: " + b + " | FecSalida: " + b.getFecSalida());
			b.cronograma();
			System.out.println("\n");
		}
	}
	
	public void salidaBuque(Buque bA, CircuitoMaritimo circuito,LocalDateTime fecSalida) {
		circuito.terminalesDelCircuito().getFirst().asignarFecSalidaBuqe(bA,fecSalida);
	}
	public double duracionEntre(TerminalPrueba origen, TerminalPrueba destino) {
		//La terminal destino debe estar dentro de algun circuito de esta Naviera. HACER VERIFICACION
		//FIX LATER
		return this.circuitoCon(origen, destino).tiempoRecorridoEntre(origen, destino);
//		LocalTime totalRecorrido = LocalTime.of(0, 0);
//		for(Tramo t : this.circuitoCon(origen, destino).getTramos()){
//			if(t.getOrigen() == origen) {
//				totalRecorrido.plusHours((long) t.getRecorrido());
//			}
//		}
//		return totalRecorrido;
	}
	private CircuitoMaritimo circuitoCon(TerminalPrueba origen, TerminalPrueba destino) {
		for(CircuitoMaritimo c : circuitos) {
			if(c.contiene(origen, destino)) {
				return c;
			}
		}
		return null; //Error , no estan las terminales en este circuito.
	}
	
	
	
	
	
}
