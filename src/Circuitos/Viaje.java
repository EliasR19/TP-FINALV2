package Circuitos;

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



//	public void fecArribos() {
//		//String a = "";
//		TerminalPrueba actual = origen; 
//		Tramo tramoAcutal = circuito.tramoConOrigen(origen);
//		for(int x = 0; x < circuito.getTramos().size(); x++) {
//			
//			if(tramoAcutal.getOrigen() == actual) {
//				cronograma.add(new Cronograma(tramoAcutal.getOrigen(), tramoAcutal.getDestino(), fecInicio, fecInicio.plusHours((long) tramoAcutal.getRecorrido())));
//				System.out.println(tramoAcutal.getOrigen().getName() + " --> " + tramoAcutal.getDestino().getName() + " | salida: " + fecInicio.toString() +" | llegada: " + fecInicio.plusHours((long) tramoAcutal.getRecorrido()));
//			}
//			tramoAcutal = circuito.tramoConOrigen(tramoAcutal.getDestino());
//			actual = tramoAcutal.getOrigen();
//		}
//		
//		
//		/*for(Tramo t : circuito.getTramos()) {
//			
//			System.out.println(t.getOrigen().getName() + " --> " + t.getDestino().getName() + " llegada: " + fecInicio.plusHours((long) t.getRecorrido()));
//		}*/
//	}
	
	public void createCronograma() {
		Tramo tramoActual = circuito.tramoConOrigen(origen);
		LocalDateTime salidaTime = fecInicio; 
		
		while (tramoActual != null) {
			cronograma.add(new Cronograma(tramoActual.getOrigen(),
		   			  tramoActual.getDestino(),
		   			  salidaTime,
		   			  salidaTime.plusHours((long) tramoActual.getDuracion())));

			salidaTime = salidaTime.plusHours((long) tramoActual.getDuracion());
			tramoActual = circuito.tramoConOrigen(tramoActual.getDestino());
		}
		
	}
	
	public List<Cronograma> getCronograma(){
		return cronograma;
	}

	public LocalDateTime getFecInicio() {
		return fecInicio;
	}
	
	public Terminal getDestinoActual() {
	    for (Cronograma c : cronograma) {
	        if (!c.getLlegoADestino()) {
	            return c.getDestino();
	        }
	    }
	    return origen; // Ya llegó a todos los destinos
	}



	public void confirmarLlegada(Terminal destino) {
		 for (Cronograma c : cronograma) {
		        if (c.getDestino().getNombre().equals(destino.getNombre())) {
		            c.confirmarLlegada();
		        }
		 }
	}
}
