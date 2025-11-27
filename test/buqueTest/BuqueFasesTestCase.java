package buqueTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.*;
import circuitos.Viaje;
import terminal.Terminal;
import ubicacionGeografica.GPS;

class BuqueFasesTestCase {

    private Buque buque;
    private Viaje viajeMock;
    private GPS gpsMock;
    private Terminal terminal;

    @BeforeEach
    void setUp() {
        // 1. Mockeamos los colaboradores
        viajeMock = mock(Viaje.class);
        gpsMock = mock(GPS.class);
        terminal = mock(Terminal.class);
        
        // 2. Configuramos el viaje para que nos de una terminal destino
        when(viajeMock.getDestinoActual()).thenReturn(terminal);
        
        // 3. Creamos el buque con los mocks
        buque = new Buque(viajeMock, gpsMock);
    }

    @Test
    void testDeOutboundAInbound() {
        buque.setFase(new Outbound(buque)); 
        when(gpsMock.distanciaA(terminal.getUbicacion())).thenReturn(49.0);
        buque.actualizarPosicion();
        assertTrue(buque.estaEnFaseInbound());
        assertFalse(buque.estaEnFaseOutbound());
    }

    @Test
    void testDeInboundAArrived() {
        buque.setFase(new Inbound(buque));
        when(gpsMock.distanciaA(terminal.getUbicacion())).thenReturn(0.0);
        buque.actualizarPosicion();
        assertTrue(buque.estaEnFaseArrived());
        assertFalse(buque.estaEnFaseInbound());
    }

    @Test
    void testDeArrivedAWorking() {
        buque.setFase(new Arrived(buque));
        buque.iniciarFaseWorking(); 
        assertTrue(buque.estaEnFaseWorking());
        assertFalse(buque.estaEnFaseArrived());
    }

    @Test
    void testDeWorkingADeparting() {
        buque.setFase(new Working(buque));
        buque.llegoAlDestino(terminal);
		buque.partidaHabilitada(terminal); 
        assertTrue(buque.estaEnFaseDeparting());
        assertFalse(buque.estaEnFaseWorking());
    }

    @Test
    void testDeDepartingAOutbound() {
    	Terminal terminalDummy = mock(Terminal.class);
        when(viajeMock.getDestinoActual()).thenReturn(terminalDummy);

        buque.setFase(new Departing(buque));
        when(gpsMock.distanciaA(any())).thenReturn(1001.0);

        buque.actualizarPosicion();

        assertTrue(buque.estaEnFaseOutbound());
        assertFalse(buque.estaEnFaseDeparting());
    }
}