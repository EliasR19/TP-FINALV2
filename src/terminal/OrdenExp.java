package terminal;

import java.time.LocalDateTime;

import buque.Buque;
import clientes.Cliente;
import clientes.Shipper;
import container.Container;
import empresasTransportistas.*;

public class OrdenExp extends Orden {

	public OrdenExp(Terminal terminal, Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		super(terminal, shipper, carga, buque, camion, chofer, turno);
	}
}
