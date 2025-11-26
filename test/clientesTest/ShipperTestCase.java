package clientesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import clientes.Shipper;
import container.*;
import empresasTransportistas.*;
import naviera.CircuitoMaritimo;
import terminal.*;
import ubicacionGeografica.GPS;

class ShipperTestCase {

	private GPS ubicacionTerminal1, ubicacionTerminal2, gpsBuque;
	private Terminal terminal1, terminal2;
	private Shipper shipper;
	private Container carga;
	private BL bl;
	private OrdenExp ordenExp;
	private Buque buque;
	private Camion camion;
	private Chofer chofer;
	private LocalDateTime turno, fecInicio;
	private CircuitoMaritimo circuito;
	private Viaje viaje;
	private Carga carga1, carga2, carga3;
	
	@BeforeEach
	void setUp() throws Exception {
		ubicacionTerminal1 = new GPS(200, 100);
		ubicacionTerminal2 = new GPS(300, 200);
		terminal1 = new Terminal("A", ubicacionTerminal1);
		terminal2 = new Terminal("B", ubicacionTerminal2);

		shipper = new Shipper("Marcos");
		
		circuito = new CircuitoMaritimo(terminal1, terminal2);
		circuito.agregarTramo(terminal1, terminal2, 120);
		circuito.agregarTramo(terminal2, terminal1, 120);
		fecInicio = LocalDateTime.of(LocalDate.of(2025,11,1), LocalTime.of(23, 0));
		viaje = new Viaje(fecInicio, terminal1, circuito);
		gpsBuque = new GPS(250, 150);
		buque = new Buque(viaje, gpsBuque);
		camion = new Camion();
		chofer = new Chofer("Maxi");
		camion.setChofer(chofer);
		camion.setCarga(carga);
		turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
		
		// se crea un BL 
		bl = new BL();
		
		carga1 = new Carga("Agua", 500d);
		carga2 = new Carga("Aceite de Oliva", 100d);
		carga3 = new Carga("Gasolina", 400d);
				
		bl = new BL();
		bl.enlistar(carga1);
		bl.enlistar(carga2);
		bl.enlistar(carga3);
		
		
		carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
		
	}

	@Test
	void testExportarCarga() {
		ordenExp = terminal1.generarOrdenExp(shipper, carga, buque, camion, chofer, turno);
		shipper.exportarCarga(ordenExp, turno);
		assertTrue(terminal1.tieneContainer(carga));
		assertEquals(null, camion.getCarga());
	}

}
