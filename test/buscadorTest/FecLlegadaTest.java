package buscadorTest;

import static org.junit.Assert.assertEquals;

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
import ubicacionGeografica.UbicacionGeografica;

public class FecLlegadaTest {
	
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

	Operador Or;
	Operador And ;
	
	Filtro fSimple;
	Filtro fSimple2;
	Filtro fCompuesto;

	
	Buscador b;
	

	@BeforeEach
	public void setUp() {
		Argentina = new Terminal("Argentina", new UbicacionGeografica(0, 0));
		Brasil = new Terminal("Brasil", new UbicacionGeografica(0, 0));
		España = new Terminal("España", new UbicacionGeografica(0, 0));
		China = new Terminal("China", new UbicacionGeografica(0, 0));
		
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
		
		viajeA = new Viaje(LocalDateTime.of(2025, 10, 31, 1, 0), Argentina, circuitoA);
		viajeB = new Viaje(LocalDateTime.of(2025, 12, 1, 23, 0), Argentina, circuitoB);

		b = new Buscador(Argentina);
	
		lineaA.agregarCircuitoMaritimo(circuitoA);
		lineaA.agregarCircuitoMaritimo(circuitoB);

		bA = new Buque(viajeA);
		bB = new Buque(viajeB);
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);
		
		Argentina.agregarLiena(lineaA);
		
		
		
		/*
		 * Circuito A = [Argentina, Brasil, España]
		 * Circuito B = [Argentina, España, China]
		 * */
	}
	
	
	@Test
	public void fecLlegadaSimple() {
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		b.agregarFiltro(fSimple);
		assertEquals(List.of(circuitoB.getTramos()), b.buscar());
		
	}
	
	
	@Test
	void fecLlegadaCompuestoAnd() {
		And = new And();
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,3), LocalTime.of(9, 0)), China);
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		b.agregarFiltro(fCompuesto);
		assertEquals(List.of(circuitoB.getTramos()), b.buscar());
		
	}
	
	@Test
	void fecLlegadaCompuestoOr() {
		Or = new Or();
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(5, 0)), Brasil);
		
		fCompuesto = new FiltroCompuesto(Or, fSimple, fSimple2);
		
		b.agregarFiltro(fCompuesto);
		assertEquals(List.of(circuitoA.getTramos(), circuitoB.getTramos()), b.buscar());
		
	}
	
	@Test
	void fecLlegadaCompuestoVacio() {
		And = new And();
		fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(5, 0)), Brasil);
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		b.agregarFiltro(fCompuesto);
		assertEquals(List.of(), b.buscar());
		
	}
	
}
