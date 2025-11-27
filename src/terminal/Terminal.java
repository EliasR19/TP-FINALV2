package terminal;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import naviera.*;
import servicios.Servicio;
import ubicacionGeografica.*;
import buque.Buque;
import buscador.Buscador;
import buscador.Filtro;
import buscadorMejorCircuito.ConceptoBusquedaDestino;
import buscadorMejorCircuito.MenorCantidadTerminales;
import circuitos.Viaje;
import clientes.Cliente;
import clientes.Consignee;
import clientes.Shipper;
import container.Carga;
import container.Container;
import empresasTransportistas.*;


public class Terminal {
	
	private String nombre;
	private GPS ubicacion;
	private List<Naviera> lineas;
	private List<Container> containers;
	private List<EmpresaTransportista> empresasTransportistas;
	private List<Camion> camiones;
	private List<Chofer> choferes;
	private List<Orden> ordenesExp;
	private List<Orden> ordenesImp;
	private List<Cliente> clientes;

	
	
	private Buscador buscador;
	
	public Terminal(String nombre, GPS ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.lineas = new ArrayList<Naviera>();
		this.containers = new ArrayList<Container>();
		this.empresasTransportistas = new ArrayList<EmpresaTransportista>();
		this.camiones = new ArrayList<Camion>();
		this.choferes = new ArrayList<Chofer>();
		this.ordenesExp = new ArrayList<Orden>();
		this.ordenesImp = new ArrayList<Orden>();
		this.clientes = new ArrayList<Cliente>();
		
		buscador = new Buscador(this);
	}
	
	public void agregarLiena(Naviera l) {
		lineas.add(l);
		//Le va agregando los viajes al buscador a medida que se le agregan 
		//las navieras con los buques(que son los que tienen los viajes)
		//buscador.agregarViajes(this.getViajes());
	}

	public String getNombre() {
		return nombre;
	}


	
	// Punto 4
	public double duracionRecorridoEntre(Naviera naviera, Terminal destino) {
		return naviera.duracionEntre(this, destino);
	}
	
	
	///
	public OrdenImp generarOrdenImp(Consignee consignee, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		clientes.add(consignee);
		camiones.add(camion);
		choferes.add(chofer);
		OrdenImp ordenImp = new OrdenImp(this, consignee, carga, buque, camion, chofer, turno);
		ordenesImp.add(ordenImp);
		
		buque.addObserver(consignee);
		return ordenImp;
	}

	
	public OrdenExp generarOrdenExp(Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		clientes.add(shipper);
		camiones.add(camion);
		choferes.add(chofer);
		OrdenExp ordenExp = new OrdenExp(this, shipper, carga, buque, camion, chofer, turno);
		ordenesExp.add(ordenExp);
		
		buque.addObserver(shipper);
		return ordenExp;
	}

	public boolean respetaElTurnoExp(OrdenExp orden, LocalDateTime horario) {
		// verifica si el camion llega a dejar la carga en un limite de 3 horas antes o despues del turno.
		LocalDateTime limiteInferior = orden.getTurno().minusHours(3);
		LocalDateTime limiteSuperior = orden.getTurno().plusHours(3);
		return horario.isBefore(limiteInferior) && horario.isAfter(limiteSuperior) || horario == orden.getTurno();
	}
	
	public boolean respetaElTurnoImp(OrdenImp orden, LocalDateTime horario) {
		LocalDateTime limite24hs = orden.getTurno().plusHours(24);
		return horario.isBefore(orden.getTurno()) && horario.isAfter(limite24hs) || horario == orden.getTurno();
	}
	
	public boolean respetaCamionYChofer(Orden orden) {
		return camiones.contains(orden.getCamion()) && choferes.contains(orden.getChofer());
	}
	
	public void descargar(Camion camion) {
		camion.descargar();
		camiones.remove(camion);
		choferes.remove(camion.getChofer());
	}
	
	public void llevarCarga(Camion camion, Container carga) {
		camion.setCarga(carga);
		camiones.remove(camion);
		choferes.remove(camion.getChofer());
	}
	
	public boolean tieneRegistradoElCamion(Camion camion) {
		return camiones.contains(camion);
	}
	
	public boolean tieneRegistradoAlChofer(Chofer chofer) {
		return choferes.contains(chofer);
	}
	
	public int cantidadDeOrdenesExp() {
		return ordenesExp.size();
	}
	
	public Boolean tieneRegistradoCliente(Cliente c) {
		return clientes.contains(c);
	}
		

	public GPS getUbicacion() {
		return ubicacion;
	}



	public void darOrdenDeInicio(Buque buque) {
		buque.iniciarFaseWorking();
	}

	public void guardarContainer(Container container) {
		containers.add(container);
	}

	
	public void retirarCarga(Container c) {
		containers.remove(c);
	}
	
	public boolean tieneContainer(Container container) {
		return containers.contains(container);
	}


	

	public List<Naviera> getNavieras(){
		return lineas;
	}
	
	public List<Viaje> getViajes(){
		for(Naviera n : lineas) {
			return n.getBuques().stream().map(b -> b.getViaje()).collect(Collectors.toList());
		}
		return new ArrayList<Viaje>();
	}

	public Integer cantidadDeOrdenesImp() {
		return ordenesImp.size();
	}

	//public Integer cantidadChoferes(Chofer chofer) {
	//	return choferes.size();
	//}

	//public Integer cantidadCamiones(Camion camion) {
	//	return camiones.size();
	//}

	
	//PUNTO 3
	
		//Buscador Mejor CM [Unico]
	public void setBuscadorCirMaritimo(ConceptoBusquedaDestino mejorC) {
		buscador.setMejorBuscadorCirMaritimo(mejorC);
	}
	
	public CircuitoMaritimo buscarMejorC(Terminal destino) {
		return buscador.buscarMejorCirMaritimo(this.getViajes(), this, destino);
	}
	
	
		//Buscador Mejores Rutas [List CM]
	public List<CircuitoMaritimo> buscarMejoresRutas(){
		return buscador.buscar(this.getViajes());
	}
	
	public void setFiltroBuscadorMejoresCM(Filtro filtro) {
		buscador.agregarFiltro(filtro);
	}
	
	
	
	public Buscador getBuscador() {
		return buscador;
	}
	
	public List<CircuitoMaritimo> getCircuitos(){
		List<CircuitoMaritimo> circuitos = new ArrayList<>();
		lineas.stream().forEach(n -> circuitos.addAll(n.getCircuitosMaritimos()));
		return circuitos;
	}
	
	//Factura, se manda en Outbound
	public void cobrarServicios(Buque buque) {
		List<Orden> ordenes= ordenesImp.stream().filter(o -> o.getBuque() == buque).collect(Collectors.toList());
		ordenes.forEach(o -> this.mandarFactura(o));
	}
	
	public void mandarFactura(Orden o) {
		o.getCliente().recibirFactura(this.generarFactura(o));
	}
	
	private StringBuilder generarFactura(Orden orden) {
		StringBuilder st = new StringBuilder();
		st.append("Servicios Desgloce de Conceptos:\n");
		orden.getServicios().forEach(sv -> st.append(sv.getTipo() + " : " + sv.getPrecioFijo() + "\n"));
		if(orden instanceof OrdenImp) {
			st.append("Viaje: " +  orden.getBuque().getViaje().costoViaje(orden.getTerminal(), this));
		}
		
		return st;
	}

	

	public List<Orden> buscarOrdenes(List<Orden> ordenes, Buque buque) {
		List<Orden> ordenesANotificar =  ordenes.stream().filter(o -> buque.getObservadores().contains(o.getCliente())).collect(Collectors.toList());
		return ordenesANotificar;
	}
	
	//Observer NotificadorMail
	
		public void notificarArribo(Buque buque) {
			//Notifica Consginee
			List<Orden> ordenesANotificar =  buscarOrdenes(ordenesImp, buque);
			ordenesANotificar.forEach(ord -> ord.getCliente().notificar( buque));
		}
		
		public void notificarSalida(Buque buque) {
			List<Orden> ordenesANotificar =  buscarOrdenes(ordenesExp, buque);
			ordenesANotificar.forEach(ord -> ord.getCliente().notificar(buque));
		}

	
	
	
	//Fases
	public void darOrdenDeDepart(Buque buque) {
		buque.llegoAlDestino(this);
		buque.partidaHabilitada(this);
	}



}