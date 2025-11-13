package clientesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.Shipper;
import container.BL;
import container.*;
import empresasTransportistas.*;
import terminal.*;
import ubicacionGeografica.UbicacionGeografica;

class ShipperTestCase {

	private UbicacionGeografica u1;
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
		u1 = new UbicacionGeografica(-23, -25);
		terminal = new Terminal("Argentina", u1);
		shipper = new Shipper("Marcos");
		buque = new Buque();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
		camion.setCarga(carga);
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		
		// se crea un BL 
		bl = new BL();
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		carga = new ContainerTanque("azul1234567", 26d, 22d, 20d, bl);
		
	}

	@Test
	void testExportarCarga() {
		ordenExp = terminal.generarOrdenExp(shipper, carga, buque, camion, chofer, turno);
		shipper.exportarCarga(ordenExp, turno);
		assertTrue(terminal.tieneContainer(carga));
		assertEquals(null, camion.getCarga());
	}

}
