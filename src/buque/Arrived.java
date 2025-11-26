package buque;


public class Arrived extends Fase {
		

	public Arrived(Buque buque) {
		super(buque);
	}

	public boolean estaEnFaseArrived() {
		return true;
	}

	@Override
	protected void cambiarFase() {
		buque.setFase(new Working(buque));
	}

	@Override
	protected boolean condicion() {
		return false;
	}

	@Override
	protected void notificarTerminal() {
		
	}



}
