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

public class FecSalidaTest {
	
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
		
		vA = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)),Argentina, circuitoA);
		vB = new Viaje(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)),Argentina, circuitoB);
		
		bA = new Buque(vA);
		bB = new Buque(vB);

	

	

		lineaA.agregarCircuitoMaritimo(circuitoA);
		lineaA.agregarCircuitoMaritimo(circuitoB);
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);
		
		//lineaA.asignarViaje(bA, circuitoA,LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		//lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		
		Argentina.agregarLiena(lineaA);
		
		b = new Buscador(Argentina);
		
		/*
		 * Circuito A = [Argentina, Brasil, España]
		 * Circuito B = [Argentina, España, China]
		 * */
	}
	
	
	@Test
	public void fecLlegadaSimple() {
		fSimple = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		Argentina.setFiltroBuscadorMejoresCM(fSimple);
		//b.agregarFiltro(fSimple);
		assertEquals(List.of(circuitoB), Argentina.buscarMejoresRutas());
		
	}
	
	@Test
	void fecLlegadaCompuestoAnd() {
		And = new And();
		fSimple = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		fSimple2 = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		//b.agregarFiltro(fCompuesto);
		Argentina.setFiltroBuscadorMejoresCM(fCompuesto);
		//Un circuito No puede tener dos fechas de salidas
		assertEquals(List.of(), Argentina.buscarMejoresRutas());
		
	}
	
	@Test
	void fecLlegadaCompuestoOr() {
		Or = new Or();
		fSimple = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		fSimple2 = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		
		fCompuesto = new FiltroCompuesto(Or, fSimple, fSimple2);
		
		//b.agregarFiltro(fCompuesto);
		Argentina.setFiltroBuscadorMejoresCM(fCompuesto);
		assertEquals(List.of(circuitoA, circuitoB), Argentina.buscarMejoresRutas());
		
	}
	
	@Test
	void fecLlegadaCompuestoVacio() {
		And = new And();
		fSimple = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		fSimple2 = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		
		fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		//b.agregarFiltro(fCompuesto);
		Argentina.setFiltroBuscadorMejoresCM(fCompuesto);
		assertEquals(List.of(), Argentina.buscarMejoresRutas());
		
	}
	

}
