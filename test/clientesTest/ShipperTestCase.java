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
import clientes.Shipper;
import container.*;
import empresasTransportistas.*;
import terminal.*;
import ubicacionGeografica.GPS;

class ShipperTestCase {

	private GPS ubicacionTerminal;
	private Terminal terminal;
	private Shipper shipper;
	private Container carga;
	private BL bl;
	private OrdenExp ordenExp;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacionTerminal = new GPS(200, 100);
		terminal = new Terminal("A", ubicacionTerminal);

		shipper = new Shipper("Marcos");

		buque = new Buque(mock(Viaje.class), mock(GPS.class));
		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
		camion.setCarga(carga);
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		

		bl = mock(BL.class);
		
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		
	}

	@Test
	void testExportarCarga() {
		ordenExp = terminal.generarOrdenExp(shipper, carga, buque, camion, chofer, turno);
		shipper.exportarCarga(ordenExp, turno);
		assertTrue(terminal.tieneContainer(carga));
		assertEquals(null, camion.getCarga());
		assertEquals("Marcos", shipper.getNombre());
	}

}
