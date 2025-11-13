package buque;

import terminal.Terminal;

public class Departing extends Fase {

	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante >= 1000) {
	        buque.setFase(new Outbound());
	        buque.getOrigenActual().mandarMailAShippersDel(buque.getViaje());
	    }
	}

	boolean estaEnFaseDeparting() {
		return true;
	}

}
