package buque;

import terminal.Terminal;

public class Departing implements Fase {

	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante >= 1000) {
	        buque.setFase(new Outbound());
	        buque.getOrigenActual().mandarMailAShippersDel(buque.getViaje());
	    }
	}

	public boolean esDeparting() {
		return true;
	}

}
