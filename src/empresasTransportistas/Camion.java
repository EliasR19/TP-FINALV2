package empresasTransportistas;

import container.Container;

public class Camion {
	
	private Container carga;
	private Chofer chofer;
	
	public Camion() {
	
	}
	
	public Container getCarga() {
		return this.carga;
	}
	
	public void setCarga(Container carga) {
		this.carga = carga;
	}
	
	public Chofer getChofer() {
		return chofer;
	}
	
	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	}
	
	public void descargar() {
		if(carga != null) {
			this.carga = null;
		}
	}
}
