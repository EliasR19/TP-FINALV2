package ObserverTest;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buque.Departing;
import buque.Inbound;
import buque.Outbound;
import circuitos.Viaje;

import clientes.Consignee;
import clientes.Shipper;
import container.BL;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.OrdenExp;
import terminal.OrdenImp;
import terminal.Terminal;
import ubicacionGeografica.GPS;

class ObserverTest {

	Terminal Argentina;
	Terminal Brasil ;
	Terminal España ;
	Terminal China;
	
	Naviera lineaA ;
	CircuitoMaritimo circuitoA ;

	Viaje vA, vB;
	
	Buque bA;

	 Container carga;
	 BL bl;
	 Camion camion;
	 Chofer chofer;
	 LocalDateTime turno;
	

	@BeforeEach
	public void setUp() {
		Argentina = new Terminal("Argentina", new GPS(0, 0));
		Brasil = new Terminal("Brasil", new GPS(0, 0));
		España = new Terminal("España", new GPS(0, 0));
		China = new Terminal("China", new GPS(0, 0));
		
		lineaA = new Naviera();
		
		circuitoA = new CircuitoMaritimo(Argentina, España);

		
		// Circuito A = [Argentina, Brasil, España]
		circuitoA.agregarTramo(Argentina, Brasil, 4);
		circuitoA.agregarTramo(Brasil, España, 20);
		circuitoA.agregarTramo(España, Argentina, 22.3d);
		

		vA = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)),Argentina, circuitoA);

		
		
		bA = new Buque(vA, new GPS(100d,200d));



		lineaA.agregarCircuitoMaritimo(circuitoA);

		lineaA.agregarBuque(bA);

		
		//lineaA.asignarViaje(bA, circuitoA,LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		//lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		
		Argentina.agregarLiena(lineaA);
		
		
		//b = new Buscador(Argentina);
		
		/*
		 * Circuito A = [Argentina, Brasil, España]
		 * Circuito B = [Argentina, España, China]
		 * */
		

		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
		
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		bl = mock(BL.class);
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		


	}
	
	@Test
	public void ConsigneeRecibeMail() {
		Consignee cliente = new Consignee("Marcos");
		OrdenImp orden = Argentina.generarOrdenImp(cliente, carga, bA, camion, chofer, turno);
		cliente.importarCarga(orden, turno);
		
		
		
		bA.setFase(new Inbound(bA));
		
		bA.cambiarPosicion(40,20);
		assertTrue(bA.estaEnFaseInbound());
		assertTrue(cliente.isSeNotifico());
	}
	
	@Test
	public void ShipperRecibeMail() {
		Shipper cliente = new Shipper("Marcos");
		OrdenExp orden = Argentina.generarOrdenExp(cliente, carga, bA, camion, chofer, turno);
		cliente.exportarCarga(orden, turno);
		
		bA.setFase(new Departing(bA));
		
		assertTrue(bA.estaEnFaseDeparting());
		assertTrue(cliente.isSeNotifico());
	}
	
	@Test
	public void RecibeFactura() {
		Consignee cliente = new Consignee("Marcos");
		OrdenImp orden = Argentina.generarOrdenImp(cliente, carga, bA, camion, chofer, turno);
		cliente.importarCarga(orden, turno);
		
		bA.setFase(new Outbound(bA));
		
		assertTrue(bA.estaEnFaseOutbound());
		assertTrue(cliente.isRecibioFactura());
	}
	
}
