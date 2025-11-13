package terminal;

import java.time.LocalDateTime;

import buque.Buque;
import circuitos.Viaje;
import clientes.*;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;

public abstract class Orden {
	
	private Terminal terminal;
	private Cliente cliente;
	private Container carga;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	private Viaje viaje;
	
	public Orden(Terminal terminal, Cliente cliente, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		
		this.terminal = terminal;
		this.cliente = cliente;
		this.carga = carga;
		this.buque = buque;
		this.camion = camion;
		this.chofer = chofer;
		this.turno = turno;
		this.viaje = buque.getViaje();
	}
	
	public Terminal getTerminal() {
		return this.terminal;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public Container getCarga() {
		return carga;
	}

	public Buque getBuque() {
		return buque;
	}

	public Camion getCamion() {
		return camion;
	}

	public Chofer getChofer() {
		return chofer;
	}

	public LocalDateTime getTurno() {
		return turno;
	}
	
	public Viaje getViaje() {
		return viaje;
	}

	public void setTurno(LocalDateTime turno) {
		this.turno = turno;
	}
	
}
