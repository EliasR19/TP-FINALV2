package Circuitos;

import java.time.LocalDateTime;

public class Viaje {
	
	private CircuitoMaritimo circuito;
	private TerminalPrueba origen;
	private LocalDateTime fecInicio;
	
	
	public Viaje(LocalDateTime fecInicio, TerminalPrueba origen, CircuitoMaritimo circuito) {
		super();
		this.fecInicio = fecInicio;
		this.origen = origen;
		this.circuito = circuito;
	}



	public void fecArribos() {
		//String a = "";
		TerminalPrueba actual = origen; 
		Tramo tramoAcutal = circuito.tramoConOrigen(origen);
		for(int x = 0; x < circuito.getTramos().size(); x++) {
			
			if(tramoAcutal.getOrigen() == actual) {
				System.out.println(tramoAcutal.getOrigen().getName() + " --> " + tramoAcutal.getDestino().getName() + " | salida" + fecInicio.toString() +" | llegada: " + fecInicio.plusHours((long) tramoAcutal.getRecorrido()));
			}
			tramoAcutal = circuito.tramoConOrigen(tramoAcutal.getDestino());
			actual = tramoAcutal.getOrigen();
		}
		
		
		/*for(Tramo t : circuito.getTramos()) {
			
			System.out.println(t.getOrigen().getName() + " --> " + t.getDestino().getName() + " llegada: " + fecInicio.plusHours((long) t.getRecorrido()));
		}*/
	}
	



	public LocalDateTime getFecInicio() {
		return fecInicio;
	}
}
