package buque;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.BillOfLading;
import container.ContainerTanque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.*;

public class BuqueFasesRestantesTestCase {
	private UbicacionGeografica u1, u2;
	private Terminal t1, t2;
	private CircuitoMaritimo circuitoA;
	private LocalDateTime fechaSalida;
	private Buque buque;
	private Naviera n1;
	private BL bl1, bl2;
	private ContainerTanque container1, container2;

	@BeforeEach
	public void setUp() {
		u1 = new UbicacionGeografica(-23, -25);
		u2 = new UbicacionGeografica(-22.91, -43.17);
		t1 = new Terminal("Argentina", u1);
		t2 = new Terminal("Brasil", u2);
		buque = new Buque();
		n1 = new Naviera();
		circuitoA = new CircuitoMaritimo(t1, t2);
		fechaSalida = LocalDateTime.of(2025, 11, 8, 10, 0);
		circuitoA.agregarTramo(t1, t2, 10);
		circuitoA.agregarTramo(t2, t1, 10);
		n1.agregarCircuitoMaritimo(circuitoA);
		n1.agregarBuque(buque);
		n1.asignarViaje(buque, circuitoA, fechaSalida);
		
		bl1 = new BL();
		bl1.enlistar("Agua", 500d);
		bl1.enlistar("Aceite de Oliva", 100d);
		bl1.enlistar("Gasolina", 400d);
		
		bl2 = new BL();
		bl2.enlistar("Agua", 500d);
		bl2.enlistar("Aceite de Oliva", 100d);
		bl2.enlistar("Gasolina", 400d);
		
		container1 = new ContainerTanque("azul1234567", 26d, 22d, 20d, bl1);
		buque.subirCarga(container1);
		
		container2 = new ContainerTanque("azul1234568", 26d, 22d, 20d, bl2);
		t2.guardarContainer(container2);

		// Seteamos que el buque ya está en el destino
		buque.getGPS().setLatitud(-22.91);
		buque.getGPS().setLongitud(-43.17);
	}
	
	@Test
	void testUnBuqueTieneCargaDe() {
		assertTrue(buque.tieneCargaDe(container1));
		assertEquals(1, buque.cargaTotal());
	}
	
	
	@Test
	void testUnBuquePasaALaFaseWorkingMedianteLaTerminalSoloSiEstaEnLaFaseArrivedYRealizaLaDescargaYCarga() {
		buque.setFase(new Arrived()); // Seteamos que arrivó para que pueda pasar a la siguiente fase
		t2.darOrdenDeInicio(buque);
		
		assertTrue(buque.estaEnFaseWorking());

		assertTrue(buque.tieneCargaDe(container2));
		assertFalse(buque.tieneCargaDe(container1));
		

		assertTrue(t2.tieneContainer(container1));
		assertFalse(t2.tieneContainer(container2));
	}
	
	


}