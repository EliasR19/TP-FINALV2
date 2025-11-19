package buqueTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

class BuqueTestCase {

	private UbicacionGeografica u1, u2;
	private Terminal t1, t2;
	private CircuitoMaritimo circuitoA;
	private LocalDateTime fechaSalida;
	private Buque buque;
	private Naviera n1; 
	private Viaje viaje;

	
	@BeforeEach
	public void setUp() {
		u1 = mock(UbicacionGeografica.class);
		u2 = mock(UbicacionGeografica.class);
		t1 = mock(Terminal.class);
		t2 = mock(Terminal.class);
		
		n1 = mock(Naviera.class);
	
		circuitoA = mock(CircuitoMaritimo.class);
		fechaSalida = mock(LocalDateTime.class);
		viaje = mock(Viaje.class);
		
		buque = new Buque(viaje);
	}
	
	@Test
	void testUnBuqueConoceElViajeQueHará() {
		assertEquals(viaje, buque.getViaje());
	}

}
