package buque;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.*;

public class BuqueFasesHastaArrivedTestCase {
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
	void testUnBuqueAvanzaYElGPSCuandoPasaUnMinutoLeDiceSuPosicionYSabeSuFaseActual() {
		assertEquals(0, buque.getGPS().getLatitud());
		assertEquals(0, buque.getGPS().getLongitud());
		
		buque.getGPS().actualizarPosicionPorUnMinuto();
		
		assertEquals(-0.003125141325200915, buque.getGPS().getLatitud());
		assertEquals(-0.005888797521152724, buque.getGPS().getLongitud());
		assertTrue(buque.estaEnFaseOutbound());
		assertFalse(buque.estaEnFaseInbound()); // Con esto dejamos en claro que solo
		assertFalse(buque.estaEnFaseArrived()); // tendrá una fase a la vez
		
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
	
	@Test
	void testElGPSDelBuqueNoSeActivaHastaQueEsteNoParte() {
		assertFalse(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueEmpiezaAFuncionarCuandoElBuqueParte() { // Esto sería para mostrar lo del timer, ya que eso
																  // lo hace de forma automatica y no se puede mostrar 
																  // en los test sino (solo el hecho de que se activa)
		
		buque.setFecSalida(LocalDateTime.now()); // Solo para que corra el test
		buque.iniciarViaje();
		assertTrue(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueNoPuedeEmpezarSiAunFaltaParaQueElBuqueParta() { // Esto sería para mostrar lo del timer, ya que eso
																  // lo hace de forma automatica y no se puede mostrar 
																  // en los test sino (solo el hecho de que se activa)
								
		buque.setFecSalida(LocalDateTime.now().plusHours(1)); // Solo para que corra el test, con esto sabemos que falta 1 hora aun
		buque.iniciarViaje();
		assertFalse(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueNoPuedeEmpezarSiYaPasoLaFechaDeSalidaDelBuqueYEsteNoSalio() { // Esto sería para mostrar lo del timer, ya que eso
																          				// lo hace de forma automatica y no se puede mostrar 
																         				// en los test sino (solo el hecho de que se activa)
								
		buque.setFecSalida(LocalDateTime.now().minusHours(1)); // Solo para que corra el test, con esto sabemos que ya paso 1 hora
		buque.iniciarViaje();
		assertFalse(buque.getGPS().getTimerIniciado());
	}
}
