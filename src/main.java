import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Circuitos.Buque;
import Circuitos.CircuitoMaritimo;
import Circuitos.LineaNaviera;
import Circuitos.TerminalPrueba;
import Circuitos.Tramo;

public class main {

	public static void main(String[] args) {
		TerminalPrueba Argentina = new TerminalPrueba("Argentina");
		TerminalPrueba Brasil = new TerminalPrueba("Brasil");
		TerminalPrueba España = new TerminalPrueba("España");
		
		LineaNaviera lineaA = new LineaNaviera();
		
		CircuitoMaritimo circuitoA = new CircuitoMaritimo(Argentina, España);
		lineaA.agregarCircuito(circuitoA);
		
		//Tramo z1 = new Tramo(Argentina, Brasil, 4);
		//Tramo z2 = new Tramo(Brasil, España, 20);
		//Tramo z3 = new Tramo(España, Argentina, 22.3d);
		circuitoA.agregarTramo(Argentina, Brasil, 4);
		circuitoA.agregarTramo(Brasil, España, 20);
		circuitoA.agregarTramo(España, Argentina, 22.3d);
		
		
		Buque bA = new Buque();
		Buque bB = new Buque();
		lineaA.agregarBuque(bA);
		//lineaA.agregarBuque(bB);
		
		
		for(TerminalPrueba t : circuitoA.terminalesDelCircuito()) {
			System.out.println(t.getName());
		}
		
		
		lineaA.salidaBuque(bA, circuitoA, LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
		//lineaA.salidaBuque(bB, circuitoA, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
		
		System.out.println(bA.getFecSalida());
		
		//Argentina.asignarViaje(bB, circuitoA);
		lineaA.asignarViaje(bA, circuitoA);
		
		lineaA.recorridos();
		System.out.println(lineaA.duracionEntre(Argentina, Brasil) + " Horas");
		
		/*
		System.out.println(circuitoA.tiempoRecorridoEntre(España, Argentina));
		for (TerminalPrueba tp : circuitoA.terminalesDelCircuito()) {
			System.out.println(tp.getName());
		}
		*/
		
		
		//A partir de los buques que tiene el circuito, y sus fechas de salida, determina el cronograma
		
		
		
	}

}
