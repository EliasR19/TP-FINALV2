package buque;

public class Outbound implements Fase {

	@Override
	public void actualizarPosicion(Buque buque, double distanciaRestante) {
		if(distanciaRestante == 0) {
	        buque.setFase(new Arrived());
	    } else if(distanciaRestante <= 50) {
	        buque.setFase(new Inbound());
	    } else {
	    	System.out.println("El buque se encuentra aún muy lejos de la terminal " + buque.getDestinoActual().getNombre());
	    }
	}

}
