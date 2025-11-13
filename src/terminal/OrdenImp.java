package terminal;

import java.time.LocalDateTime;
import java.util.List;

import buque.Buque;
import clientes.Consignee;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import servicios.Servicio;

public class OrdenImp extends Orden{

	public OrdenImp(Terminal terminal, Consignee consignee, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno, List<Servicio> servicios) {
		super(terminal, consignee, carga, buque, camion, chofer, turno, servicios);
	}
}
