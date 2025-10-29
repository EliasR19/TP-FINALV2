package Circuitos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Viaje {
	
	private CircuitoMaritimo circuito;
	private TerminalPrueba origen;
	private LocalDateTime fecInicio;
	private List<Cronograma> cronograma;
	
	
	public Viaje(LocalDateTime fecInicio, TerminalPrueba origen, CircuitoMaritimo circuito) {
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
		TerminalPrueba actual = origen; 
		Tramo tramoAcutal = circuito.tramoConOrigen(origen);
		LocalDateTime salidaTime = fecInicio; 
		for(int x = 0; x < circuito.getTramos().size(); x++) {
			
			if(tramoAcutal.getOrigen() == actual) {
				cronograma.add(new Cronograma(tramoAcutal.getOrigen(), tramoAcutal.getDestino(), salidaTime, salidaTime.plusHours((long) tramoAcutal.getRecorrido())));
			}
			salidaTime = salidaTime.plusHours((long) tramoAcutal.getRecorrido());
			tramoAcutal = circuito.tramoConOrigen(tramoAcutal.getDestino());
			actual = tramoAcutal.getOrigen();
		}
		
	}
	public List<Cronograma> getCronograma(){
		return cronograma;
	}

	



	public LocalDateTime getFecInicio() {
		return fecInicio;
	}
}
