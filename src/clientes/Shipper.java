package clientes;

import java.time.LocalDateTime;

import terminal.OrdenExp;

public class Shipper extends Cliente {
	
	public Shipper(String nombre) {
		super(nombre);
	}
	
	public void exportarCarga(OrdenExp orden, LocalDateTime horario) {
		if(orden.getTerminal().respetaElTurnoExp(orden, horario) && orden.getTerminal().respetaCamionYChofer(orden)) {
			orden.getCamion().descargar();
			orden.getTerminal().guardarContainer(orden.getCarga());
		}
	}
	
	
}
