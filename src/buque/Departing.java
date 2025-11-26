package buque;

import terminal.Terminal;

public class Departing extends Fase {

	public Departing(Buque buque) {
		super(buque);
	}

//	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
//		if(distanciaRestante >= 1000) {
//	        buque.setFase(new Outbound());
//	        buque.getOrigenActual().mandarMailAShippersDel(buque.getViaje());
//	    }
//	}
//
//	public boolean estaEnFaseDeparting() {
//		return true;
//	}

	@Override
	protected void cambiarFase() {
		buque.setFase(new Outbound(buque));
	}

	@Override
	protected boolean condicion() {
		return buque.distanciaA(terminal) >= 1000;
	}

	@Override
	protected void notificarTerminal() {
		//terminal.notificar();
	}

}
