package clientes;

import java.time.LocalDateTime;

import buque.Buque;
import terminal.OrdenExp;

public class Shipper extends Cliente {
	
	public Shipper(String nombre) {
		super(nombre);
	}
	
	// posible merge a clase terminal
	public void exportarCarga(OrdenExp orden, LocalDateTime horario) {
		if(orden.getTerminal().respetaElTurnoExp(orden, horario) && orden.getTerminal().respetaCamionYChofer(orden)) {
			orden.getTerminal().descargar(orden.getCamion());
			orden.getTerminal().guardarContainer(orden.getCarga());
		}
	}

	@Override
	public void notificar(Buque b) {
			String mail = "Sarpa buque: " + b;
			this.agregarMail(mail);
			System.out.println(mail);
		
	}

	
}
