package clientes;

import java.time.LocalDateTime;

import buque.Buque;
import terminal.OrdenImp;

public class Consignee extends Cliente {

	public Consignee(String nombre) {
		super(nombre);
	}
	
	public void importarCarga(OrdenImp orden, LocalDateTime horario) {
		if(orden.getTerminal().respetaElTurnoImp(orden, horario) && orden.getTerminal().respetaCamionYChofer(orden)) {
			orden.getTerminal().llevarCarga(orden.getCamion(), orden.getCarga());
			orden.getTerminal().retirarCarga(orden.getCarga());
		}
	}

	@Override
	public void notificar(StringBuilder desgloce, Buque buque) {
		System.out.println("El buque " + buque + "ya llego a la terminal");
		System.out.println(desgloce);
	}
	
}
