package buque;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Circuitos.Cronograma;
import Circuitos.Viaje;
import container.Container;
import terminal.Terminal;
import ubicacionGeografica.GPS;

public class Buque {
	
	private Fase fase;
	private Viaje viaje;
	private LocalDateTime fecSalida;
	private GPS gps;
	
	//Cambiar en el merge
	private List<Container> carga;
	
	public Buque() {
		carga = new ArrayList<Container>();
		gps = new GPS(0, 0, this);
		fase = new Outbound();
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

	public GPS getGPS() {
		return gps;
	}

	public Terminal getDestinoActual() {
		return viaje.getDestinoActual(LocalDateTime.now());
	}
	
	public void actualizarPosicion(double latitud, double longitud, double distanciaRestante) {
	    fase.actualizarPosicion(this, latitud, longitud, distanciaRestante);
	}


	public void setFase(Fase nuevaFase) {
		this.fase = nuevaFase;
	}
}
