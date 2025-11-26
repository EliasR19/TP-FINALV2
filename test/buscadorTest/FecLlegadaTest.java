package buscadorTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buscador.*;
import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.GPS;
import ubicacionGeografica.GPS;

public class FecLlegadaTest {
	
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
	

	GPS dummyGPS;
	
	Buscador b;
	

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
		
		bA = new Buque(vA, new GPS(100d,200d));
		bB = new Buque(vB,new GPS(100d,200d));


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
	public void fecLlegadaSimple() {
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		Argentina.setFiltroBuscadorMejoresCM(fSimple);
		//b.agregarFiltro(fSimple);
		//assertEquals(List.of(circuitoB), b.buscar());
		assertEquals(List.of(circuitoB), Argentina.buscarMejoresRutas());
	}
	
	
	@Test
	void fecLlegadaCompuestoAnd() {
		And = new And();
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,3), LocalTime.of(9, 0)), China);
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		Argentina.setFiltroBuscadorMejoresCM(fCompuesto);
		//b.agregarFiltro(fCompuesto);
		assertEquals(List.of(circuitoB), Argentina.buscarMejoresRutas());
		
	}
	
	@Test
	void fecLlegadaCompuestoOr() {
		Or = new Or();
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(5, 0)), Brasil);
		
		fCompuesto = new FiltroCompuesto(Or, fSimple, fSimple2);
		Argentina.setFiltroBuscadorMejoresCM(fCompuesto);
		//b.agregarFiltro(fCompuesto);
		assertEquals(List.of(circuitoA, circuitoB),  Argentina.buscarMejoresRutas());
		
	}
	
	@Test
	void fecLlegadaCompuestoVacio() {
		And = new And();
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(5, 0)), Brasil);
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		Argentina.setFiltroBuscadorMejoresCM(fCompuesto);
		//b.agregarFiltro(fCompuesto);
		assertEquals(List.of(), Argentina.buscarMejoresRutas());
		
	}
	
}
