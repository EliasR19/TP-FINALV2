package terminal;

import buque.Buque;
import clientes.Shipper;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;

public abstract class Orden {
	
	private Shipper shipper;
	private Container carga;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	
	public Orden(Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer) {
		
		this.shipper = shipper;
		this.carga = carga;
		this.buque = buque;
		this.camion = camion;
		this.chofer = chofer;
	}

	public Shipper getShipper() {
		return shipper;
	}

	public Container getCarga() {
		return carga;
	}

	public Buque getBuque() {
		return buque;
	}

	public Camion getCamion() {
		return camion;
	}

	public Chofer getChofer() {
		return chofer;
	}
	
	
}
