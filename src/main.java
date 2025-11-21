

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import buque.Buque;
import buscador.Buscador;
import buscador.Filtro;
import buscador.Operador;
import buscador.PuertoDestino;
import circuitos.Cronograma;
import circuitos.Viaje;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import ubicacionGeografica.UbicacionGeografica;

public class main {

	public static void main(String[] args) {
		
		Terminal Argentina;
		Terminal Brasil ;
		Terminal España ;
		Terminal China;
		
		Naviera lineaA ;
		CircuitoMaritimo circuitoA ;
		CircuitoMaritimo circuitoB ;

		Viaje vA, vB;
		
		Buque bA;
		Buque bB ;

		Operador Or;
		Operador And ;
		
		Filtro fSimple;
		Filtro fSimple2;
		Filtro fCompuesto;

		
		Buscador b;

			Argentina = new Terminal("Argentina", new UbicacionGeografica(0, 0));
			Brasil = new Terminal("Brasil", new UbicacionGeografica(0, 0));
			España = new Terminal("España", new UbicacionGeografica(0, 0));
			China = new Terminal("China", new UbicacionGeografica(0, 0));
			
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
			
			vA = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)),Argentina, circuitoA);
			vB = new Viaje(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)),Argentina, circuitoB);
			
			bA = new Buque(vA);
			bB = new Buque(vB);

		

		

			lineaA.agregarCircuitoMaritimo(circuitoA);
			lineaA.agregarCircuitoMaritimo(circuitoB);
			lineaA.agregarBuque(bA);
			lineaA.agregarBuque(bB);
		
			
			Argentina.agregarLiena(lineaA);
			
			/*
			 * Circuito A = [Argentina, Brasil, España]
			 * Circuito B = [Argentina, España, China]
			 * */

			//b = new Buscador(Argentina);
			fSimple = new PuertoDestino(China);
			Argentina.setFiltroBuscadorMejoresCM(fSimple);
			//b.agregarFiltro(fSimple);
			
			//Argentina.getViajes().forEach(v -> System.out.println(v.getOrigenActual().getNombre() + " - " + v.getDestinoActual().getNombre()));
			//System.out.println(bA.getViaje().getOrigenActual().getNombre());
			
			//b.getViajes().forEach(v -> System.out.println("a" + v.getOrigenActual().getNombre() + " - " + v.getDestinoActual().getNombre()));
			
			//System.out.println(vB.tieneDestinoYLlegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España));
			

			for(CircuitoMaritimo cm : Argentina.buscarMejoresRutas()) {
				System.out.println("a" + cm.getOrigen().getNombre());
			}
			
			
			for(Cronograma c : vA.getCronograma()) {
				System.out.println(c.getOrigen().getNombre() + " | " + c.getSalida() + " -->" + c.getDestino().getNombre() + " | " + c.getLlegada());
			}
			for(Cronograma c : vB.getCronograma()) {
				System.out.println(c.getOrigen().getNombre() + " | " + c.getSalida() + " -->" + c.getDestino().getNombre() + " | " + c.getLlegada());
			}
			
			//System.out.println(vA.tieneDestino(Brasil));
			
			
		

	}

}
