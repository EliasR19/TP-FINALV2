package buque;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import circuitos.Cronograma;
import circuitos.Viaje;
import container.Container;
import terminal.Terminal;
import ubicacionGeografica.GPS;

public class Buque extends ObservadoB{
	
	private Fase fase;
	private Viaje viaje;
	private GPS gps;
	private List<Container> carga;
	private List<Terminal> mailsQueMandoA;
	
	public Buque(Viaje viaje, GPS gps) {
		this.gps = gps;
		this.asignarViaje(viaje);
		mailsQueMandoA = new ArrayList<Terminal>();
		carga = new ArrayList<Container>();
		fase = new Outbound(this);
	}
	
	
	public LocalDateTime getFecSalida() {
		return viaje.getFecInicio();
	}

	public void asignarViaje(Viaje viaje) {
		this.viaje = viaje;
		//gps.setLatitud(viaje.getOrigenActual().getUbicacion().getLatitud());
		//gps.setLongitud(viaje.getOrigenActual().getUbicacion().getLongitud());
		
	}

	public GPS getGPS() {
		return gps;
	}

	public Terminal getDestinoActual() {
		return viaje.getDestinoActual();
	}
	
	public Terminal getOrigenActual() {
		return viaje.getOrigenActual();
	}
	
	public void actualizarPosicion() {
	    fase.cambiarSiSePuede();
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Fase getFase() {
		return fase;
	}
	
	public void setFase(Fase nuevaFase) {
		this.fase = nuevaFase;
		this.notificarTerminal();
	}
	
	@Override
	public void notificarTerminal() {
		fase.notificarTerminal();
	}

	
	public List<Terminal> getMailsQueMandoA() {
		return mailsQueMandoA;
	}
	
	
	public void setMailsQueMandoA(List<Terminal> mailsQueMandoA) {
		this.mailsQueMandoA = mailsQueMandoA;
	}

	public boolean estaEnFaseOutbound() {
		return fase.estaEnFaseOutbound();
	}

	public boolean estaEnFaseInbound() {
		return fase.estaEnFaseInbound();
	}



	public boolean estaEnFaseArrived() {
		return fase.estaEnFaseArrived();
	}



	public void subirCarga(Container container) {
		carga.add(container);
	}

	public boolean tieneCargaDe(Container container) {
		return carga.contains(container);
	}

	public int cargaTotal() {
		return carga.size();
	}

	public void iniciarFaseWorking() {
		if (fase.estaEnFaseArrived()) {
			fase.cambiarFase();
		}
	}


	private void bajarCargas(List<Container> cargas) {
		for (Container c : cargas) {
			this.bajarCarga(c);
		}
		
	}

	public void partidaHabilitada(Terminal terminal) {
		if (fase.estaEnFaseWorking()) {
			fase.cambiarFase();
		}
		//gps.iniciarTimer(terminal);
		
	}


	public boolean estaEnFaseWorking() {
		return fase.estaEnFaseWorking();
	}

	public boolean estaEnFaseDeparting() {
		return fase.estaEnFaseDeparting();
	}

	public void bajarCarga(Container c) {
		carga.remove(c);
	}

	public void recibirCargas(List<Container> cargas) {
		for(Container c : cargas) {
			this.subirCarga(c);
		}
	}
	
	public void cronograma() {
		viaje.createCronograma();
	}
	
	public void llegoAlDestino(Terminal destino) {
		viaje.confirmarLlegada(destino);
	}
	
	public void showCronograma() {
		for(Cronograma c : viaje.getCronograma()) {
			c.getEntero();
		}
	}


	public List<Container> getCarga() {
		return carga;
	}


	public double distanciaA(Terminal t) {
		return gps.distanciaA(t.getUbicacion());
	}

	public void cambiarPosicion(double lat, double lon) {
		gps.setLatitud(lat);
		gps.setLongitud(lon);
		fase.cambiarSiSePuede();
	}


}
