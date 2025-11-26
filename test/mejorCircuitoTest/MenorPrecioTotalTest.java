package mejorCircuitoTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buscador.Buscador;
import buscador.Filtro;
import buscador.Operador;
import buscadorMejorCircuito.BuscadorMejorC;
import buscadorMejorCircuito.MenorPrecioTotal;
import buscadorMejorCircuito.MenorTiempoRecorrido;
import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.GPS;
import ubicacionGeografica.GPS;

public class MenorPrecioTotalTest {

	
	Terminal Argentina;
	Terminal Brasil ;
	Terminal España ;
	Terminal China;
	
	Naviera lineaA ;
	CircuitoMaritimo circuitoA ;
	CircuitoMaritimo circuitoB ;

	Viaje vA, vB;
	
	Buque bA;
	Buque bB ;

	Operador Or;
	Operador And ;
	
	Filtro fSimple;
	Filtro fSimple2;
	Filtro fCompuesto;

	
	Buscador b;
	GPS dummyGPS;

	@BeforeEach
	public void setUp() {
		Argentina = new Terminal("Argentina", new GPS(0, 0));
		Brasil = new Terminal("Brasil", new GPS(0, 0));
		España = new Terminal("España", new GPS(0, 0));
		China = new Terminal("China", new GPS(0, 0));
		
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
		circuitoB.agregarTramo(China,Argentina, 55d);
		
		vA = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)),Argentina, circuitoA);
		vB = new Viaje(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)),Argentina, circuitoB);
		
		dummyGPS = mock(GPS.class);
		
		bA = new Buque(vA,dummyGPS);
		bB = new Buque(vB,dummyGPS);

	

	

		lineaA.agregarCircuitoMaritimo(circuitoA);
		lineaA.agregarCircuitoMaritimo(circuitoB);
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);
		
		//lineaA.asignarViaje(bA, circuitoA,LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		//lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		
		Argentina.agregarLiena(lineaA);
		
		//b = new Buscador(Argentina);
		
		/*
		 * Circuito A = [Argentina, Brasil, España]
		 * Circuito B = [Argentina, España, China]
		 * */
		
	}
		
	@Test
	public void MenorTiempoTotalTest() {
		BuscadorMejorC mejorRuta = new MenorPrecioTotal();
		Argentina.setBuscadorCirMaritimo(mejorRuta);
		assertEquals(circuitoB, Argentina.buscarMejorC(España));
	}
	
}
