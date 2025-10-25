package Circuitos;

import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class CircuitoMaritimo {
	private List<Tramo> tramos = new ArrayList<Tramo>();
	
	public void agregarTramo(Tramo t) {
		tramos.add(t);
	}
	
	//Despues hacer privado
	public List<TerminalPrueba> terminalesDelCircuito(){
		List<TerminalPrueba> listaTerminales = new ArrayList<TerminalPrueba>();
		for(Tramo t : tramos) {
			listaTerminales.add(t.getOrigen());
		}
		return listaTerminales;
		
	}
	
	
	public List<Tramo> getTramos(){
		return tramos;
	}
	
	
	public double tiempoRecorridoEntre(TerminalPrueba a, TerminalPrueba b) {
		//Por ahora se supone que las dos terminales , a y b, estan en el circuito, más adelante se debe agregar verificación.
		//FIX LATER
		//HACER IMPLEMENTACION MÁS PROLIJA.
		double tiempoTotal = 0;
		TerminalPrueba terminalActual = a;
		if(a == b) {
			return tramos.stream().mapToDouble(t -> t.getRecorrido()).sum();
		}
			
		int tramo = 0;
		Tramo t = tramos.get(tramo);
		
		while(terminalActual != b) {
			if(terminalActual != b && t.getOrigen() == terminalActual ) {
				System.out.println("actual: " + terminalActual.getName());
				tiempoTotal = tiempoTotal + t.getRecorrido();
				terminalActual = t.getDestino();
			}
			tramo++;
			if(tramo == tramos.size()) {
				tramo = 0;
			}
			 t = tramos.get(tramo);
		}
		return tiempoTotal;
	}
	
	public Tramo tramoConOrigen(TerminalPrueba t) {
		for(Tramo t1 : tramos) {
			if(t1.getOrigen() == t) {
				return t1;
			}
		}
		return null;
	}
}
