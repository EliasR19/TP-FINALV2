package clientesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clientes.Shipper;
import container.BL;
import container.Container;
import container.ContainerTanque;
import terminal.Terminal;

class ShipperTestCase {

	private Terminal origen, destino;
	private Shipper shipper;
	private Container carga;
	private BL bl;
	
	@BeforeEach
	void setUp() throws Exception {
		origen = new Terminal("A");
		destino = new Terminal("B");
		shipper = new Shipper();
		
		// se crea un BL 
		bl = new BL();
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		carga = new ContainerTanque("azul1234567", 26d, 22d, 20d, bl);
	}

	@Test
	void test() {
		;
	}

}
