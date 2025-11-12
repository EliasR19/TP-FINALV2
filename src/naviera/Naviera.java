package naviera;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Circuitos.Viaje;
import terminal.Terminal;
import buque.Buque;

public class Naviera {
	
	private List<Buque> buques;
	private List<CircuitoMaritimo> circuitos;
	
	public Naviera() {
		this.buques = new ArrayList<Buque>();
		this.circuitos = new ArrayList<CircuitoMaritimo>();
	}
	
	public List<Buque> getBuques(){
		return buques;
	}
	
	public List<CircuitoMaritimo> getCircuitosMaritimos(){
		return circuitos;
	}



	public boolean tieneBuque(Buque buque) {
		return buques.contains(buque);
	}

	public int cantidadDeBuques() {
		return buques.size();
	}

	public void agregarCircuitoMaritimo(CircuitoMaritimo circuitoMaritimo) {
		circuitos.add(circuitoMaritimo);
		
	}

	public boolean tieneCircuito(CircuitoMaritimo circuitoMaritimo) {
		return circuitos.contains(circuitoMaritimo);
	}

	public int cantidadDeCircuitos() {
		return circuitos.size();
	}
	public void agregarBuque(Buque b) {
		buques.add(b);
	}
	

	
	public void establecerSalida(Buque b, LocalDateTime fecSalida) {
		b.setFecSalida(fecSalida);
	}
	
	
	
	public void salidaBuque(Buque bA, CircuitoMaritimo circuito,LocalDateTime fecSalida) {
		circuito.terminalesDelCircuito().getFirst().asignarFecSalidaBuqe(bA,fecSalida);
	}
	public void asignarViaje(Buque b,CircuitoMaritimo c) {
		//Asigna un viaje a un buque
		b.asignarViaje(new Viaje(b.getFecSalida(), c.getOrigen(), c));
		b.cronograma();
	}
	
	
	public double duracionEntre(Terminal origen, Terminal destino) {
		
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
	
	private CircuitoMaritimo circuitoCon(Terminal origen, Terminal destino) {
		//Devuelve un Circuito que contenga las dos terminales dadas
		for(CircuitoMaritimo c : circuitos) {
			if(c.contiene(origen, destino)) {
				return c;
			}
		}
		return null; //Error , no estan las terminales en este circuito.
	}
	
	
	
	public void recorridos() {
		for(Buque b : buques) {
			System.out.println("Buque: " + b + " | FecSalida: " + b.getFecSalida());
			b.cronograma();
			System.out.println("\n");
		}
	}

	public void armarCronograma() {
		for(Buque b : buques) {
			b.cronograma();
		}
	}

	public void showCronogramaBuque() {
		buques.stream().forEach(b -> b.cronograma());
	}
	
}
