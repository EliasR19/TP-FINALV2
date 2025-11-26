package buque;

import terminal.Terminal;

public class Outbound extends Fase {

	public Outbound(Buque buque) {
		super(buque);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public void actualizarPosicion(Buque buque, double distanciaRestante, Terminal destino) {
//		if(distanciaRestante <= 50000) {
//	        buque.setFase(new Inbound());
//	        buque.getFase().actualizarPosicion(buque, distanciaRestante, destino);
//	    } else {
//	    	System.out.println("El buque se encuentra aún muy lejos de la terminal " + buque.getDestinoActual().getNombre());
//	    }
//	}
	
	
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
	protected void notificarTerminal() {
		
	}

}
