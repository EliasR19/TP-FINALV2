package buque;

import terminal.Terminal;

public class Inbound implements Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
		buque.avisarSobreInminenteArribo(destino);
    	System.out.println("El buque se encuentra a 50 kms o menos de la Terminal " + buque.getDestinoActual().getNombre());
	}

	@Override
	public boolean estaEnFaseOutbound() {
		return false;
	}

	@Override
	public boolean estaEnFaseInbound() {
		return true;
	}

}
