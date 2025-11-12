package terminal;

import java.time.LocalDateTime;

import buque.Buque;
import clientes.Shipper;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;

public class OrdenImp extends Orden{

	public OrdenImp(Terminal terminal, Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		super(terminal, shipper, carga, buque, camion, chofer, turno);
	}
	
	@Override
	public boolean respetaElTurno(LocalDateTime horario) {
		LocalDateTime limite24hs = this.getTurno().plusHours(24);
		return horario.isBefore(this.getTurno()) && horario.isAfter(limite24hs);
	}
	
}
