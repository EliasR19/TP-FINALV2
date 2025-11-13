package clientesTest;

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
import container.ContainerDry;
import container.ContainerReefer;
import container.ContainerTanque;
import empresasTransportistas.*;
import servicios.ServicioDesconsolidado;
import servicios.ServicioElectricidad;
import servicios.ServicioLavado;
import servicios.ServicioPesado;
import terminal.OrdenImp;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

class ConsigneeTestCase {

	private ServicioLavado servicioLavado;
	private ServicioElectricidad servicioElectricidad;
	private ServicioPesado servicioPesado;
	private ServicioDesconsolidado servicioDesconsolidado;
	private UbicacionGeografica u1;
	private Terminal terminal;
	private Consignee consignee;
	private Container carga;
	private BL bl;
	private OrdenImp ordenImp;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	
	@BeforeEach
	void setUp() throws Exception {
		u1 = new UbicacionGeografica(-23, -25);
		terminal = new Terminal("Argentina", u1);
		consignee = new Consignee("Marcos");
		buque = new Buque();
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
		
		servicioLavado = new ServicioLavado(100d);
		servicioElectricidad = new ServicioElectricidad(100d, null, null); // Se deberia setear para
																		   // cada caso del buque
		servicioPesado = new ServicioPesado(5000d);
		servicioDesconsolidado = new ServicioDesconsolidado(3000d);
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		terminal.agregarServicio(servicioLavado);
		terminal.agregarServicio(servicioElectricidad);
		terminal.agregarServicio(servicioPesado);
		terminal.agregarServicio(servicioDesconsolidado);
		
		
		ordenImp = terminal.generarOrdenImp(consignee, carga, buque, camion, chofer, turno, terminal.asignarServicios(carga));
	}
	
	@Test
	void testEnUnaOrdenSeGuardanLosServiciosQueTendraElContainer() {
		// Tomando en cuenta que el container es de tipo Tanque, tendra todos los servicios menos
		// el de Electricidad y BLEspecial
		assertTrue(ordenImp.tieneServivio(servicioLavado));
		assertTrue(ordenImp.tieneServivio(servicioPesado));
		assertFalse(ordenImp.tieneServivio(servicioElectricidad));
		assertFalse(ordenImp.tieneServivio(servicioDesconsolidado));
	}
	
	@Test
	void testImportarCarga() {

		consignee.importarCarga(ordenImp, turno);
		assertFalse(terminal.tieneContainer(carga));
		assertEquals(carga, camion.getCarga());
	}

}
