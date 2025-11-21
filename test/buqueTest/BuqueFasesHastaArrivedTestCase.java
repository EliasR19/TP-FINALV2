package buqueTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
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
	private Viaje viaje;

	
	@BeforeEach
	public void setUp() {
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		
		n1 = new Naviera();
		circuitoA = new CircuitoMaritimo(t1, t2);
		fechaSalida = LocalDateTime.of(2025, 11, 8, 10, 0);
		
		circuitoA.agregarTramo(t1, t2, 10);
		circuitoA.agregarTramo(t2, t1, 10);
		n1.agregarCircuitoMaritimo(circuitoA);
		n1.agregarBuque(buque);
		
		viaje = new Viaje(fechaSalida, t1, circuitoA);
		
		buque = new Buque(viaje);
		buque.getGPS().setTimerIniciado(true);
	}
	
	@Test
	void testUnBuqueConoceElViajeQueHará() {
		assertEquals(viaje, buque.getViaje());
	}
	
	@Test
	void testUnBuqueSabeSuPosicionGraciasAlGPS() {
		assertEquals(-23, buque.getGPS().getLatitud());
		assertEquals(-25, buque.getGPS().getLongitud());
	}
	
	@Test
	void testUnBuqueTieneCoomoFaseInicialElEstadoOutbound() {
		assertTrue(buque.estaEnFaseOutbound());
		
		assertFalse(buque.estaEnFaseInbound());
		assertFalse(buque.estaEnFaseArrived()); 
		assertFalse(buque.estaEnFaseWorking());
		assertFalse(buque.estaEnFaseDeparting());
	}
	
	@Test
	void testUnBuqueAvanzaYElGPSCuandoPasaUnMinutoLeDiceSuPosicionYSabeSuFaseActual() {
		buque.getGPS().actualizarPosicionPorUnMinuto();
		
		assertEquals(-22.9999641273093, buque.getGPS().getLatitud());
		assertEquals(-25.00724229574184, buque.getGPS().getLongitud());

		assertTrue(buque.estaEnFaseOutbound());
	}
	
	@Test
	void testUnBuqueCuandoNoEstaA50KMOMenosDeLaTerminalAunNoPasaALaSiguienteFase() {
		for (int i = 0; i < 2442 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(50843.44274510807, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseOutbound()); // Aún está por arriba de los 50kms
	}
	
	@Test
	void testUnBuqueCuandoEnElPróximoMinutoYaVaAEstarA50kmOMenosCambiaAInboundPeroNoMandaElMail() {
		for (int i = 0; i < 2443 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(50103.45619089812, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseInbound());
		assertFalse(buque.mandoMailA(t2));
	}
	
	@Test
	void testUnBuqueCuandoEstaA50KMOMenosDeLaTerminalPasaALaSiguienteFaseYMandaElMailALaTerminalDeDestino() {
		for (int i = 0; i < 2444 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(49363.46944096873, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseInbound());
		assertTrue(buque.mandoMailA(t2));
	}
	
	@Test
	void testUnBuqueLlegaAlDestinoYApagaSuTimer() {
		for (int i = 0; i < 2511 ; i++) { // El buque debe viajar 2511 minutos para llegar al destino
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(-22.91, buque.getGPS().getLatitud());
		assertEquals(-43.17, buque.getGPS().getLongitud());
		assertTrue(buque.estaEnFaseArrived());
		assertFalse(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueIncialmenteEstaApagado() {
		buque = new Buque(viaje);
		assertFalse(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueEmpiezaAFuncionarCuandoElBuqueParte() { // Esto sería para mostrar lo del timer, ya que eso
																  // lo hace de forma automatica y no se puede mostrar 
																  // en los test sino (solo el hecho de que se activa)
		
		buque.getViaje().setFecInicio(LocalDateTime.now()); // Solo para que corra el test
		buque.iniciarViaje();
		assertTrue(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueNoPuedeEmpezarSiAunFaltaParaQueElBuqueParta() { // Esto sería para mostrar lo del timer, ya que eso
																          // lo hace de forma automatica y no se puede mostrar 
																          // en los test sino (solo el hecho de que se activa)
		
		buque = new Buque(viaje);
		buque.getViaje().setFecInicio(LocalDateTime.now().plusHours(1)); // Solo para que corra el test, con esto sabemos que falta 1 hora aun
		try {
			buque.iniciarViaje();
	        fail("Se debe lanzar la excepción porque aún falta una hora para la salida del buque");
	    } catch (RuntimeException e) {
	        assertEquals("Aún falta para iniciar el viaje", e.getMessage());
	    }
		assertFalse(buque.getGPS().getTimerIniciado());
	}
	
	@Test
	void testElGPSDelBuqueNoPuedeEmpezarSiYaPasoLaFechaDeSalidaDelBuqueYEsteNoSalio() { // Esto sería para mostrar lo del timer, ya que eso
																          				// lo hace de forma automatica y no se puede mostrar 
																         				// en los test sino (solo el hecho de que se activa)
		buque = new Buque(viaje);
		buque.getViaje().setFecInicio(LocalDateTime.now().minusHours(1)); // Solo para que corra el test, con esto sabemos que ya paso 1 hora
		try {
			buque.iniciarViaje();
	        fail("Se debe lanzar la excepción porque ya pasó una hora para la salida del buque");
	    } catch (RuntimeException e) {
	        assertEquals("Se deberá arreglar un nuevo cronograma por atraso antes de salir", e.getMessage());
	    }
		assertFalse(buque.getGPS().getTimerIniciado());
	}
}
