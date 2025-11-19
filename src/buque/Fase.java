package buque;

import terminal.Terminal;

public interface Fase {

	abstract void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino);

	default boolean esOutbound() {
		return false;
	}

	default boolean esInbound() {
		return false;
	}

	default boolean esArrived() {
		return false;
	}

	default boolean esWorking() {
		return false;
	}

	default boolean esDeparting() {
		return false;
	}
	
}
