package recorridosTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import circuitos.*;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

public class TramoTest {
	
	UbicacionGeografica u1, u2;
	Terminal t1, t2;
	Tramo tA;
	
	@BeforeEach
	public void setup() {
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		
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
