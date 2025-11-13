package mejorCircuitoTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buscadorMejorCircuito.*;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

class MenorTiempoTotalTest {

	Terminal Argentina;
	Terminal Brasil ;
	Terminal España ;
	Terminal China;
	
	Naviera lineaA ;
	CircuitoMaritimo circuitoA ;
	CircuitoMaritimo circuitoB ;
	
	Buque bA;
	Buque bB ;
	BuscadorMejorC mejorRuta ;


	@BeforeEach
	public void setUp() {
		Argentina = new Terminal("Argentina", new UbicacionGeografica(19,20));
		Brasil = new Terminal("Brasil", new UbicacionGeografica(1,4));
		España = new Terminal("España", new UbicacionGeografica(500,320));
		China = new Terminal("China", new UbicacionGeografica(1400, 500));
		
		lineaA = new Naviera();
		
		circuitoA = new CircuitoMaritimo(Argentina, España);
		circuitoB = new CircuitoMaritimo(Argentina, China);
		
		// Circuito A = [Argentina, Brasil, España]
		circuitoA.agregarTramo(Argentina, Brasil, 4);
		circuitoA.agregarTramo(Brasil, España, 20);
		circuitoA.agregarTramo(España, Argentina, 22.3d);
		
		//Circuito B = [Argentina, España, China]
		circuitoB.agregarTramo(Argentina, España, 4);
		circuitoB.agregarTramo(España, China, 30d);
		circuitoB.agregarTramo(China,Argentina, 25);
		
		
		
		bA = new Buque();
		bB = new Buque();
		
		lineaA.agregarCircuitoMaritimo(circuitoA);
		lineaA.agregarCircuitoMaritimo(circuitoB);
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);

		
		//Argentina.asignarViaje(bB, circuitoA);
		lineaA.asignarViaje(bA, circuitoA, LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		//lineaA.showCronogramaBuque();
		
		Argentina.agregarLiena(lineaA);
		mejorRuta = new MenorTiempoRecorrido();
		
	}
		
	@Test
	public void MenorTiempoTotalTest() {
		Argentina.setMejorBuscador(mejorRuta);
		assertEquals(circuitoB, Argentina.buscarMejorC(España));
	}
	
	
}
