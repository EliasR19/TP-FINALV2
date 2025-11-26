package buqueTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Arrived;
import buque.Buque;
import buque.Inbound;
import circuitos.Viaje;
import container.BL;
import container.Carga;
import container.ContainerTanque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.*;

public class BuqueFasesRestantesTestCase {
	private GPS u1, u2, u3;
	private Terminal t1, t2, t3;
	private CircuitoMaritimo circuitoA;
	private LocalDateTime fechaSalida;
	private Buque buque;
	private Naviera naviera;
	private BL bl1, bl2;
	private ContainerTanque container1, container2;

	@BeforeEach
	public void setUp() throws Exception {
		u1 = new GPS(-23, -25);
		u2 = new GPS(-22.91, -43.17);
		u3 = new GPS(-10, -20);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		t3 = new Terminal("Angora", u3);
		
		buque = new Buque(mock(Viaje.class), mock(GPS.class));
		
		fechaSalida = LocalDateTime.of(2025, 11, 8, 10, 0);
		
		circuitoA = new CircuitoMaritimo(t1, t2); 
		circuitoA.agregarTramo(t1, t2, 2511);
		circuitoA.agregarTramo(t2, t3, 10);
		circuitoA.agregarTramo(t3, t1, 100);
		
		naviera = new Naviera();
		naviera.agregarCircuitoMaritimo(circuitoA);
		naviera.agregarBuque(buque);
		naviera.asignarViaje(buque, circuitoA, fechaSalida);
				
		bl1 = mock(BL.class);
		
		bl2 = mock(BL.class);
		
		container1 = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl1);
		buque.subirCarga(container1);
		
		container2 = new ContainerTanque("azul1234568", "Tanque", 26d, 22d, 20d, bl2);
		t2.guardarContainer(container2);

		// Seteamos que el buque ya está en el destino
		buque.getGPS().setLatitud(-22.91);
		buque.getGPS().setLongitud(-43.17);
		buque.setFase(new Arrived(buque));
	}
	
	/*@Test
	void testUnBuqueTieneCargaDe() {
		assertTrue(buque.tieneCargaDe(container1));
		assertEquals(1, buque.cargaTotal());
	}
	
	
	@Test
	void testUnBuquePasaALaFaseWorkingMedianteLaTerminalSoloSiEstaEnLaFaseArrivedYRealizaLaDescargaYCarga() {
		t2.darOrdenDeInicio(buque);
		
		assertTrue(buque.estaEnFaseWorking());

		assertTrue(buque.tieneCargaDe(container2));
		assertFalse(buque.tieneCargaDe(container1));
		

		assertTrue(t2.tieneContainer(container1));
		assertFalse(t2.tieneContainer(container2));
	}
	
	@Test
	void testUnBuqueNoPuedePasarALaFaseWorkingSiEstaEnUnaFaseQueNoSeaArrived() {
		buque.setFase(new Inbound(buque)); // Seteamos que aun no llegó
		t2.darOrdenDeInicio(buque);
		
		assertFalse(buque.estaEnFaseWorking());
	}
	
	@Test
	void testUnBuquePasaALaFaseDepartingSoloCuandoLaTerminalLoAutorizaYYaRealizóElTrabajoDeDescargaYCarga() {
		t2.darOrdenDeInicio(buque); // Con esto aseguramos que el trabajo fue completado
		t2.darOrdenDeDepart(buque);
		
		assertTrue(buque.estaEnFaseDeparting());
	}
	
	@Test
	void testUnBuquePasaALaFaseDepartingYSuNuevoDestinoEsLaT3() {
		t2.darOrdenDeInicio(buque);
		t2.darOrdenDeDepart(buque);
		
		assertEquals(t3, buque.getDestinoActual());
	}
	
	@Test
	void testUnBuqueEnFaseDepartingCuandoEstáAlejado1KilometroOMasVuelveAPasarAFaseOutboundYLaTerminalLeAvisaALosShippers() {
		
		GPS ubicacionTerminal = t2.getUbicacion();
		
		t2.darOrdenDeInicio(buque);
		t2.darOrdenDeDepart(buque);
		
		buque.actualizarPosicion();
		buque.actualizarPosicion();
		
		assertEquals(2219.91728655714, buque.getGPS().distanciaA(ubicacionTerminal));
		assertTrue(buque.estaEnFaseOutbound()); 
		
	}*/
	


}