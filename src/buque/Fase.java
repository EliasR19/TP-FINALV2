package buque;

import terminal.Terminal;

public interface Fase {

	abstract void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino);

	default boolean estaEnFaseOutbound() {
		return false;
	}

	default boolean estaEnFaseInbound() {
		return false;
	}

	default boolean estaEnFaseArrived() {
		return false;
	}

	default boolean estaEnFaseWorking() {
		return false;
	}

	default boolean estaEnFaseDeparting() {
		return false;
	}
	
}
