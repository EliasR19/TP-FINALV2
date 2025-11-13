package buque;

import terminal.Terminal;

public class Departing extends Fase {

	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante >= 1000) {
	        buque.setFase(new Outbound());
	        this.notificarTerminal(buque, buque.getOrigenActual());
	    }
	}

	boolean estaEnFaseDeparting() {
		return true;
	}
	
	public void notificarTerminal(Buque buque, Terminal origen) {
		System.out.println(origen.getNombre());
		buque.notificarTerminal(origen);
	}

}
