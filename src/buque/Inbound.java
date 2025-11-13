package buque;

import terminal.Terminal;

public class Inbound extends Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante <= 0) {
	        buque.setFase(new Arrived());
	    } else {
	    	this.notificarTerminal(buque, buque.getOrigenActual());
	    	//System.out.println("El buque se encuentra a 50 kms o menos de la Terminal " + buque.getDestinoActual().getNombre());
	    }
	}

	public boolean estaEnFaseInbound() {
		return true;
	}

	public void notificarTerminal(Buque buque, Terminal destino) {
		buque.notificarTerminal(destino);
	}
}
