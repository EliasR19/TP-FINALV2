package buque;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import circuitos.Cronograma;
import circuitos.Viaje;
import container.Container;
import terminal.Terminal;
import ubicacionGeografica.GPS;

public class Buque {
	
	private Fase fase;
	private Viaje viaje;
	private GPS gps;
	private List<Container> carga;
	private List<Terminal> mailsQueMandoA;
	
	public Buque(Viaje viaje) {
		fase = new Outbound();
		this.viaje = viaje;
		gps = new GPS(0, 0, this);
		this.asignarViaje(viaje);
		mailsQueMandoA = new ArrayList<Terminal>();
	}
	
	public void setFecSalida(LocalDateTime fecSalida) {
		this.fecSalida = fecSalida;
	}
	
	public LocalDateTime getFecSalida() {
		return fecSalida;
	}

	public void asignarViaje(Viaje viaje) {
		this.viaje = viaje;
		gps.setLatitud(viaje.getOrigen().getUbicacion().getLatitud());
		gps.setLongitud(viaje.getOrigen().getUbicacion().getLongitud());
		
	}

	public GPS getGPS() {
		return gps;
	}

	public Terminal getDestinoActual() {
		return viaje.getDestinoActual();
	}
	
	public Terminal getOrigenActual() {
		return viaje.getOrigen();
	}
	
	public void actualizarPosicion(double distanciaRestante, Terminal destino) {
	    fase.actualizarPosicion(this, distanciaRestante, destino);
	}


	public void setFase(Fase nuevaFase) {
		this.fase = nuevaFase;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public Fase getFase() {
		return fase;
	}

	public boolean estaEnFaseOutbound() {
		return fase.estaEnFaseOutbound();
	}

	public boolean estaEnFaseInbound() {
		return fase.estaEnFaseInbound();
	}

	public void avisarSobreInminenteArribo(Terminal destino) {
		if(!mailsQueMandoA.contains(destino)) {
			destino.mandarMailConsignees(viaje);
			mailsQueMandoA.add(destino);
		}
	}

	public boolean estaEnFaseArrived() {
		return fase.estaEnFaseArrived();
	}

	public void iniciarViaje() {
		if (LocalDateTime.now().isEqual(fecSalida)) {
			System.out.println("Buque iniciando viaje hacia la Terminal " + this.getDestinoActual().getNombre());
			mailsQueMandoA.clear();
			getGPS().iniciarTimer(viaje.getDestinoActual());
		}else if (LocalDateTime.now().isBefore(fecSalida)){
			System.out.println("Aún falta para iniciar el viaje");
		}else if (LocalDateTime.now().isAfter(fecSalida)){
			System.out.println("Se deberá arreglar un nuevo cronograma por atraso antes de salir");
		}
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
			this.setFase(new Working());
		}
	}

	public void realizarDescargaYCarga(Terminal destino) { // Como no se contempla el proceso de carga y descarga 
														   // dejamos que se descargan todos los del buque y se
														   // cargan todos de la terminal
		List<Container> cargaParaLaTerminal = new ArrayList<>(carga);
		
		this.bajarCargas(cargaParaLaTerminal);
		destino.recibirCarga(cargaParaLaTerminal, this);
		
	}

	private void bajarCargas(List<Container> cargas) {
		for (Container c : cargas) {
			this.bajarCarga(c);
		}
		
	}

	public void partidaHabilitada(Terminal terminal) {
		this.setFase(new Departing());
		gps.iniciarTimer(terminal);
		
	}

	public void asignarDatosParaElViaje(LocalDateTime fechaSalida, Terminal origen) {
		this.setFecSalida(fechaSalida);
		gps.setLatitud(origen.getUbicacion().getLatitud());
		gps.setLongitud(origen.getUbicacion().getLongitud());
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

}
