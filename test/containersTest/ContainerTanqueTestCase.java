package containersTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.Carga;
import container.ContainerTanque;

class ContainerTanqueTestCase {
	
	private ContainerTanque container;
	private BL bl;
	private Carga carga1, carga2, carga3;
	
	@BeforeEach
	public void setUp() {
		carga1 = new Carga("Agua", 500d);
		carga2 = new Carga("Aceite de Oliva", 100d);
		carga3 = new Carga("Gasolina", 400d);
				
		bl = new BL();
		bl.enlistar(carga1);
		bl.enlistar(carga2);
		bl.enlistar(carga3);
		
		container = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		
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
