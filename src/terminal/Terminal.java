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
import buscadorMejorCircuito.BuscadorMejorC;
import buscadorMejorCircuito.MenorCantidadTerminales;
import circuitos.Viaje;
import clientes.Cliente;
import clientes.Consignee;
import clientes.Shipper;
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
	private List<Shipper> shippers;
	private List<Consignee> consignees;

	
	
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
		this.shippers = new ArrayList<Shipper>();
		this.consignees= new ArrayList<Consignee>();
		
		buscador = new Buscador(this);
	}
	
	public void agregarLiena(Naviera l) {
		lineas.add(l);
		//Le va agregando los viajes al buscador a medida que se le agregan 
		//las navieras con los buques(que son los que tienen los viajes)
		buscador.agregarViajes(this.getViajes());
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
		consignees.add(consignee);
		camiones.add(camion);
		choferes.add(chofer);
		OrdenImp ordenImp = new OrdenImp(this, consignee, carga, buque, camion, chofer, turno);
		ordenesImp.add(ordenImp);
		return ordenImp;
	}
	
	public OrdenExp generarOrdenExp(Shipper shipper, Container carga, Buque buque, Camion camion, Chofer chofer, LocalDateTime turno) {
		shippers.add(shipper);
		camiones.add(camion);
		choferes.add(chofer);
		OrdenExp ordenExp = new OrdenExp(this, shipper, carga, buque, camion, chofer, turno);
		ordenesExp.add(ordenExp);
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
	
	public Boolean tieneRegistradoSh(Shipper shipper) {
		return shippers.contains(shipper);
	}
		

	public GPS getUbicacion() {
		return ubicacion;
	}

	public void mandarMailConsignees(Viaje viaje) {
		for (Orden orden : ordenesExp) {
			if (orden.getViaje().equals(viaje)) {
				//notificador.enviarMailDeLlegadaDeBuque(orden.getCliente(), orden);
			}
		}
	}

	public void darOrdenDeInicio(Buque buque) {
		buque.iniciarFaseWorking();
		//buque.realizarDescargaYCarga(this); No se contempla en el trabajo
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

	//public void recibirCarga(List<Container> carga, Buque buque) {
	//	List<Container> cargaParaElBuque = new ArrayList<Container>(this.getContainers());
	//	
	//	for(Container c : carga) {
	//		this.guardarContainer(c);
	//	}
	//	
	//	this.retirarCargas(cargaParaElBuque);
	//	buque.recibirCargas(cargaParaElBuque);
	//}

	//private void retirarCargas(List<Container> cargas) {
	//	for(Container c : cargas) {
	//		this.retirarCarga(c);
	//	}
	//}

	//private List<Container> getContainers() {
	//	return containers;
	//}
	

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

	public Boolean tieneRegistradoC(Consignee consignee) {
		return consignees.contains(consignee);
	}
	
	//PUNTO 3
	
		//Buscador Mejor CM [Unico]
	public void setBuscadorCirMaritimo(BuscadorMejorC mejorC) {
		buscador.setMejorBuscadorCirMaritimo(mejorC);
	}
	
	public CircuitoMaritimo buscarMejorC(Terminal destino) {
		return buscador.buscarMejorCirMaritimo(this, destino);
	}
	
	
		//Buscador Mejores Rutas [List CM]
	public List<CircuitoMaritimo> buscarMejoresRutas(){
		return buscador.buscar();
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
	
	//Cobrar //HACER TESTS
	public void pagoServicios(Container c) {
		Orden orden= this.buscarOrden(c);
		orden.getCliente().recibirMail(this.generarFactura(orden));

	}
	
	private StringBuilder generarFactura(Orden orden) {
		StringBuilder st = new StringBuilder();
		st.append("Servicios Desgloce de Conceptos:\n");
		orden.getServicios().forEach(sv -> st.append(sv.getTipo() + " : " + sv.getPrecioFijo() + "\n"));
		
		return st;
	}



	private Orden buscarOrden(Container c) {
		List<Orden> ordExpImp = new ArrayList<>();
		ordExpImp.addAll(ordenesExp);
		ordExpImp.addAll(ordenesImp);
		return ordExpImp.stream().filter(o -> o.getCarga() == c).findFirst().orElseThrow();
		
	}

	//Servicios
	public void darServicioContainer(Container c, Servicio s) {
		c.darServicio(s);
	}
	
	
	
	//Fases
	public void darOrdenDeDepart(Buque buque) {
		buque.llegoAlDestino(this);
		buque.partidaHabilitada(this);
	}

	public void mandarMailAShippersDel(Viaje viaje) {
		for (Orden orden : ordenesExp) {
			if (orden.getViaje().equals(viaje)) {
				notificador.enviarMailDeSalidaDeBuque(orden.getCliente(), orden);
			}
		}
	}


}