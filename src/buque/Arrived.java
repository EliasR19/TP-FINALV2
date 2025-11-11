package buque;

import terminal.Terminal;

public class Arrived extends Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		// En esta fase no realiza nada
	}

	public boolean estaEnFaseArrived() {
		return true;
	}

}
