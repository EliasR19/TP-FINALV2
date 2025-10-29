package Circuitos;

import java.time.LocalDateTime;

public class Buque {
	private Viaje viaje;
	private LocalDateTime fecSalida;
	
	public void setFecSalida(LocalDateTime fecSalida) {
		this.fecSalida = fecSalida;
	}
	
	public LocalDateTime getFecSalida() {
		return fecSalida;
	}

	public void asignarViaje(Viaje viaje) {
		this.viaje = viaje;
		
	}
	
	public void cronograma() {
		viaje.createCronograma();
		for(Cronograma c : viaje.getCronograma()) {
			c.getEntero();
		}
	}
	
}
