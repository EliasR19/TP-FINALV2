package recorridos.tramo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import circuitos.Tramo;
import naviera.*;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

public class CircuitoTest {
	
	UbicacionGeografica u1, u2, u3;
	Terminal t1, t2, t3;
	Tramo tA, tB, tC;
	
	CircuitoMaritimo cA;
	
	@BeforeEach
	public void setup() {
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		u3 = new UbicacionGeografica(-5, 2000);
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
