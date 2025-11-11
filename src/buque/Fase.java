package buque;

import terminal.Terminal;

public abstract class Fase {

	abstract void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino);

	boolean estaEnFaseOutbound() {
		return false;
	}

	boolean estaEnFaseInbound() {
		return false;
	}

	boolean estaEnFaseArrived() {
		return false;
	}

	boolean estaEnFaseWorking() {
		return false;
	}

	boolean estaEnFaseDeparting() {
		return false;
	}
	
}
