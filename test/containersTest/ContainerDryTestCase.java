package containersTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.*;

class ContainerDryTestCase {
	
	private ContainerDry containerD;
	private ContainerDry container;
	private BLEspecial blEspecial;
	private BL bl1;
	private BL bl2;
	
	@BeforeEach
	public void setUp() {
		bl1 = new BL();
		
		bl1.enlistar("Agua", 500d);
		bl1.enlistar("Aceite de Oliva", 100d);
		bl1.enlistar("Gasolina", 400d);
		
		bl2 = new BL();
		
		bl2.enlistar("Agua", 500d);
		bl2.enlistar("Aceite de Oliva", 100d);
		bl2.enlistar("Gasolina", 400d);
		
		blEspecial = new BLEspecial();
		blEspecial.agregarBL(bl1);
		blEspecial.agregarBL(bl2);
		
		container = new ContainerDry("azul1234567", 26d, 22d, 20d, bl1);
		
		containerD = new ContainerDry("azul1234567", 26d, 22d, 20d, blEspecial);
		
	}
	
	@Test
	void testContainerDryCreación() {
		
		assertEquals("azul1234567", containerD.getId());
		assertEquals(26d, containerD.getAncho());
		assertEquals(22d, containerD.getLargo());
		assertEquals(20d, containerD.getAltura());
	}
	
	@Test
	void testContainerDryPesoTotal() {
		
		assertEquals(1000d, container.getPesoTotal());
		
	}
	
	@Test
	void testContainerDryDesconsolidadoPesoTotal() {
		
		assertEquals(2000d, containerD.getPesoTotal());
		
	}

}
