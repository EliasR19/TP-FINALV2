package NotificarMailTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.Consignee;
import clientes.Shipper;
import container.BL;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

public class LlegaBuqueTest {
	UbicacionGeografica u1;
	UbicacionGeografica u2;
	Terminal t1;
	Terminal t2;
	CircuitoMaritimo circuitoA;
	LocalDateTime fechaSalida;
	Buque buque;
	Naviera n1; 
	 
	 UbicacionGeografica ubicacionTerminal;
	 Consignee consignee;
	 Shipper shipper;
	 Container carga;
	 Camion camion;
	 Chofer chofer;
	 BL bl;
	 LocalDateTime turno;
		
	 @BeforeEach
	 public void setUp() {
		ubicacionTerminal = new UbicacionGeografica(200, 100);
		consignee = new Consignee("Pedro");
		buque = new Buque();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		turno = LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0));
			
		bl = new BL();
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
			
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		

		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		buque = new Buque();
		n1 = new Naviera();
		circuitoA = new CircuitoMaritimo(t1, t2);
		fechaSalida = LocalDateTime.of(2025, 11, 8, 10, 0);
		circuitoA.agregarTramo(t1, t2, 10);
		circuitoA.agregarTramo(t2, t1, 10);
		n1.agregarCircuitoMaritimo(circuitoA);
		n1.agregarBuque(buque);
		n1.asignarViaje(buque, circuitoA, fechaSalida);
		
		t1.agregarLiena(n1);
	 }
	 
	 @Test
	 public void mailAConsignees() {
		 assertTrue(consignee.getMails().isEmpty());
		 
		t1.generarOrdenImp(consignee, carga, buque, camion, chofer, turno);
		t1.darOrdenDeDepart(buque);
		for (int i = 0; i < 500 ; i++) { 
			//System.out.println(buque.estaEnFaseInbound());
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertTrue(!consignee.getMails().isEmpty());
	 }



}
