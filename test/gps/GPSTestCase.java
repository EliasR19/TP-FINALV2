package gps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ubicacionGeografica.GPS;

class GPSTestCase {
	
	private GPS gps1;
	private GPS gps2;
	
	@BeforeEach
	void setUp() throws Exception {
		gps1 = new GPS(100, 200);
		gps2 = new GPS(50, 100);
	}

	@Test
	void testConstructorGPS() {
		assertEquals(100, gps1.getLatitud());
		assertEquals(200, gps1.getLongitud());
	}
	
	@Test
	void testEsMismaPosicion() {
		gps2.setPosicion(100,200);
		assertTrue(gps1.esMismaPosicion(gps2));
	}
	
	@Test
	void testNoEsMismaPosicion() {
		assertFalse(gps1.esMismaPosicion(gps2));
	}

}
