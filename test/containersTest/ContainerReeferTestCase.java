package containersTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.Carga;
import container.ContainerReefer;

class ContainerReeferTestCase {
	
	private ContainerReefer container;
	private BL bl;
	private Carga carga1, carga2, carga3;
	
	@BeforeEach
	public void setUp() {
		
		carga1 = new Carga("Hielo", 500d);
		carga2 = new Carga("Fruta Congelada", 100d);
		carga3 = new Carga("Pescado", 400d);
		
		bl = new BL();
		
		bl.enlistar(carga1);
		bl.enlistar(carga2);
		bl.enlistar(carga3);
		
		container = new ContainerReefer("azul1234567", "Reefer", 26d, 22d, 20d, bl, 20d);
		
	}
	
	@Test
	void testContainerReeferCreación() {
		
		assertEquals("azul1234567", container.getId());
		assertTrue(container.EsRefeer());
		assertEquals(26d, container.getAncho());
		assertEquals(22d, container.getLargo());
		assertEquals(20d, container.getAltura());
		assertEquals(bl, container.getBl());
		assertFalse(container.tieneBLEspecial());
		assertEquals(20d, container.getKwPoreHora());
	}
	
	@Test
	void testContainerReeferPesoTotal() {
		
		assertEquals(1000d, container.getPesoTotal());
		
		
	}
}
