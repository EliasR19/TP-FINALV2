package terminal;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import Circuitos.Viaje;
import naviera.*;
import buque.Buque;
import clientes.*;
import container.Container;
import empresasTransportistas.*;


public class Terminal {
	
	private String name;
	private List<Naviera> lineas;
	private List<Container> cargas;
	private List<EmpresaTransportista> empresasTransportistas;
	private Map<OrdenExp, LocalDateTime> ordenesExp;
	//private Map<OrdenImp, LocalDateTime> ordenesImp;
	private List<Shipper> shippers;
	
	public Terminal(String name) {
		this.name = name;
		this.lineas = new ArrayList<Naviera>();
		this.cargas = new ArrayList<Container>();
		this.empresasTransportistas = new ArrayList<EmpresaTransportista>();
		this.ordenesExp = new HashMap<OrdenExp, LocalDateTime>();
		this.shippers = new ArrayList<Shipper>();
	}
	
	public void agregarLiena(Naviera l) {
		lineas.add(l);
	}

	public String getName() {
		return name;
	}
	

	

	public void asignarFecSalidaBuqe(Buque bA, LocalDateTime fecSalida) {
		//Se supone que el buque esta dentro de la terminal
		bA.setFecSalida(fecSalida);
	}
	
	// Punto 4
	public double duracionRecorridoEntre(Naviera naviera, Terminal destino) {
		return naviera.duracionEntre(this, destino);
	}
	
	public void generarOrdenExp(Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		ordenesExp.put(new OrdenExp(shipper, carga, buque, camion, chofer), turno);
		shippers.add(shipper);
		
	}

	public Boolean tieneRegistradoSh(Shipper shipper) {
		return shippers.contains(shipper);
	}
		
	public void exportarCarga(Terminal t) {
		//Buscar linea que contenga un circuito que contenga 't' como origen de algun Viaje.
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