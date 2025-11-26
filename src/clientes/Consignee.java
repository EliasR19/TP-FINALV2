package clientes;

import java.time.LocalDateTime;

import buque.Buque;
import terminal.OrdenImp;

public class Consignee extends Cliente {
	
	private boolean recibioFactura = true; // solo para los test
	

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
	public void notificar(Buque buque) {
		System.out.println("El buque " + buque + "ya llego a la terminal");
		seNotifico = true; // solo para los test
	}
	
	@Override
	public void recibirFactura(StringBuilder sb) {
		recibioFactura = true;
		System.out.println("Factura:" );
		System.out.println(sb);

	}
	

	public boolean isRecibioFactura() {
		return recibioFactura;
	}
	
}
