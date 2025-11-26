package buqueTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import ubicacionGeografica.GPS;

class BuqueTestCase {

	private Buque buque;
	private Viaje viaje;
	private GPS gps;

	
	@BeforeEach
	public void setUp() throws Exception {
			
		viaje = mock(Viaje.class);
		gps = new GPS(100, 200);
		buque = new Buque(viaje, gps);
	}
	
	@Test 
	void testConstructorBuque() {
		verify(this.buque);
	}
	
	@Test
	void testUnBuqueConoceElViajeQueHará() {
		assertEquals(viaje, buque.getViaje());
	}

}
