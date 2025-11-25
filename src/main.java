

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buque.Buque;
import buscador.Buscador;
import buscador.Filtro;
import buscador.Operador;
import buscador.PuertoDestino;
import circuitos.Cronograma;
import circuitos.Viaje;
import container.BL;
import container.BLEspecial;
import container.Carga;
import container.ContainerDry;
import container.ContainerReefer;
import container.ContainerTanque;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import servicios.ServicioDesconsolidado;
import servicios.ServicioElectricidad;
import servicios.ServicioLavado;
import servicios.ServicioPesado;
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
		


			Argentina = new Terminal("Argentina", new UbicacionGeografica(17, 22));
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
			
			//lineaA.asignarViaje(bA, circuitoA,LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
			//lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
			
			
			Argentina.agregarLiena(lineaA);
			
			//b = new Buscador(Argentina);
			
			/*
			 * Circuito A = [Argentina, Brasil, España]
			 * Circuito B = [Argentina, España, China]
			 * */
			double x = 23;
			double y = 30;
			
			System.out.println(bA.getFase());
			System.out.println(bA.getGPS().getLatitud());
			System.out.println(bA.getGPS().getLongitud());
			System.out.println(bA.getGPS().distanciaA(x,y));
			
			bA.getGPS().actualizarPosA(x, y);
			
			System.out.println(bA.getFase());
			System.out.println(bA.getGPS().getLatitud());
			System.out.println(bA.getGPS().getLongitud());
			System.out.println(bA.getGPS().distanciaA(x,y));
		

	}

}
