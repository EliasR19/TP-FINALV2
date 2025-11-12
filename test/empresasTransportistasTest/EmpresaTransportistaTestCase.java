package empresasTransportistasTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import empresasTransportistas.EmpresaTransportista;


class EmpresaTransportistaTestCase {

	private EmpresaTransportista empresaT;
	private Camion camion;
	private Chofer chofer;
	
	@Test
	void testUnaEmpresaTransportistaTieneRegistradosALosCamionesYChofereAutorizados() {
		
		empresaT = new EmpresaTransportista();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		
		empresaT.registraCamion(camion);
		empresaT.registraChofer(chofer);
		
		assertTrue(empresaT.estaRegistradoCamion(camion));
		assertTrue(empresaT.estaRegistradoChofer(chofer));

	}

}
