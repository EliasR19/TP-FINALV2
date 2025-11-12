<<<<<<< HEAD
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Circuitos.Tramo;
import Circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;
import buque.Buque;
import buscador.*;

public class main {

	public static void main(String[] args) {
		Terminal Argentina = new Terminal("Argentina", new UbicacionGeografica(0, 0));
		Terminal Brasil = new Terminal("Brasil", new UbicacionGeografica(0, 0));
		Terminal España = new Terminal("España", new UbicacionGeografica(0, 0));
		Terminal China = new Terminal("China", new UbicacionGeografica(0, 0));
		
		Naviera lineaA = new Naviera();
		
		CircuitoMaritimo circuitoA = new CircuitoMaritimo(Argentina, España);
		lineaA.agregarCircuitoMaritimo(circuitoA);
		
		CircuitoMaritimo circuitoB = new CircuitoMaritimo(Argentina, China);
		lineaA.agregarCircuitoMaritimo(circuitoB);
		
		//Tramo z1 = new Tramo(Argentina, Brasil, 4);
		//Tramo z2 = new Tramo(Brasil, España, 20);
		//Tramo z3 = new Tramo(España, Argentina, 22.3d);
		circuitoA.agregarTramo(Argentina, Brasil, 4);
		circuitoA.agregarTramo(Brasil, España, 20);
		circuitoA.agregarTramo(España, Argentina, 22.3d);
		
		circuitoB.agregarTramo(Argentina, España, 4);
		circuitoB.agregarTramo(España, China, 30d);
		circuitoB.agregarTramo(China,Argentina, 55d);
		
		Buque bA = new Buque();
		Buque bB = new Buque();
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);
		
		
		//for(Terminal t : circuitoA.terminalesDelCircuito()) {
		//	System.out.println(t.getName());
		//}
		
		
		lineaA.asignarViaje(bA, circuitoA,LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		
		lineaA.showCronogramaBuque();
		
		
		//System.out.println("que" + circuitoA.tiempoRecorridoEntre(España, Argentina));
		System.out.println("Recorrido: ");
		for (Terminal tp : circuitoB.terminalesDelCircuito()) {
			System.out.println(tp.getNombre());
		}
		
	
		//A partir de los buques que tiene el circuito, y sus fechas de salida, determina el cronograma
		
		Argentina.agregarLiena(lineaA);
		
		
		
		//BUSCADOR
		Terminal Argelia = new Terminal("Argelia", new UbicacionGeografica(0, 0));
		
		System.out.println("Buscador");
		Operador Or = new Or();
		Operador And = new And();
		
		Filtro f1 = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		Filtro f2 = new PuertoDestino(Brasil);
		Filtro f3 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		
		Filtro fCompuesto1 = new FiltroCompuesto(And, f1, f3);
		Filtro fCompuesto2 = new FiltroCompuesto(Or, fCompuesto1, f2);
		
		
		Buscador b = new Buscador(Argentina);
		And = new And();
		Filtro fSimple = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		Filtro fSimple2 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,3), LocalTime.of(9, 0)), China);
		
		Filtro fCompuesto = new FiltroCompuesto(And, fSimple, fSimple2);
		
		b.agregarFiltro(fSimple);
		
		for (List<Tramo> tList : b.buscar()) {
			tList.forEach(t -> System.out.print("Origen :" + t.getOrigen().getNombre() + " |Destino :" + t.getDestino().getNombre() + " - "));
			System.out.println();
		}
=======
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

			
			//Argentina.asignarViaje(bB, circuitoA);
			lineaA.asignarViaje(bA, circuitoA, LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
			lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
			
			//lineaA.showCronogramaBuque();
			
			Argentina.agregarLiena(lineaA);
			
			
			System.out.println("total" + circuitoB.terminalesEntre(Argentina, China));

			
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
		
>>>>>>> refs/heads/Mejor_Circuito
		
	}

}
