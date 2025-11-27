package recorridosTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import circuitos.Tramo;
import naviera.*;
import terminal.Terminal;
import ubicacionGeografica.GPS;

public class CircuitoTest {
	
	GPS u1, u2, u3;
	Terminal t1, t2, t3, t4;
	Tramo tA, tB, tC;
	
	CircuitoMaritimo cA;
	
	@BeforeEach
	public void setup() {
		u1 = new GPS(-23, -25);
		u2 = new GPS(-22.91, -43.17);
		u3 = new GPS(-5, 2000);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		t3 = new Terminal("Francia", u3);;
		
		//tA = new Tramo(t1, t2, 10d);
		//tB = new Tramo(t2, t3, 30d);
		//tC = new Tramo(t3, t1, 45d);
		
		
		cA = new CircuitoMaritimo(t1, t3);
		
		
		cA.agregarTramo(t1, t2, 10d);
		cA.agregarTramo(t2, t3, 30d);
		cA.agregarTramo(t3, t1, 45d);
		
	}
	
	
	@Test
	void testConstructorCircuitoMaritimo() {
		assertEquals(t1, cA.getOrigen());
		assertEquals(t3, cA.getDestino());
	}
	
	@Test
	public void testTerminales() {
		assertEquals(Arrays.asList(t1,t2,t3), cA.terminalesDelCircuito());
	}
	
	@Test
	public void testContieneTerminal() {
		assertTrue(cA.contiene(t1, t3));
	}
	
	
	@Test
	public void testDuracionEntreDosTerminales() {
		assertEquals(40d, cA.tiempoRecorridoEntre(t1, t3));
	}
	
	@Test
	public void testExcepcionTramoConOrigen() {
		t4 = new Terminal("Chile", u1);
		assertThrows(RuntimeException.class, () -> cA.tramoConOrigen(t4));
	}
	
}
