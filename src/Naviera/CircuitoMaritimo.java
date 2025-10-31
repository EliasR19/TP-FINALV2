package Naviera;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import Circuitos.Tramo;
import Terminal.Terminal;

public class CircuitoMaritimo {
	private List<Tramo> tramos = new ArrayList<Tramo>();
	private Terminal origen;
	private Terminal destino;

	
	public CircuitoMaritimo(Terminal origen, Terminal destino) {
		this.origen = origen;
		this.destino = destino;
	}
	
	
	public void agregarTramo(Terminal tOrigen, Terminal TDestino, double duracion) {
		// FIX LATER que no haya dos tramos con el mismo origen/destino
		tramos.add(new Tramo(tOrigen, TDestino, duracion));
		
		
		//A - B    B - B2   B2- C   C - A
	}
	
	
	
//	public List<Tramo> terminalesDelCircuitoOrd(){

	
	
	//Despues hacer protected
	public List<Terminal> terminalesDelCircuito(){
		//Devuelve todas las terminales del circuito
		List<Terminal> listaTerminales = new ArrayList<Terminal>();
		for(Tramo t : tramos) {
			listaTerminales.add(t.getOrigen());
		}
		return listaTerminales;
		
	}
	
	
	
	
	public double tiempoRecorridoEntre(Terminal origen, Terminal destino) {
		//Por ahora se supone que las dos terminales , a y b, estan en el circuito, más adelante se debe agregar verificación.
		//FIX LATER
		//HACER IMPLEMENTACION MÁS PROLIJA.
		double tiempoTotal = 0;
		Terminal terminalActual = origen;
		if(origen == destino) {
			return tramos.stream().mapToDouble(t -> t.getDuracion()).sum();
		}
			
		int tramo = 0;
		Tramo t = tramos.get(tramo);
		
		while(terminalActual != destino) {
			if(t.getOrigen() == terminalActual ) {
			//	System.out.println("actual: " + terminalActual.getName());
				tiempoTotal = tiempoTotal + t.getDuracion();
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
	
	public Tramo tramoConOrigen(Terminal t) {
		for(Tramo t1 : tramos) {
			if(t1.getOrigen() == t) {
				return t1;
			}
		}
		return null;
	}

	public boolean contiene(Terminal origen, Terminal destino) {
		return terminalesDelCircuito().contains(origen) && terminalesDelCircuito().contains(destino);
	}
	public List<Tramo> getTramos(){
		return tramos;
	}
	
	public Terminal getOrigen() {
		return origen;
	}

}
