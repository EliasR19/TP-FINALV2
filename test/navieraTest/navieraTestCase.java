package navieraTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import naviera.*;
import terminal.*;
import ubicacionGeografica.UbicacionGeografica;

class navieraTestCase {

	private Naviera naviera;
	private Buque buque1, buque2;
	private UbicacionGeografica u1, u2,u3;
	private Terminal terminal1, terminal2, terminal3;
	private CircuitoMaritimo circuitoM, circuitoMM;
	
	@BeforeEach
	public void setUp() {
		naviera = new Naviera();
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		terminal1 = new Terminal("Argentina", u1);
		terminal2 = new Terminal("Brasil", u2);
		terminal3 = new Terminal("Francia", u3);;
		
		circuitoM = new CircuitoMaritimo(terminal1, terminal3);
		circuitoMM = new CircuitoMaritimo(terminal1, terminal3);
			
			
		
		buque1 = new Buque();
		buque2 = new Buque();
		
		naviera.agregarBuque(buque1);
		naviera.agregarBuque(buque2);
		
		circuitoM.agregarTramo(terminal1, terminal2, 10d);
		circuitoM.agregarTramo(terminal2, terminal3, 30d);
		circuitoM.agregarTramo(terminal3, terminal1, 45d);
		
		circuitoMM.agregarTramo(terminal1, terminal3, 10d);
		circuitoMM.agregarTramo(terminal3, terminal1, 30d);

		
		naviera.agregarCircuitoMaritimo(circuitoM);
		naviera.agregarCircuitoMaritimo(circuitoMM);
		
		naviera.asignarViaje(buque2, circuitoMM, LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		naviera.asignarViaje(buque1, circuitoM, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
	}
	
	@Test
	void testUnaNavieraTieneVariosBuques() {
		assertTrue(naviera.tieneBuque(buque1));
		assertTrue(naviera.tieneBuque(buque2));
		assertEquals(2, naviera.cantidadDeBuques());
		

	
	}
	
	@Test
	void testUnaNavieraTieneVariosCircuitosMaritimos() {


		
		assertTrue(naviera.tieneCircuito(circuitoM));
		assertTrue(naviera.tieneCircuito(circuitoMM));
		assertEquals(2, naviera.cantidadDeCircuitos());
		
	}
	
	@Test
	public void TestDuracionEntreDosTerminales() {
		assertEquals(10, naviera.duracionEntre(terminal1, terminal2));
	}
	
	@Test
	public void cronogramaTest() {
		
		
		assertEquals("\n"
				+ "\n"
				+ "Argentina --> Brasil | salida: 2025-12-01T23:00 | llegada: 2025-12-02T09:00\n"
				+ "Brasil --> Francia | salida: 2025-12-02T09:00 | llegada: 2025-12-03T15:00\n"
				+ "Francia --> Argentina | salida: 2025-12-03T15:00 | llegada: 2025-12-05T12:00\n"
				+ "\n"
				+ "Argentina --> Francia | salida: 2025-10-31T01:00 | llegada: 2025-10-31T11:00\n"
				+ "Francia --> Argentina | salida: 2025-10-31T11:00 | llegada: 2025-11-01T17:00" ,
				naviera.showCronogramaBuque());
	}



}
