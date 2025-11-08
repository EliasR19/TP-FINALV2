package buque;

import terminal.Terminal;

public interface Fase {

	void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino);

	boolean estaEnFaseOutbound();

	boolean estaEnFaseInbound();

}
