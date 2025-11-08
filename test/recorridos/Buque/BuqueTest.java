package recorridos.Buque;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.*;

public class BuqueTest {
	private UbicacionGeografica u1, u2;
	private Terminal t1, t2;
	private CircuitoMaritimo circuitoA;
	private LocalDateTime fechaSalida;
	private Buque buque;
	private Naviera n1;

	@BeforeEach
	public void setUp() {
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		buque = new Buque();
		n1 = new Naviera();
		circuitoA = new CircuitoMaritimo(t1, t2);
		fechaSalida = LocalDateTime.of(2025, 11, 8, 10, 0);
		circuitoA.agregarTramo(t1, t2, 10);
		n1.agregarCircuitoMaritimo(circuitoA);
		n1.agregarBuque(buque);
		n1.asignarViaje(buque, circuitoA, fechaSalida);
	}
	
	@Test
	void test1() {
		assertEquals(1, circuitoA.cantidadDeTramos());
	}
	
	@Test
	void testUnBuqueTieneUnGPSQueLeDiceSuPosicion() {
		assertEquals(0, buque.getGPS().getLatitud());
		assertEquals(0, buque.getGPS().getLongitud());
	}
	
	@Test
	void testUnBuqueAvanzaYElGPSCuandoPasaUnMinutoLeDiceSuPosicion() {
		assertEquals(0, buque.getGPS().getLatitud());
		assertEquals(0, buque.getGPS().getLongitud());
		
		buque.getGPS().actualizarPosicionPorUnMinuto();
		
		assertTrue(buque.getGPS().getLatitud() != 0);
		assertTrue(buque.getGPS().getLongitud() != 0);
		
	}
	
	@Test
	void testUnBuqueCuandoNoEstaA50MetrosOMenosDeLaTerminalAunNoPasaALaSiguienteFase() {
		assertEquals(0, buque.getGPS().getLatitud());
		assertEquals(0, buque.getGPS().getLongitud());
		
		for (int i = 0; i < 7113 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(50689.38652158723, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseOutbound()); // Aún está por arriba de los 50kms
	}
	
	@Test
	void testUnBuqueCuandoEstaA50MetrosOMenosDeLaTerminalPasaALaSiguienteFase() {
		assertEquals(0, buque.getGPS().getLatitud());
		assertEquals(0, buque.getGPS().getLongitud());
		
		for (int i = 0; i < 7114 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(49948.46292897436, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseInbound());
	}
	
	@Test
	void testUnBuqueLlegaAlDestino() {
		assertEquals(0, buque.getGPS().getLatitud());
		assertEquals(0, buque.getGPS().getLongitud());
		
		for (int i = 0; i < 7609 ; i++) { // El buque debe viajar 7609 minutos para llegar al destino
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(-22.91, buque.getGPS().getLatitud());
		assertEquals(-43.17, buque.getGPS().getLongitud());
		assertTrue(buque.estaEnFaseArrived());
	}

}
