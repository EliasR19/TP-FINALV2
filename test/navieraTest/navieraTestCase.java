package navieraTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import naviera.*;
import terminal.*;

class navieraTestCase {

	private Naviera naviera;
	private Buque buque1, buque2;
	private Terminal terminal1, terminal2;
	private CircuitoMaritimo circuitoM, circuitoMM;
	
	@BeforeEach
	public void setUp() {
		naviera = new Naviera();
		terminal1 = new Terminal("A");
		terminal2 = new Terminal("B");
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
		
		circuitoM = new CircuitoMaritimo(terminal1, terminal2);
		circuitoMM = new CircuitoMaritimo(terminal2, terminal1);
		
		naviera.agregarCircuitoMaritimo(circuitoM);
		naviera.agregarCircuitoMaritimo(circuitoMM);
		
		assertTrue(naviera.tieneCircuito(circuitoM));
		assertTrue(naviera.tieneCircuito(circuitoMM));
		assertEquals(2, naviera.cantidadDeCircuitos());
		
	}


}
