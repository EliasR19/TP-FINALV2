package terminal;

import java.time.LocalDateTime;

import buque.Buque;
import clientes.Shipper;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;

public class OrdenExp extends Orden {

	public OrdenExp(Terminal terminal, Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		super(terminal, shipper, carga, buque, camion, chofer, turno);
	}
	
	@Override
	public boolean respetaElTurno(LocalDateTime horario) {
		LocalDateTime limiteInferior = this.getTurno().minusHours(3);
		LocalDateTime limiteSuperior = this.getTurno().plusHours(3);
		return horario.isBefore(limiteInferior) && horario.isAfter(limiteSuperior);
	}
	
	
}
