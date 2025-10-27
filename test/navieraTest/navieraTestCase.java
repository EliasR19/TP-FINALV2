package navieraTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;

class navieraTestCase {

	private Naviera naviera;
	private Buque buque1;
	private Buque buque2;
	private Terminal terminal1;
	private Terminal terminal2;
	private CircuitoMaritimo circuitoM;
	private CircuitoMaritimo circuitoMM;
	
	@BeforeEach
	public void setUp() {
		naviera = new Naviera();
	}
	
	@Test
	void testUnaNavieraTieneVariosBuques() {
		
		buque1 = new Buque();
		buque2 = new Buque();
		
		naviera.agregarBuque(buque1);
		naviera.agregarBuque(buque2);
		
		assertTrue(naviera.tieneBuque(buque1));
		assertTrue(naviera.tieneBuque(buque2));
		assertEquals(2, naviera.cantidadDeBuques());
	}
	
	@Test
	void testUnaNavieraTieneVariosCircuitosMaritimos() {
		terminal1 = new Terminal();
		terminal2 = new Terminal();
		circuitoM = new CircuitoMaritimo(terminal1, terminal2);
		circuitoMM = new CircuitoMaritimo(terminal2, terminal1);
		
		naviera.agregarCircuitoMaritimo(circuitoM);
		naviera.agregarCircuitoMaritimo(circuitoMM);
		
		assertTrue(naviera.tieneCircuito(circuitoM));
		assertTrue(naviera.tieneCircuito(circuitoMM));
		assertEquals(2, naviera.cantidadDeCircuitos());
		
	}

}
