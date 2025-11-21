package clientesTest;


import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.Consignee;
import container.BL;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.*;
import terminal.OrdenImp;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

class ConsigneeTestCase {

	private UbicacionGeografica u1;
	private Terminal terminal;
	private Consignee consignee;
	private Container carga;
	private BL bl;
	private OrdenImp ordenImp;
	private Buque buqueDummy;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	
	@BeforeEach
	void setUp() throws Exception {
		u1 = new UbicacionGeografica(-23, -25);
		terminal = new Terminal("Argentina", u1);
		consignee = new Consignee("Marcos");
		buqueDummy = mock(Buque.class);
		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
		camion.setCarga(carga);
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		
		// se crea un BL 
		bl = new BL();
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		
	}
	
	@Test
	void testImportarCarga() {
		ordenImp = terminal.generarOrdenImp(consignee, carga, buqueDummy, camion, chofer, turno);
		consignee.importarCarga(ordenImp, turno);
		assertFalse(terminal.tieneContainer(carga));
		assertEquals(carga, camion.getCarga());
	} 

}
