package navieraTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import naviera.*;
import terminal.*;
import ubicacionGeografica.GPS;
import ubicacionGeografica.GPS;

class navieraTestCase {

	private Naviera naviera;
	private Buque buque1, buque2;
	private GPS u1, u2, u3;
	private Terminal terminal1, terminal2, terminal3;
	private CircuitoMaritimo circuito1, circuito2;
	private Viaje viaje1, viaje2;
	private GPS gps1, gps2;
	
	@BeforeEach
	public void setUp() {
		naviera = new Naviera();
		u1 = new GPS(-23, -25);
		u2 = new GPS(-22.91, -43.17);
		terminal1 = new Terminal("Argentina", u1);
		terminal2 = new Terminal("Brasil", u2);
		terminal3 = new Terminal("España", u3);		
		
		circuito1 = new CircuitoMaritimo(terminal1, terminal2);
		circuito1.agregarTramo(terminal1, terminal2, 4);
        circuito1.agregarTramo(terminal2, terminal3, 20);
        circuito1.agregarTramo(terminal3, terminal1, 22.3d);
        
		circuito2 = new CircuitoMaritimo(terminal2, terminal1);
		circuito2.agregarTramo(terminal1, terminal2, 4);
        circuito2.agregarTramo(terminal2, terminal3, 20);
        circuito2.agregarTramo(terminal3, terminal1, 22.3d);
		
	}
	
	@Test
	void testUnaNavieraTieneVariosBuques() {
		
		viaje1 = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)), terminal1, circuito1);
		viaje2 = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)), terminal2, circuito2);

        gps1 = new GPS(100, 200);
        gps2 = new GPS(200, 100);
		
		buque1 = new Buque(viaje1, gps1);
		buque2 = new Buque(viaje2, gps2);
		
		naviera.agregarBuque(buque1);
		naviera.agregarBuque(buque2);
		
		assertTrue(naviera.tieneBuque(buque1));
		assertTrue(naviera.tieneBuque(buque2));
		assertEquals(2, naviera.cantidadDeBuques());
	}
	
	@Test
	void testUnaNavieraTieneVariosCircuitosMaritimos() {
		
		naviera.agregarCircuitoMaritimo(circuito1);
		naviera.agregarCircuitoMaritimo(circuito2);
		
		assertTrue(naviera.tieneCircuito(circuito1));
		assertTrue(naviera.tieneCircuito(circuito2));
		assertEquals(2, naviera.cantidadDeCircuitos());
		
	}


}
