package cronograma;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import circuitos.Cronograma;
import terminal.Terminal;

class CronogramaTestCase {
	
	private Cronograma cronograma;
	private Terminal t1, t2;
	private LocalDateTime salida, llegada;
	
	@BeforeEach
	void setUp() throws Exception {
		t1 = mock(Terminal.class);
		t2 = mock(Terminal.class);
		salida = mock(LocalDateTime.class);
		llegada = mock(LocalDateTime.class);
		cronograma = new Cronograma(t1, t2, salida, llegada);
	}

	@Test
	void testConstructorCronograma() {
		assertEquals(t1, cronograma.getOrigen());
		assertEquals(t2, cronograma.getDestino());
		assertEquals(salida, cronograma.getSalida());
		assertEquals(llegada, cronograma.getLlegada());
		assertFalse(cronograma.getLlegoADestino());
	}

	@Test
	void testConfirmarLlegada() {
		cronograma.confirmarLlegada();
		assertTrue(cronograma.getLlegoADestino());
	}
}
