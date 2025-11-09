package buque;

import terminal.Terminal;

public class Outbound extends Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante <= 50000) {
	        buque.setFase(new Inbound());
	        buque.getFase().actualizarPosicion(buque, distanciaRestante, destino);
	    } else {
	    	System.out.println("El buque se encuentra aún muy lejos de la terminal " + buque.getDestinoActual().getNombre());
	    }
	}

	public boolean estaEnFaseOutbound() {
		return true;
	}
}
