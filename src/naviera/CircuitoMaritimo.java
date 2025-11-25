package naviera;

import java.util.ArrayList;
import java.util.List;

import circuitos.Tramo;
import terminal.*;

public class CircuitoMaritimo {
	
	private Terminal origen;
	private Terminal destino;
	private List<Tramo> tramos;
	
	public CircuitoMaritimo(Terminal terminalOrigen, Terminal terminalDestino) {
		this.origen = terminalOrigen;
		this.destino = terminalDestino;
		this.tramos = new ArrayList<Tramo>();
	}

	public Terminal getTerminalOrigen() {
		return origen;
	}

	public Terminal getTerminalDestino() {
		return destino;
	}
	
	
	public void agregarTramo(Terminal tOrigen, Terminal tDestino, double duracion) {
		Tramo tramo = new Tramo(tOrigen, tDestino, duracion);
		tramos.add(tramo);
		//A - B    B - B2   B2- C   C - A
	}

	
	
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
		if(this.contiene(origen, destino)) {
			
			double tiempoTotal = 0;
			Terminal terminalActual = origen;
			
			if(origen == destino) {
				return tramos.stream().mapToDouble(t -> t.getDuracion()).sum();
			}
				
		//int tramo = 0;
		//Tramo t = tramos.get(tramo);
			/*
			 * while(terminalActual != destino) { if(t.getOrigen() == terminalActual ) {
			 *   tiempoTotal =tiempoTotal + t.getDuracion(); 
			 *   terminalActual = t.getDestino(); } 
			 *   tramo++;
			 * if(tramo == tramos.size()) { tramo = 0; } t = tramos.get(tramo); }
			 */
			
			for(Tramo tr : tramos) {
				terminalActual = tr.getDestino();
				tiempoTotal = tiempoTotal + tr.getDuracion();
				if(terminalActual == destino) {
					return tiempoTotal;
				}
			}

		}
		return -1;
	}
	
	public int terminalesEntre(Terminal origen, Terminal destino) {
		int count = 0;
		Terminal terminalActual = origen;
		
		for(Tramo tr : tramos) {
			if(terminalActual != destino) {
				count ++;
			} else {
				return count;
			}
			terminalActual = tr.getDestino();
		}
		
		return count;
	}
	
	
	public Tramo tramoConOrigen(Terminal t) {
		for(Tramo t1 : tramos) {
			if(t1.getOrigen().equals(t)) {
				return t1;
			}
		}
		throw new RuntimeException("No existe ningun tramo en el circuito con origen en: " + t.getNombre());
	}

	public boolean contiene(Terminal origen, Terminal destino) {
		return terminalesDelCircuito().contains(origen) && terminalesDelCircuito().contains(destino);
	}
	
	
	public double precioTotalEntre(Terminal origen, Terminal destino) {
		double precioDiaViaje = 3000;
		return this.tiempoRecorridoEntre(origen, destino) * precioDiaViaje;
	}
	
	public List<Tramo> getTramos(){
		return tramos;
	}
	
	public Terminal getOrigen() {
		return origen;
	}

	public int cantidadDeTramos() {
		return tramos.size();
	}
	

}
