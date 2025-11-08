package buque;

import terminal.Terminal;

public class Arrived implements Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean estaEnFaseOutbound() {
		return false;
	}

	@Override
	public boolean estaEnFaseInbound() {
		return false;
	}

	@Override
	public boolean estaEnFaseArrived() {
		return true;
	}

}
