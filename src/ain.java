import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;

import buque.Buque;
import buque.Departing;
import buque.Outbound;
import buscador.Buscador;
import buscador.Filtro;
import buscador.Operador;
import circuitos.Viaje;
import clientes.Consignee;
import clientes.Shipper;
import container.BL;
import container.Container;
import container.ContainerTanque;
import empresasTransportistas.Camion;
import empresasTransportistas.Chofer;
import naviera.CircuitoMaritimo;
import naviera.Naviera;
import servicios.Servicio;
import servicios.ServicioElectricidad;
import terminal.OrdenExp;
import terminal.OrdenImp;
import terminal.Terminal;
import ubicacionGeografica.GPS;

public class ain {

	public static void main(String[] args) {

		Terminal Argentina;
		Terminal Brasil ;
		Terminal España ;
		Terminal China;
		
		Naviera lineaA ;
		CircuitoMaritimo circuitoA ;

		Viaje vA, vB;
		
		Buque bA;


		
		 Consignee consignee;
		 Container carga;
		 BL bl;
		 OrdenImp ordenImp;
		 Camion camion;
		 Chofer chofer;
		 LocalDateTime turno;
		

			Argentina = new Terminal("Argentina", new GPS(0, 0));
			Brasil = new Terminal("Brasil", new GPS(0, 0));
			España = new Terminal("España", new GPS(0, 0));
			China = new Terminal("China", new GPS(0, 0));
			
			lineaA = new Naviera();
			
			circuitoA = new CircuitoMaritimo(Argentina, España);

			
			// Circuito A = [Argentina, Brasil, España]
			circuitoA.agregarTramo(Argentina, Brasil, 4);
			circuitoA.agregarTramo(Brasil, España, 20);
			circuitoA.agregarTramo(España, Argentina, 22.3d);
			

			vA = new Viaje(LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)),Argentina, circuitoA);

			
			
			bA = new Buque(vA, new GPS(100d,200d));



			lineaA.agregarCircuitoMaritimo(circuitoA);

			lineaA.agregarBuque(bA);

			
			//lineaA.asignarViaje(bA, circuitoA,LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)));
			//lineaA.asignarViaje(bB, circuitoB, LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0)));
			
			
			Argentina.agregarLiena(lineaA);
			
			
			//b = new Buscador(Argentina);
			
			/*
			 * Circuito A = [Argentina, Brasil, España]
			 * Circuito B = [Argentina, España, China]
			 * */
			
			consignee = new Consignee("Marcos");
			camion = new Camion();
			chofer = new Chofer("Maxi");
			camion.setChofer(chofer);
			
			turno = LocalDateTime.of(LocalDate.of(2025,12,1), LocalTime.of(23, 0));
			bl = mock(BL.class);
			carga = new ContainerTanque("azul1234567", "Tanque", 26d, 22d, 20d, bl);
			
			bA.subirCarga(carga);
			
			ordenImp = Argentina.generarOrdenImp(consignee, carga, bA, camion, chofer, turno);
			consignee.importarCarga(ordenImp, turno);
			
			Shipper cliente = new Shipper("Marcos");
			OrdenExp orden = Argentina.generarOrdenExp(cliente, carga, bA, camion, chofer, turno);
			cliente.exportarCarga(orden, turno);
			
			
			bA.setFase(new Outbound(bA));
			//Argentina.cobrarServicios(bA);
			
			//System.out.println(Argentina.cantidadDeOrdenesImp());
			
//			System.out.println(Argentina.tieneRegistradoCliente(cliente));
//			System.out.println(bA.getDestinoActual().getNombre());
//			
//			System.out.println(bA.getGPS().distanciaA(Argentina.getUbicacion()));
//			System.out.println(bA.getFase());
//			System.out.println(bA.getGPS().getLatitud() + " | " + bA.getGPS().getLongitud());
//			bA.cambiarPosicion(40,20);
//			System.out.println(bA.getGPS().distanciaA(Argentina.getUbicacion()));
//			System.out.println(bA.getFase());
//			System.out.println(bA.getGPS().getLatitud() + " | " + bA.getGPS().getLongitud());
//		
//		
		//	Argentina.notificarArribo(bA);
		
	}

}
