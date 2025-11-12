package buscadorTest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buscador.*;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

public class PuertoDestinoTest {
	
	Terminal Argentina;
	Terminal Brasil ;
	Terminal España ;
	Terminal China;
	
	Naviera lineaA ;
	CircuitoMaritimo circuitoA ;
	CircuitoMaritimo circuitoB ;
	
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
		
		
		bA = new Buque();
		bB = new Buque();

	

		b = new Buscador(Argentina);
	
		
		

		lineaA.agregarCircuitoMaritimo(circuitoA);
		lineaA.agregarCircuitoMaritimo(circuitoB);
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);
		
		lineaA.salidaBuque(bA, circuitoA, LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		lineaA.salidaBuque(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));

		lineaA.asignarViaje(bA, circuitoA);
		lineaA.asignarViaje(bB, circuitoB);
		//lineaA.armarCronograma();
		
		Argentina.agregarLiena(lineaA);
		
		
		/*
		 * Circuito A = [Argentina, Brasil, España]
		 * Circuito B = [Argentina, España, China]
		 * */
	}
	
	
	@Test
	public void PuertoDestinoSimple() {
		fSimple = new PuertoDestino(Brasil);
		b.agregarFiltro(fSimple);
		assertEquals(List.of(circuitoA.getTramos()), b.buscar());
		
	}
	
	@Test
	void PuertoDestinoCompuestoAnd() {
		And = new And();
		fSimple = new PuertoDestino(Brasil);
		fSimple2 = new PuertoDestino(España);
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		b.agregarFiltro(fCompuesto);
		assertEquals(List.of(circuitoA.getTramos()), b.buscar());
		
	}
	
	@Test
	void PuertoDestinoCompuestoOr() {
		Or = new Or();
		fSimple = new PuertoDestino(Brasil);
		fSimple2 = new PuertoDestino(China);
		
		fCompuesto = new FiltroCompuesto(Or, fSimple, fSimple2);
		
		b.agregarFiltro(fCompuesto);
		assertEquals(List.of(circuitoA.getTramos(), circuitoB.getTramos()), b.buscar());
		
	}
	
	@Test
	void PuertoDestinoCompuestoVacio() {
		And = new And();
		fSimple = new PuertoDestino(Brasil);
		fSimple2 = new PuertoDestino(China);
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		b.agregarFiltro(fCompuesto);
		assertEquals(List.of(), b.buscar());
		
	}
	
	
}