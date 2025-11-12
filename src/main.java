import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import naviera.CircuitoMaritimo;
import naviera.Naviera;
import terminal.Terminal;
import buque.Buque;
import buscador.*;
import circuitos.Tramo;

public class main {

	public static void main(String[] args) {
		Terminal Argentina = new Terminal("Argentina");
		Terminal Brasil = new Terminal("Brasil");
		Terminal España = new Terminal("España");
		Terminal China = new Terminal("China");
		
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
		
		
		lineaA.salidaBuque(bA, circuitoA, LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		lineaA.salidaBuque(bB, circuitoA, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		//System.out.println(bA.getFecSalida());
		
		//Argentina.asignarViaje(bB, circuitoA);
		lineaA.asignarViaje(bA, circuitoA);
		lineaA.asignarViaje(bB, circuitoB);
		
		lineaA.recorridos();
		//System.out.println(lineaA.duracionEntre(Argentina, Brasil) + " Horas");
		
		/*
		System.out.println(circuitoA.tiempoRecorridoEntre(España, Argentina));
		for (TerminalPrueba tp : circuitoA.terminalesDelCircuito()) {
			System.out.println(tp.getName());
		}
		*/
		
		
		//A partir de los buques que tiene el circuito, y sus fechas de salida, determina el cronograma
		
		Argentina.agregarLiena(lineaA);
		
		
		
		//BUSCADOR
		Terminal Argelia = new Terminal("Argelia");
		
		System.out.println("Buscador");
		Operador Or = new Or();
		Operador And = new And();
		
		Filtro f1 = new FechaSalida(LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		Filtro f2 = new PuertoDestino(Brasil);
		Filtro f3 = new FechaLLegada(LocalDateTime.of(LocalDate.of(2025,12,2), LocalTime.of(3, 0)), España);
		
		Filtro fCompuesto1 = new FiltroCompuesto(And, f1, f3);
		Filtro fCompuesto2 = new FiltroCompuesto(Or, fCompuesto1, f2);
		
		
		Buscador b = new Buscador(Argentina);
		
		b.agregarFiltro(fCompuesto2);
		
		for (List<Tramo> tList : b.buscar()) {
			tList.forEach(t -> System.out.print("Origen :" + t.getOrigen().getName() + " |Destino :" + t.getDestino().getName() + " - "));
			System.out.println();
		}
		
	}

}
