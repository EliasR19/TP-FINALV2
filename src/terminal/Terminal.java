package terminal;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private Map<OrdenExp, LocalDateTime> ordenesExp;
	//private Map<OrdenImp, LocalDateTime> ordenesImp;
	private List<Shipper> shippers;
	
	public Terminal(String nombre, UbicacionGeografica ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.lineas = new ArrayList<Naviera>();
		this.containers = new ArrayList<Container>();
		this.empresasTransportistas = new ArrayList<EmpresaTransportista>();
		this.ordenesExp = new HashMap<OrdenExp, LocalDateTime>();
		this.shippers = new ArrayList<Shipper>();
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
		
		this.darCargas(cargaParaElBuque);
		buque.recibirCargas(cargaParaElBuque);
	}

	private void darCargas(List<Container> cargas) {
		for(Container c : cargas) {
			this.darCarga(c);
		}
	}

	private List<Container> getContainers() {
		return containers;
	}

	public void darCarga(Container c) {
		containers.remove(c);
	}

	public void darOrdenDeDepart(Buque buque) {
		buque.partidaHabilitada(this);
	}
	
}