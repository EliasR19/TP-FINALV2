package buque;

import terminal.Terminal;

public class Departing implements Fase {

	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante >= 1000) {
	        buque.getOrigenActual().mandarMailAShippersDel(buque.getViaje());
	        buque.setFase(new Outbound());
	    }
	}

	public boolean esDeparting() {
		return true;
	}

}
