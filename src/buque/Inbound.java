package buque;

import terminal.Terminal;

public class Inbound extends Fase {

	public Inbound(Buque buque) {
		super(buque);
	}



	public boolean estaEnFaseInbound() {
		return true;
	}

	@Override
	protected void cambiarFase() {
		buque.setFase(new Arrived(buque));		
	}

	@Override
	protected boolean condicion() {
		return buque.distanciaA(terminal) == 0;
	}

	@Override
	public void notificarTerminal() {
		terminal.notificarArribo(buque);
	}

}
