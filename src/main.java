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
		
		CircuitoMaritimo circuitoA = new CircuitoMaritimo();
		lineaA.agregarCircuito(circuitoA);
		
		Tramo z1 = new Tramo(Argentina, Brasil, 4);
		Tramo z2 = new Tramo(Brasil, España, 20);
		Tramo z3 = new Tramo(España, Argentina, 22.3d);
		circuitoA.agregarTramo(z1);
		circuitoA.agregarTramo(z2);
		circuitoA.agregarTramo(z3);
		
		
		Buque bA = new Buque();
		Buque bB = new Buque();
		lineaA.agregarBuque(bA);
		lineaA.agregarBuque(bB);
		
		Brasil.asignarViaje(bB, LocalDateTime.of(LocalDate.now(), LocalTime.of(1, 0)), circuitoA);
		España.asignarViaje(bA, LocalDateTime.of(LocalDate.of(2025, 10, 30), LocalTime.of(15, 0)), circuitoA);
		
		lineaA.recorridos();
		
		
		/*
		System.out.println(circuitoA.tiempoRecorridoEntre(España, Argentina));
		for (TerminalPrueba tp : circuitoA.terminalesDelCircuito()) {
			System.out.println(tp.getName());
		}
		*/
		

		
		
		
	}

}
