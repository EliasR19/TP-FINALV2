package buque;

import terminal.Terminal;

public class Outbound extends Fase {

	public Outbound(Buque buque) {
		super(buque);
	}
	
	@Override
	protected void cambiarFase() {
		buque.setFase(new Inbound(buque));
	}
	
	@Override
	protected boolean condicion() {
		return buque.distanciaA(terminal) <= 50000;
	}

	public boolean estaEnFaseOutbound() {
		return true;
	}

	@Override
	public void notificarTerminal() {
		terminal.cobrarServicios(buque);
	}

}
