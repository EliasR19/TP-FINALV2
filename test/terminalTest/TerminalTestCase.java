package terminalTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import circuitos.Viaje;
import clientes.*;
import container.BL;
import container.Carga;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import naviera.CircuitoMaritimo;
import terminal.*;
import ubicacionGeografica.GPS;
import ubicacionGeografica.GPS;
 
class TerminalTestCase {
	
	 private GPS ubicacionTerminal;
	    private Terminal Argentina, Brasil, España;
	    private Consignee consignee;
	    private Shipper shipper;
	    private Container carga;
	    private Buque buque;
	    private Camion camion;
	    private Chofer chofer;
	    private BL bl;
	    private LocalDateTime turno;
	    private Viaje viaje;
	    private CircuitoMaritimo circuitoA;
	    private GPS gps;
	    
	    
	        @BeforeEach
	        void setUp() throws Exception {
	        ubicacionTerminal = new GPS(200, 100);
	        Argentina = new Terminal("A", ubicacionTerminal);
	        Brasil = new Terminal("B", new GPS(4150, 255));
	        España = new Terminal("E", new GPS(25, 33));
	        shipper = new Shipper("Marcos");
	        
	        circuitoA = new CircuitoMaritimo(Argentina, España);
	        
	        circuitoA.agregarTramo(Argentina, Brasil, 4);
	        circuitoA.agregarTramo(Brasil, España, 20);
	        circuitoA.agregarTramo(España, Argentina, 22.3d);
	        
	        
	        viaje = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)),Argentina, circuitoA);

	        gps = new GPS(100, 200);
	        buque = new Buque(viaje, gps);

	        camion = new Camion();
	        chofer = new Chofer("Maxi");
	        turno = LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0));
	        
	        bl = new BL();
	        bl = mock(BL.class);
	        when(bl.getPesoTotal()).thenReturn(4000d);
	        
	        carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
	}
	
	@Test
	void testLaTerminalEstaUbicadaEn200X100Y() {
		GPS ubicacion = Argentina.getUbicacion();
		assertEquals(200, ubicacion.getLatitud());
		assertEquals(100, ubicacion.getLongitud());
	}


	@Test
	void testGenerarOrdenExp() {
		
		Argentina.generarOrdenExp(shipper, carga, buque, camion, chofer, turno);
		
		assertTrue(Argentina.tieneRegistradoSh(shipper));
		assertTrue(Argentina.tieneRegistradoElCamion(camion));
		assertTrue(Argentina.tieneRegistradoAlChofer(chofer));
		assertEquals(1, Argentina.cantidadDeOrdenesExp());
		
	}
	
	@Test 
	void testGenerarOrdenImp() { 
		
		Argentina.generarOrdenImp(consignee, carga, buque, camion, chofer, turno);
		
		assertTrue(Argentina.tieneRegistradoC(consignee));
		assertTrue(Argentina.tieneRegistradoElCamion(camion));
		assertTrue(Argentina.tieneRegistradoAlChofer(chofer));
		assertEquals(1, Argentina.cantidadDeOrdenesImp());
		
	}
	
}
