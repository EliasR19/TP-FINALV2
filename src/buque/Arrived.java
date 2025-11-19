package buque;

import terminal.Terminal;

public class Arrived implements Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		System.out.println("El buque está en espera del inicio del trabajo de descarga y carga");
	}

	public boolean estaEnFaseArrived() {
		return true;
	}

}
