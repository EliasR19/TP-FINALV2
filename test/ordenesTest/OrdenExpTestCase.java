package ordenesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import clientes.*;
import container.*;
import empresasTransportistas.*;
import terminal.*;
import ubicacionGeografica.UbicacionGeografica;

class OrdenExpTestCase {
	
	private UbicacionGeografica ubicacion;
	private OrdenExp ordenExp;
	private Terminal terminal;
	private Shipper shipper;
	private ContainerTanque carga;
	private BL bl;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacion = new UbicacionGeografica(0,0);
		terminal = new Terminal("A", ubicacion);
		shipper = new Shipper("Dan");
		buque = new Buque();
		camion = new Camion();
		chofer = new Chofer("Maxi");
		
		bl = new BL();
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		carga = new ContainerTanque("azul1234567", 26d, 22d, 20d, bl);
		
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		
		ordenExp = new OrdenExp(terminal, shipper, carga, buque, camion, chofer, turno);
	}

	@Test
	void testGenerarOrdenDeExportación() {
		fail("Not yet implemented");
	}

}
