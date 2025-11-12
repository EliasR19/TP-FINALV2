package terminal;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Circuitos.Viaje;
import naviera.*;
import ubicacionGeografica.UbicacionGeografica;
import buque.Buque;
import clientes.*;
import container.Container;
import empresasTransportistas.*;


public class Terminal {
	
	private String nombre;
	private UbicacionGeografica ubicacion;
	private List<Naviera> lineas;
	private List<Container> containers;
	private List<EmpresaTransportista> empresasTransportistas;
	private List<Camion> camiones;
	private List<Chofer> choferes;
	private List<OrdenExp> ordenesExp;
	private List<OrdenImp> ordenesImp;
	private List<Shipper> shippers;
	private List<Consignee> consignees;
	
	
	public Terminal(String nombre, UbicacionGeografica ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.lineas = new ArrayList<Naviera>();
		this.containers = new ArrayList<Container>();
		this.empresasTransportistas = new ArrayList<EmpresaTransportista>();
		this.camiones = new ArrayList<Camion>();
		this.choferes = new ArrayList<Chofer>();
		this.ordenesExp = new ArrayList<OrdenExp>();
		this.ordenesImp = new ArrayList<OrdenImp>();
		this.shippers = new ArrayList<Shipper>();
		this.consignees= new ArrayList<Consignee>();
	}
	
	public void agregarLiena(Naviera l) {
		lineas.add(l);
	}

	public String getNombre() {
		return nombre;
	}
	

	

	public void asignarFecSalidaBuqe(Buque bA, LocalDateTime fecSalida) {
		//Se supone que el buque esta dentro de la terminal
		bA.setFecSalida(fecSalida);
	}
	
	// Punto 4
	public double duracionRecorridoEntre(Naviera naviera, Terminal destino) {
		return naviera.duracionEntre(this, destino);
	}
	
	public void generarOrdenImp(Consignee consignee, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		ordenesImp.add(new OrdenImp(this, consignee, carga, buque, camion, chofer, turno));
		consignees.add(consignee);
		camiones.add(camion);
		choferes.add(chofer);
	}
	
	public void generarOrdenExp(Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		ordenesExp.add(new OrdenExp(this, shipper, carga, buque, camion, chofer, turno));
		shippers.add(shipper);
		camiones.add(camion);
		choferes.add(chofer);
	}

	public boolean respetaElTurnoExp(OrdenExp orden, LocalDateTime horario) {
		LocalDateTime limiteInferior = orden.getTurno().minusHours(3);
		LocalDateTime limiteSuperior = orden.getTurno().plusHours(3);
		return horario.isBefore(limiteInferior) && horario.isAfter(limiteSuperior);
	}
	
	public boolean respetaElTurnoImp(OrdenImp orden, LocalDateTime horario) {
		LocalDateTime limite24hs = orden.getTurno().plusHours(24);
		return horario.isBefore(orden.getTurno()) && horario.isAfter(limite24hs);
	}
	
	public boolean respetaCamionYChofer(Orden orden) {
		return camiones.contains(orden.getCamion()) && choferes.contains(orden.getChofer());
	}
	
	public Boolean tieneRegistradoSh(Shipper shipper) {
		return shippers.contains(shipper);
	}
		

	public UbicacionGeografica getUbicacion() {
		return ubicacion;
	}

	public void mandarMailConsignees() {
		// TODO Auto-generated method stub
	}

	public void darOrdenDeInicio(Buque buque) {
		buque.iniciarFaseWorking();
		buque.realizarDescargaYCarga(this);
	}

	public void guardarContainer(Container container) {
		containers.add(container);
	}

	public boolean tieneContainer(Container container) {
		return containers.contains(container);
	}

	public void recibirCarga(List<Container> carga, Buque buque) {
		List<Container> cargaParaElBuque = new ArrayList<>(this.getContainers());
		
		for(Container c : carga) {
			this.guardarContainer(c);
		}
		
		this.retirarCargas(cargaParaElBuque);
		buque.recibirCargas(cargaParaElBuque);
	}

	private void retirarCargas(List<Container> cargas) {
		for(Container c : cargas) {
			this.retirarCarga(c);
		}
	}

	private List<Container> getContainers() {
		return containers;
	}

	public void retirarCarga(Container c) {
		containers.remove(c);
	}
	
	public List<Naviera> getNavieras(){
		return lineas;
	}
	
	public List<Viaje> getViajes(){
		for(Naviera n : lineas) {
			return n.getBuques().stream().map(b -> b.getViaje()).collect(Collectors.toList());
		}
		
		return null;
	}
	
}