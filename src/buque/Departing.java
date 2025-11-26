package buque;

import terminal.Terminal;

public class Departing extends Fase {

	public Departing(Buque buque) {
		super(buque);
	}



	@Override
	protected void cambiarFase() {
		buque.setFase(new Outbound(buque));
	}

	@Override
	protected boolean condicion() {
		return buque.distanciaA(terminal) >= 1000;
	}

	@Override
	public void notificarTerminal() {
		terminal.notificarSalida(buque);
	}
	
	@Override
	public boolean estaEnFaseDeparting() {
		return true;
	}

}
