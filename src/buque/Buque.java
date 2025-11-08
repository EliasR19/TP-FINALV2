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
		this.gps = new GPS(0, 0, this);
		
	}
	


	public GPS getGPS() {
		return gps;
	}

	public Terminal getDestinoActual() {
		return viaje.getDestinoActual();
	}
	
	public void actualizarPosicion(double distanciaRestante) {
	    fase.actualizarPosicion(this, distanciaRestante);
	}


	public void setFase(Fase nuevaFase) {
		this.fase = nuevaFase;
	}

	public Viaje getViaje() {
		return viaje;
	}
}
