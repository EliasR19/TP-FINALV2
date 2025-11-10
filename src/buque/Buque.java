package buque;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Circuitos.Cronograma;
import Circuitos.Viaje;
import container.Container;

public class Buque {
	
	private Viaje viaje;
	private LocalDateTime fecSalida;
	
	//Cambiar en el merge
	private List<Container> carga;
	
	public Buque() {
		carga = new ArrayList<Container>();
	}
	
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
	
	public Viaje getViaje() {
		return viaje;
	}
}
