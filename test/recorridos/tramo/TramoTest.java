package recorridos.tramo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Circuitos.*;
import terminal.Terminal;

public class TramoTest {
	
	Terminal t1;
	Terminal t2;
	Tramo tA;
	
	@BeforeEach
	public void setup() {
		t1 = new Terminal("Terminal 1");
		t2 = new Terminal("Terminal 2");
		
		tA = new Tramo(t1, t2, 10d);
		
	}
	
	
	@Test
	public void TestTramoOrigen() {
		assertEquals(t1, tA.getOrigen());
	}
	
	@Test
	public void TestTramoDestino() {
		assertEquals(t2, tA.getDestino());
	}
	
	@Test
	public void TestTramoDuracion() {
		assertEquals(10, tA.getDuracion());
	}
	
}
