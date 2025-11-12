package clientes;

import java.time.LocalDateTime;

import terminal.OrdenExp;

public class Shipper extends Cliente{
	
	public Shipper(String nombre) {
		super(nombre);
	}
	
	public void exportarCarga(OrdenExp orden, LocalDateTime horario) {
		if(orden.respetaElTurno(horario) && orden.respetaCamionYChofer(orden.getCamion())) {
			orden.getCamion().descargar();
			orden.getTerminal().guardarContainer(orden.getCarga());
		}
	}
	
	
}
