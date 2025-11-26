package viajeTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import terminal.Terminal;
import ubicacionGeografica.GPS;

class ViajeTestCase {
	
	private Viaje viaje;
	private Terminal terminal1, terminal2;
	private GPS ubicacionTerminal1, ubicacionTerminal2;
	private LocalDateTime fecInicio;
	private CircuitoMaritimo circuito;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacionTerminal1 = new GPS(200, 100);
		ubicacionTerminal2 = new GPS(300, 200);
		terminal1 = new Terminal("A", ubicacionTerminal1);
		terminal2 = new Terminal("B", ubicacionTerminal2);
		fecInicio = LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0));
		circuito = new CircuitoMaritimo(terminal1, terminal2);
		circuito.agregarTramo(terminal1, terminal2, 120);
		circuito.agregarTramo(terminal2, terminal1, 120);
		viaje = new Viaje(fecInicio, terminal1, circuito);
	}

	@Test
	void testConstructorViaje() {
		assertEquals(fecInicio, viaje.getFecInicio());
		assertEquals(terminal1, viaje.getOrigenActual());
		assertEquals(circuito, viaje.getCircuito());
	}

}
