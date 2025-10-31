package terminal;

import buque.Buque;
import clientes.Shipper;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;

public class OrdenExp extends Orden {

	public OrdenExp(Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer) {
		super(shipper, carga, buque, camion, chofer);
	}
	
	
	
}
