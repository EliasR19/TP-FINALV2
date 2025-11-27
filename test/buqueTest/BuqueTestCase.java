package buqueTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import container.*;
import ubicacionGeografica.GPS;

class BuqueTestCase {

	private Buque buque;
	private Viaje viaje;
	private GPS gps;
	private Container container;
	
	@BeforeEach
	public void setUp() throws Exception {
			
		viaje = mock(Viaje.class);
		gps = mock(GPS.class);
		buque = new Buque(viaje, gps);
		container = mock(Container.class);
	}
	
	@Test 
	void testConstructorBuque() {
		assertEquals(viaje, buque.getViaje());
		assertEquals(gps, buque.getGPS());
		assertTrue(buque.getCarga().isEmpty());
	}
	
	@Test
	void testAgregarCarga() {
		buque.subirCarga(container);
		assertTrue(buque.tieneCargaDe(container));
	}
	
	@Test
	void testBajarCarga() {
		buque.subirCarga(container);
		buque.bajarCarga(container);
		assertFalse(buque.tieneCargaDe(container));
	}
	
}
