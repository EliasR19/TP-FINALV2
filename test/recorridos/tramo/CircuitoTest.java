package recorridos.tramo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Circuitos.CircuitoMaritimo;
import Circuitos.TerminalPrueba;
import Circuitos.Tramo;

public class CircuitoTest {
	
	
	TerminalPrueba t1;
	TerminalPrueba t2;
	TerminalPrueba t3;
	Tramo tA;
	Tramo tB;
	Tramo tC;
	
	CircuitoMaritimo cA;
	
	@BeforeEach
	public void setup() {
		t1 = new TerminalPrueba("Terminal 1");
		t2 = new TerminalPrueba("Terminal 2");
		t3 = new TerminalPrueba("Terminal 3");
		
		//tA = new Tramo(t1, t2, 10d);
		//tB = new Tramo(t2, t3, 30d);
		//tC = new Tramo(t3, t1, 45d);
		
		
		cA = new CircuitoMaritimo(t1, t3);
		
		
		cA.agregarTramo(t1, t2, 10d);
		cA.agregarTramo(t2, t3, 30d);
		cA.agregarTramo(t3, t1, 45d);
		
	}
	
	
	@Test
	public void TestTerminales() {
		assertEquals(Arrays.asList(t1,t2,t3), cA.terminalesDelCircuito());
	}
	
	@Test
	public void TestContieneTerminal() {
		assertTrue(cA.contiene(t1, t3));
	}
	
	
	@Test
	public void TestDuracionEntreDosTerminales() {
		assertEquals(40d, cA.tiempoRecorridoEntre(t1, t3));
	}
	
}
