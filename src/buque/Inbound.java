package buque;

import terminal.Terminal;

public class Inbound extends Fase {

	public Inbound(Buque buque) {
		super(buque);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
//		if(distanciaRestante <= 0) {
//	        buque.setFase(new Arrived());
//	    } else {
//	    	buque.avisarSobreInminenteArribo(destino);
//	    	System.out.println("El buque se encuentra a 50 kms o menos de la Terminal " + buque.getDestinoActual().getNombre());
//	    }
//	}

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
	protected void notificarTerminal() {
		//temrinal.notificar();
	}

}
