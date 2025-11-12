package clientes;

import java.time.LocalDateTime;

import terminal.OrdenImp;

public class Consignee extends Cliente {

	public Consignee(String nombre) {
		super(nombre);
	}
	
	public void importarCarga(OrdenImp orden, LocalDateTime horario) {
		if(orden.respetaElTurno(horario) && orden.respetaCamionYChofer(orden.getCamion())) {
			orden.getTerminal().retirarCarga(orden.getCarga());
		}
	}
	
}
