package buqueTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.Consignee;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
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
		circuitoA.agregarTramo(t2, t1, 10);
		n1.agregarCircuitoMaritimo(circuitoA);
		n1.agregarBuque(buque);
		n1.asignarViaje(buque, circuitoA, fechaSalida);
	}
	
	@Test
	void testUnBuqueTieneUnGPSQueLeDiceSuPosicion() {
		assertEquals(-23, buque.getGPS().getLatitud());
		assertEquals(-25, buque.getGPS().getLongitud());
	}
	 
	@Test
	void testUnBuqueAvanzaYElGPSCuandoPasaUnMinutoLeDiceSuPosicionYSabeSuFaseActual() {
		buque.getGPS().actualizarPosicionPorUnMinuto();
		
		assertEquals(-22.9999641273093, buque.getGPS().getLatitud());
		assertEquals(-25.00724229574184, buque.getGPS().getLongitud());
		assertTrue(buque.estaEnFaseOutbound());
		assertFalse(buque.estaEnFaseInbound()); // Con esto dejamos en claro que solo
		assertFalse(buque.estaEnFaseArrived()); // tendrá una fase a la vez
		assertFalse(buque.estaEnFaseWorking());
		assertFalse(buque.estaEnFaseDeparting());
	}
	
	@Test
	void testUnBuqueCuandoNoEstaA50MetrosOMenosDeLaTerminalAunNoPasaALaSiguienteFase() {
		for (int i = 0; i < 2443 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(50103.45619089812, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseOutbound()); // Aún está por arriba de los 50kms
	}
	
	@Test
	void testUnBuqueCuandoEstaA50MetrosOMenosDeLaTerminalPasaALaSiguienteFase() {
		for (int i = 0; i < 2444 ; i++) { 
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
		assertEquals(49363.46944096873, buque.getGPS().getDistanciaRestante());
		assertTrue(buque.estaEnFaseInbound());
	}
	
	@Test
	void testUnBuqueLlegaAlDestino() {
		Consignee consignee = new Consignee("Marcos");
		Camion camion = new Camion();
		Chofer chofer = new Chofer("Maxi");
		LocalDateTime turno = LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0));
		
		t1.generarOrdenImp(consignee, null, buque, camion, chofer, turno);
		
		for (int i = 0; i < 2511 ; i++) { // El buque debe viajar 2511 minutos para llegar al destino
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
