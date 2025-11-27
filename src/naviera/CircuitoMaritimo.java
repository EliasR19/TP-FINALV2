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


	public Terminal getOrigen() {
		return origen;
	}
	
	public Terminal getDestino() {
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
	    double tiempoTotal = 0.0;

	    if (this.contiene(origen, destino)) {
	        int i = 0;

	        if (origen.equals(destino)) {
	            while (i < tramos.size()) {
	                tiempoTotal += tramos.get(i).getDuracion();
	                i++;
	            }
	            
	        } else {
	            while (!tramos.get(i).getOrigen().equals(origen)) {
	                i++;
	            }

	            while (!tramos.get(i).getDestino().equals(destino)) {
	                tiempoTotal += tramos.get(i).getDuracion();
	                i++;
	            }
	            
	            tiempoTotal += tramos.get(i).getDuracion();
	        }
	    }

	    return tiempoTotal;
	}
	
	public int terminalesEntre(Terminal origen, Terminal destino) {
	    int count = 0;
	    int i = 0;
	    
	    while (!tramos.get(i).getOrigen().equals(origen)) {
	    	i++;
	    }
	    
	    while (!tramos.get(i).getDestino().equals(destino)) {
	    	count++;
	        i++;
	    }
	    count++;
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
	

	
	

}
