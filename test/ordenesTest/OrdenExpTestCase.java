package ordenesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.Cliente;
import container.Container;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import terminal.Terminal;

class OrdenExpTestCase {
	
	private Terminal terminal;
	private Cliente cliente;
	private Container carga;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
