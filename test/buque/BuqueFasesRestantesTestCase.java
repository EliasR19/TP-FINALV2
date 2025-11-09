package buque;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		n1.agregarCircuitoMaritimo(circuitoA);
		n1.agregarBuque(buque);
		n1.asignarViaje(buque, circuitoA, fechaSalida);
		
		for (int i = 0; i < 7609 ; i++) { // El buque debe viajar 7609 minutos para llegar al destino
			buque.getGPS().actualizarPosicionPorUnMinuto();
		}
	}
	
	@Test
	void testUnBuquePasaALaFaseWorkingMedianteLaTerminal() {
		t2.darOrdenDeInicio(buque);
	}
	
	@Test
	void testUnBuqueTieneUnGPSQueLeDiceSuPosicion() {
		
	}
	
	@Test
	void testUnBuqueAvanzaYElGPSCuandoPasaUnMinutoLeDiceSuPosicionYSabeSuFaseActual() {
		
	}
	
	@Test
	void testUnBuqueCuandoNoEstaA50MetrosOMenosDeLaTerminalAunNoPasaALaSiguienteFase() {
		
	}
	
	@Test
	void testUnBuqueCuandoEstaA50MetrosOMenosDeLaTerminalPasaALaSiguienteFase() {
		
	}
	
	@Test
	void testUnBuqueLlegaAlDestino() {
		
	}

}