package recorridos.Buque;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Circuitos.Tramo;
import buque.Buque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.*;

public class BuqueTest {
	private UbicacionGeografica u1, u2;
	private Terminal t1, t2;
	private Buque buque;
	private GPS gpsB;
	private Naviera n1;

	@BeforeEach
	public void setUp() {
		u1 = new UbicacionGeografica(200000, 1000000);
		u2 = new UbicacionGeografica(450000, 350000);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		buque = new Buque();
		gpsB = buque.getGPS();
		n1 = new Naviera();
		CircuitoMaritimo circuitoA = new CircuitoMaritimo(t1, t2);
		
		n1.agregarCircuitoMaritimo(circuitoA);
		circuitoA.agregarTramo(t1, t2, 10);
		n1.agregarBuque(buque);
		n1.asignarViaje(buque, circuitoA);
	}
	
	@Test
	void testUnBuqueTieneUnGPSQueLeDiceSuPosicion() {
		assertEquals(0, gpsB.getLatitud());
		assertEquals(0, gpsB.getLongitud());
	}
	
	@Test
	void testUnBuqueAvanzaYElGPSCuandoPasaUnMinutoLeDiceSuPosicion() {
		assertEquals(0, gpsB.getLatitud());
		assertEquals(0, gpsB.getLongitud());
		
		gpsB.actualizarPosicionPorUnMinuto();
		
		assertTrue(gpsB.getLatitud() != 0);
		assertTrue(gpsB.getLongitud() != 0);
		
	}

}
