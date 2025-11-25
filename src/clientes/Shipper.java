package clientes;

import java.time.LocalDateTime;

import buque.Buque;
import terminal.OrdenExp;

public class Shipper extends Cliente {
	
	public Shipper(String nombre) {
		super(nombre);
	}
	
	public void exportarCarga(OrdenExp orden, LocalDateTime horario) {
		if(orden.getTerminal().respetaElTurnoExp(orden, horario) && orden.getTerminal().respetaCamionYChofer(orden)) {
			orden.getTerminal().guardarContainer(orden.getCarga());
			orden.getTerminal().descargar(orden.getCamion());
		}
	}

	@Override
	public void notificar(StringBuilder desgloce, Buque buque) {
		System.out.println("El buque " + buque + " sale de la temerminal.");
		System.out.println(desgloce);
	}

	
}
