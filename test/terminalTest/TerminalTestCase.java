package terminalTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.*;
import container.BL;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import terminal.*;
import ubicacionGeografica.UbicacionGeografica;
 
class TerminalTestCase {
	
	private UbicacionGeografica ubicacionTerminal;
	private Terminal terminal;
	private Consignee consignee;
	private Shipper shipper;
	private Container carga;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private BL bl;
	private LocalDateTime turno;
	
	
		@BeforeEach
		void setUp() throws Exception {
		ubicacionTerminal = new UbicacionGeografica(200, 100);
		terminal = new Terminal("A", ubicacionTerminal);
		shipper = new Shipper("Marcos");
		buque = new Buque();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		turno = LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0));
		
		bl = new BL();
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
	}
	
	@Test
	void testLaTerminalTieneEstaUbicadaEn200X100Y() {
		UbicacionGeografica ubicacion = terminal.getUbicacion();
		assertEquals(200, ubicacion.getLatitud());
		assertEquals(100, ubicacion.getLongitud());
	}


	@Test
	void testGenerarOrdenExp() {
		
		terminal.generarOrdenExp(shipper, carga, buque, camion, chofer, turno);
		
		assertTrue(terminal.tieneRegistradoSh(shipper));
		assertTrue(terminal.tieneRegistradoElCamion(camion));
		assertTrue(terminal.tieneRegistradoAlChofer(chofer));
		assertEquals(1, terminal.cantidadDeOrdenesExp());
		
	}
	
	@Test 
	void testGenerarOrdenImp() {
		
		terminal.generarOrdenImp(consignee, carga, buque, camion, chofer, turno);
		
		assertTrue(terminal.tieneRegistradoC(consignee));
		assertTrue(terminal.tieneRegistradoElCamion(camion));
		assertTrue(terminal.tieneRegistradoAlChofer(chofer));
		assertEquals(1, terminal.cantidadDeOrdenesImp());
		
		
	}
	
}
