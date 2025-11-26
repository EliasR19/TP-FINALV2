package buque;

import java.util.ArrayList;
import java.util.List;

import clientes.ObserverC;

public abstract class ObservadoB {
	private List<ObserverC> observadores;

	public ObservadoB() {
		super();
		this.observadores = new ArrayList<>();
	}
	public List<ObserverC> getObservadores() {
		return observadores;
	}
	
	
	public void addObserver(ObserverC o) {
		observadores.add(o);
	}
	
	public abstract void notificarTerminal();

	
}
