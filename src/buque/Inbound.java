package buque;

import terminal.Terminal;

public class Inbound implements Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		if(distanciaRestante <= 0) {
	        buque.setFase(new Arrived());
	    } else {
	    	buque.avisarSobreInminenteArribo(destino);
	    	System.out.println("El buque se encuentra a 50 kms o menos de la Terminal " + buque.getDestinoActual().getNombre());
	    }
	}

	public boolean estaEnFaseInbound() {
		return true;
	}

}
