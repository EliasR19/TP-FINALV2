package buque;

import terminal.Terminal;

public class Working extends Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		buque.getGPS().apagarTimer();
		buque.realizarDescargaYCarga(destino);
	}

	public boolean estaEnFaseWorking() {
		return true;
	}



}
