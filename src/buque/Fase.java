package buque;

import terminal.Terminal;

public abstract class Fase {

	protected Buque buque;
	protected Terminal terminal; //la terminal a la que tiene que llegar
	
	public Fase(Buque buque) {
		this.buque = buque;
		terminal = buque.getDestinoActual();
	}



	public void cambiarSiSePuede() {
		if(this.condicion()) {
			this.cambiarFase();
		}
	}
	
	
	protected abstract void cambiarFase();

	protected abstract boolean condicion();

	public abstract void notificarTerminal();
	
	public boolean estaEnFaseOutbound() {
		return false;
	}

	public boolean estaEnFaseInbound() {
		return false;
	}

	public boolean estaEnFaseArrived() {
		return false;
	}

	public boolean estaEnFaseWorking() {
		return false;
	}

	public boolean estaEnFaseDeparting() {
		return false;
	}
	
}
