package clientesTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import clientes.Consignee;
import container.BL;
import container.Carga;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.*;
import naviera.CircuitoMaritimo;
import terminal.OrdenImp;
import terminal.Terminal;
import ubicacionGeografica.GPS;

class ConsigneeTestCase {

	private GPS ubicacionTerminal1, ubicacionTerminal2, gpsBuque;
	private Terminal terminal1, terminal2;
	private Consignee consignee;
	private Container carga;
	private BL bl;
	private OrdenImp ordenImp;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno, fecInicio;
	private CircuitoMaritimo circuito;
	private Viaje viaje;
	private Carga carga1, carga2, carga3;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacionTerminal1 = new GPS(200, 100);
		ubicacionTerminal2 = new GPS(300, 200);
		terminal1 = new Terminal("A", ubicacionTerminal1);
		terminal2 = new Terminal("B", ubicacionTerminal2);
		
		consignee = new Consignee("Marcos");
		
		buque = new Buque(mock(Viaje.class), mock(GPS.class));
		
		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
		camion.setCarga(carga);
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		
		buque = new Buque(mock(Viaje.class), mock(GPS.class));
		// se crea un BL 
		bl = mock(BL.class);
		
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		
	}
	
	@Test
	void testImportarCarga() {
		ordenImp = terminal1.generarOrdenImp(consignee, carga, buque, camion, chofer, turno);
		consignee.importarCarga(ordenImp, turno);
		assertFalse(terminal1.tieneContainer(carga));
		assertEquals(carga, camion.getCarga());
		assertEquals("Marcos", consignee.getNombre());
	}

}
