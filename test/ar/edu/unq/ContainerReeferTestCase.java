package ar.edu.unq;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.ContainerReefer;

class ContainerReeferTestCase {
	
	private ContainerReefer container;
	private BL bl;
	
	@BeforeEach
	public void setUp() {
		bl = new BL();
		
		bl.enlistar("Hielo", 500d);
		bl.enlistar("Fruta Congelada", 100d);
		bl.enlistar("Pescado", 400d);
		
		container = new ContainerReefer("azul1234567", 26d, 22d, 20d, bl);
		
	}
	
	@Test
	void testContainerReeferCreación() {
		
		assertEquals("azul1234567", container.getId());
		assertEquals(26d, container.getAncho());
		assertEquals(22d, container.getLargo());
		assertEquals(20d, container.getAltura());
	}
	
	@Test
	void testContainerReeferPesoTotal() {
		
		assertEquals(1000d, container.getPesoTotal());
		
		
	}
}
