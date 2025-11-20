package circuitos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import naviera.CircuitoMaritimo;
import terminal.Terminal;

public class Viaje {
	
	private CircuitoMaritimo circuito;
	private Terminal origen;
	private LocalDateTime fecInicio;
	private List<Cronograma> cronograma;
	
	
	public Viaje(LocalDateTime fecInicio, Terminal origen, CircuitoMaritimo circuito) {
		super();
		this.fecInicio = fecInicio;
		this.origen = origen;
		this.circuito = circuito;
		cronograma = new ArrayList<>();
	}
	
	public void createCronograma() {
		Tramo tramoActual = circuito.tramoConOrigen(origen);
		LocalDateTime salidaTime = fecInicio; 
		
		while (!(tramoActual.getDestino().getNombre().equals(origen.getNombre()))) {
			cronograma.add(new Cronograma(tramoActual.getOrigen(),
		   			  tramoActual.getDestino(),
		   			  salidaTime,
		   			  salidaTime.plusHours((long) tramoActual.getDuracion())));

			salidaTime = salidaTime.plusHours((long) tramoActual.getDuracion());
			tramoActual = circuito.tramoConOrigen(tramoActual.getDestino());
		}
		
		cronograma.add(new Cronograma(tramoActual.getOrigen(),
	   			  tramoActual.getDestino(),
	   			  salidaTime,
	   			  salidaTime.plusHours((long) tramoActual.getDuracion())));
		
	}
	
	public List<Cronograma> getCronograma(){
		return cronograma;
	}

	public LocalDateTime getFecInicio() {
		return fecInicio;
	}
	

	public Terminal getDestinoActual() {
	    return origen; 
	}// Ya llegó a todos los destinos
	
	
	public Terminal getOrigen() {

		return origen; // Ya llegó a todos los destinos
	}
	

	public CircuitoMaritimo getCircutio() {
		return circuito;
	}
	
	public boolean tieneDestino(Terminal terminal) {	
		return circuito.contiene(origen, terminal);

	}
	
	public boolean tieneDestinoYLlegada(LocalDateTime llegada,  Terminal destino) {
		for(Cronograma c : cronograma) {
			if(c.getDestino() == destino && c.getLlegada().equals(llegada)) {
				return true;
			}
		}
		return false;

	}



	public void confirmarLlegada(Terminal destino) {
		for (Cronograma c : cronograma) {
	        if (c.getDestino().getNombre().equals(destino.getNombre())) {
	            c.confirmarLlegada();
	        }
		}
	}

}
