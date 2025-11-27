package containersTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.*;

class ContainerDryTestCase {
	
	private ContainerDry containerD;
	private ContainerDry container;
	private BLEspecial blEspecial;
	private BL bl1;
	private BL bl2;
	private Carga carga1, carga2, carga3;
	
	@BeforeEach
	public void setUp() {
		carga1 = new Carga("Agua", 500d);
		carga2 = new Carga("Aceite de Oliva", 100d);
		carga3 = new Carga("Gasolina", 400d);
				
		bl1 = new BL();
		bl1.enlistar(carga1);
		bl1.enlistar(carga2);
		bl1.enlistar(carga3);
		
		bl2 = new BL();
		bl2.enlistar(carga1);
		bl2.enlistar(carga2);
		bl2.enlistar(carga1);
		
		blEspecial = new BLEspecial();
		blEspecial.agregarBL(bl1);
		blEspecial.agregarBL(bl2);
		
		container = new ContainerDry("azul1234567", "Dry", 26d, 22d, 20d, bl1);
		
		containerD = new ContainerDry("azul1234567", "Dry", 26d, 22d, 20d, blEspecial);
		
	}
	
	@Test
	void testContainerDryCreación() {
		
		assertEquals("azul1234567", containerD.getId());
		assertEquals(26d, containerD.getAncho());
		assertEquals(22d, containerD.getLargo());
		assertEquals(20d, containerD.getAltura());
		assertEquals(blEspecial, containerD.getBl());
	}
	
	@Test
	void testContainerDryPesoTotal() {
		
		assertEquals(1000d, container.getPesoTotal());
		
	}
	
	@Test
	void testGetCargaBL() {
		List<Carga> cargas = new ArrayList<Carga>();
		cargas.add(carga1);
		cargas.add(carga2);
		cargas.add(carga3);
		assertEquals(bl1.getCarga(), cargas);
	}
	
	@Test
	void testGetCargaBLEspecial() {
		List<Carga> cargas = new ArrayList<Carga>();
		cargas.add(carga1);
		cargas.add(carga2);
		cargas.add(carga3);
		assertTrue(blEspecial.getCarga().containsAll(cargas));
	}
	
	@Test
	void testContainerDryDesconsolidadoPesoTotal() {
		
		assertEquals(2100d, containerD.getPesoTotal());
		
	}
	
	@Test
	void cargaNombre() {
		assertEquals("Agua", carga1.getNombre());
	}

}
