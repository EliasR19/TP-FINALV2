package mejorCircuitoTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buscadorMejorCircuito.BuscadorMejorC;
import buscadorMejorCircuito.MenorCantidadTerminales;
import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

public class MenorCantidadTerminalesTest {

	Terminal Argentina;
	Terminal Brasil ;
	Terminal España ;
	Terminal China;
	
	Naviera lineaA ;
	CircuitoMaritimo circuitoA ;
	CircuitoMaritimo circuitoB ;
	
	Viaje viajeA;
	Viaje viajeB;
	
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
		
		viajeA = new Viaje(LocalDateTime.of(2025, 10, 31, 1, 0), Argentina, circuitoA);
		viajeB = new Viaje(LocalDateTime.of(2025, 12, 1, 23, 0), Argentina, circuitoB);
		
		lineaA.agregarCircuitoMaritimo(circuitoA);
		lineaA.agregarCircuitoMaritimo(circuitoB);
		
		bA = new Buque(viajeA);
		bB = new Buque(viajeB);
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);

		//lineaA.showCronogramaBuque();
		
		Argentina.agregarLiena(lineaA);
		mejorRuta = new MenorCantidadTerminales();
		
	}
		
	@Test
	public void MenorTiempoTotalTest() {
		Argentina.setMejorBuscador(mejorRuta);
		assertEquals(circuitoB, Argentina.buscarMejorC(España));
	}
	
}
