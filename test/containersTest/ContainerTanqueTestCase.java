package containersTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.ContainerTanque;

class ContainerTanqueTestCase {
	
	private ContainerTanque container;
	private BL bl;
	
	@BeforeEach
	public void setUp() {
		bl = new BL();
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		container = new ContainerTanque("azul1234567", 26d, 22d, 20d, bl);
		
	}
	
	@Test
	void testContainerTanqueCreación() {
		
		assertEquals("azul1234567", container.getId());
		assertEquals(26d, container.getAncho());
		assertEquals(22d, container.getLargo());
		assertEquals(20d, container.getAltura());
	}
	
	@Test
	void testContainerTanquePesoTotal() {
		
		assertEquals(1000d, container.getPesoTotal());
		
		
	}

}
