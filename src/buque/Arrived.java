package buque;

import terminal.Terminal;

public class Arrived extends Fase {
		
	boolean iniciarWokign = false;
	
//	@Override
//	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
//		System.out.println("El buque está en espera del inicio del trabajo de descarga y carga");
//	}

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
