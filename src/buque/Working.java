package buque;

import terminal.Terminal;

public class Working implements Fase {

	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		buque.getGPS().apagarTimer();
	}

	public boolean estaEnFaseWorking() {
		return true;
	}



}
