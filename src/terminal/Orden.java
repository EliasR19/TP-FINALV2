package terminal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BooleanSupplier;

import buque.Buque;
import circuitos.Viaje;
import clientes.*;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import servicios.Servicio;
import servicios.ServicioElectricidad;

public abstract class Orden {
	
	private Terminal terminal;
	private Cliente cliente;
	private Container carga;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	private Viaje viaje;
	private boolean pagado;
	private List<Servicio> servicios;
	
	public Orden(Terminal terminal, Cliente cliente, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno, List<Servicio> servicios) {
		
		this.terminal = terminal;
		this.cliente = cliente;
		this.carga = carga;
		this.buque = buque; 
		this.camion = camion;
		this.chofer = chofer;
		this.turno = turno;
		viaje = buque.getViaje();
		pagado = false;
		this.servicios = servicios;
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
	
	public boolean getPagado() {
		return pagado;
	}

	public void setTurno(LocalDateTime turno) {
		this.turno = turno;
	}
	
	public boolean tieneServivio(Servicio servicio) {
		return servicios.contains(servicio);
	}	
	
	
}
