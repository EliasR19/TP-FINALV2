package empresasTransportistasTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import empresasTransportistas.EmpresaTransportista;


class EmpresaTransportistaTestCase {

	private EmpresaTransportista empresaT;
	private Camion camion;
	private Chofer chofer;
	
	private ContainerTanque c;
	
	@Test
	void testUnaEmpresaTransportistaTieneRegistradosALosCamionesYChofereAutorizados() {
		
		empresaT = new EmpresaTransportista();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		
		c = new ContainerTanque(null, null, 0, 0, 0, null);
		camion.setCarga(c);
		
		empresaT.registraCamion(camion);
		empresaT.registraChofer(chofer);
		
		assertTrue(empresaT.estaRegistradoCamion(camion));
		assertTrue(empresaT.estaRegistradoChofer(chofer));
		
		assertTrue(!empresaT.getCamiones().isEmpty());
		assertTrue(!empresaT.getChoferes().isEmpty());
		
		assertEquals("Maxi", empresaT.getChoferes().getFirst().getNombre());
		camion.descargar();
		assertEquals(null, camion.getCarga());
	}

}
