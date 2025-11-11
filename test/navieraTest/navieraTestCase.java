package navieraTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import naviera.*;
import terminal.*;
import ubicacionGeografica.UbicacionGeografica;

class navieraTestCase {

	private Naviera naviera;
	private Buque buque1, buque2;
	private UbicacionGeografica u1, u2;
	private Terminal terminal1, terminal2;
	private CircuitoMaritimo circuitoM, circuitoMM;
	
	@BeforeEach
	public void setUp() {
		naviera = new Naviera();
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		terminal1 = new Terminal("Argentina", u1);
		terminal2 = new Terminal("Brasil", u2);
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
