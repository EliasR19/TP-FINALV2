package naviera;

import java.util.ArrayList;
import java.util.List;

import buque.Buque;

public class Naviera {
	
	private List<Buque> buques;
	private List<CircuitoMaritimo> circuitosMaritimos;
	
	public Naviera() {
		this.buques = new ArrayList<Buque>();
		this.circuitosMaritimos = new ArrayList<CircuitoMaritimo>();
	}
	
	public List<Buque> getBuques(){
		return buques;
	}
	
	public List<CircuitoMaritimo> getcircuitosMaritimos(){
		return circuitosMaritimos;
	}

	public void agregarBuque(Buque buque) {
		buques.add(buque);
	}

	public boolean tieneBuque(Buque buque) {
		return buques.contains(buque);
	}

	public int cantidadDeBuques() {
		return buques.size();
	}

	public void agregarCircuitoMaritimo(CircuitoMaritimo circuitoMaritimo) {
		circuitosMaritimos.add(circuitoMaritimo);
		
	}

	public boolean tieneCircuito(CircuitoMaritimo circuitoMaritimo) {
		return circuitosMaritimos.contains(circuitoMaritimo);
	}

	public int cantidadDeCircuitos() {
		return circuitosMaritimos.size();
	}

}
