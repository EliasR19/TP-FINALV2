import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;

import buque.Buque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;
import buscadorMejorCircuito.*;

public class main {

	public static void main(String[] args) {
		
		Terminal Argentina;
		Terminal Brasil ;
		Terminal España ;
		Terminal China;
		
		Naviera lineaA ;
		CircuitoMaritimo circuitoA ;
		CircuitoMaritimo circuitoB ;
		
		Buque bA;
		Buque bB ;



			Argentina = new Terminal("Argentina", new UbicacionGeografica(19,20));
			Brasil = new Terminal("Brasil", new UbicacionGeografica(1,4));
			España = new Terminal("España", new UbicacionGeografica(500,320));
			China = new Terminal("China", new UbicacionGeografica(1400, 500));
			
			lineaA = new Naviera();
			
			circuitoA = new CircuitoMaritimo(Argentina, España);
			circuitoB = new CircuitoMaritimo(Argentina, China);
			
			// Circuito A = [Argentina, Brasil, España]
			circuitoA.agregarTramo(Argentina, Brasil, 4);
			circuitoA.agregarTramo(Brasil, España, 20);
			circuitoA.agregarTramo(España, Argentina, 22.3d);
			
			//Circuito B = [Argentina, España, China]
			circuitoB.agregarTramo(Argentina, España, 4);
			circuitoB.agregarTramo(España, China, 30d);
			circuitoB.agregarTramo(China,Argentina, 55d);
			
			
			
			bA = new Buque();
			bB = new Buque();
			
			lineaA.agregarCircuitoMaritimo(circuitoA);
			lineaA.agregarCircuitoMaritimo(circuitoB);
			lineaA.agregarBuque(bA);
			lineaA.agregarBuque(bB);
		
			
			Argentina.agregarLiena(lineaA);
			
			
			//System.out.println(lineaA.duracionEntre(Argentina, Brasil) + " Horas");
			
			
			System.out.println(circuitoA.tiempoRecorridoEntre(Argentina, España));
			System.out.println(circuitoB.tiempoRecorridoEntre(Argentina, España));

			
			
			
			BuscadorMejorC mejor = new MenorTiempoRecorrido();
			Argentina.setMejorBuscador(mejor);
			
			for(CircuitoMaritimo c : Argentina.getCircuitos()) {
				System.out.println(c.getTerminalOrigen().getNombre() + " - " + c.getTerminalDestino().getNombre() + " | Duracion: " + 
						c.tiempoRecorridoEntre(c.getTerminalOrigen(),c.getTerminalDestino())
						);
			}
			
			System.out.println(Argentina.buscarMejorC(España).equals(circuitoB));
			
			/*
			 * Circuito A = [Argentina, Brasil, España]
			 * Circuito B = [Argentina, España, China]
			 * */
		
		
	}

}
