package buque;

import terminal.Terminal;

public class Outbound implements Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if((distanciaRestante - 740) <= 50000) { // El buque solo se puede mover a 740 metros, por ende, si
												 // a la distancia actual le quitamos estos 740m(referentes a
												 // a la próxima vez que se mueva) y nos da 50km o menos, quiere
												 // decir que la próxima vez ya tendría que realizar las tareas
											     // de la fase Inbound, por eso ya en esta etapa cambiaría de
												 // fase para estar preparada para el próximo minuto
			buque.setFase(new Inbound());
			System.out.println("El buque se encuentra aún muy lejos de la terminal " + buque.getDestinoActual().getNombre());
			// E informa que aún se encuentra lejos del destino
		} else {
	    	System.out.println("El buque se encuentra aún muy lejos de la terminal " + buque.getDestinoActual().getNombre());
	    }
	}

	public boolean esOutbound() {
		return true;
	}
}
