package buque;

import terminal.Terminal;

public class Working extends Fase {


	
	public Working(Buque buque) {
		super(buque);
	}

	public boolean estaEnFaseWorking() {
		return true;
	}

	@Override
	protected void cambiarFase() {
		buque.setFase(new Departing(buque));
	}

	@Override
	protected boolean condicion() {
		return false;
	}



	@Override
	public void notificarTerminal() {
		
	}
	



}
