package terminalTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.Shipper;
import container.BL;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import terminal.Terminal;

class TerminalTestCase {
	
	private Terminal terminal;
	private Shipper shipper;
	private Container carga;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private BL bl;
	private LocalDateTime turno;
	
	
	@BeforeEach
	void setUp() throws Exception {
		terminal = new Terminal("A");
		shipper = new Shipper();
		buque = new Buque();
		camion = new Camion();
		chofer = new Chofer();
		turno = LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0));
		
		bl = new BL();
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		carga = new ContainerTanque("azul1234567", 26d, 22d, 20d, bl);
	}

	@Test
	void testGenerarOrdenExp() {
		
		terminal.generarOrdenExp(shipper, carga, buque, camion, chofer, turno);
		
		assertTrue(terminal.tieneRegistradoSh(shipper));
		
	}

}
