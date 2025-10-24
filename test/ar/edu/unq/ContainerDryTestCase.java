package ar.edu.unq;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import container.ContainerTanque;

class ContainerDryTestCase {
	
	private ContainerTanque container1;
	
	@Test
	void testContainerTanqueCreación() {
		
		container1 = new ContainerTanque("azul1234567", 26d, 22d, 20d);
		
		assertEquals("azul1234567", container1.getId());
		assertEquals(26d, container1.getAncho());
		assertEquals(22d, container1.getLargo());
		assertEquals(20d, container1.getAltura());
	}

}
