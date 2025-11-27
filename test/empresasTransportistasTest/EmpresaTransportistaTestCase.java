package empresasTransportistasTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import empresasTransportistas.EmpresaTransportista;


class EmpresaTransportistaTestCase {

	private EmpresaTransportista empresaT;
	private Camion camion;
	private Chofer chofer;
	
	@BeforeEach
	public void setUp() {
		empresaT = new EmpresaTransportista();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
	}
	
	@Test
	void testUnaEmpresaTransportistaTieneRegistradosALosCamionesYChofereAutorizados() {
		
		empresaT.registraCamion(camion);
		empresaT.registraChofer(chofer);
		
		assertTrue(empresaT.estaRegistradoCamion(camion));
		assertTrue(empresaT.estaRegistradoChofer(chofer));
		assertEquals("Maxi", chofer.getNombre());

	}
	
	@Test
	void testDescargar() {
		camion.setCarga(mock(Container.class));
		camion.descargar();
		assertNull(camion.getCarga());
	}
	
	@Test
	void testGetters() {
		assertTrue(empresaT.getChoferes().isEmpty());
		assertTrue(empresaT.getCamiones().isEmpty());
	}
	
}
