

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
		
		 ContainerTanque containerT;
		 ContainerDry containerDS;
		 ContainerDry containerDD;
		 ContainerReefer containerR;
		 BL bl;
		 BLEspecial blE;
		 LocalDateTime inicio, fin;
		 ServicioLavado servicioLavado;
		 ServicioElectricidad servicioElectricidad;
		 ServicioPesado servicioPesado;
		 ServicioDesconsolidado servicioDesconsolidado;
		
		Carga dummyCarga;
		Carga c1;

			
			bl = new BL();
			blE = new BLEspecial();
			
			inicio = LocalDateTime.of(2025, 11, 7, 8, 0);
	        fin = LocalDateTime.of(2025, 11, 7, 10, 0);
			
			servicioLavado = new ServicioLavado();
			servicioElectricidad = new ServicioElectricidad(inicio, fin);
			servicioPesado = new ServicioPesado();
			//servicioDesconsolidado = new ServicioDesconsolidado(3000d);
			
	       // dummyCarga = mock(Carga.class);
	        //when(dummyCarga.getPeso()).thenReturn(300d);
	        
	        c1 = new Carga("A", 300d);
	        
			//bl.agregarCarga(dummyCarga);
			bl.agregarCarga(c1);
	        
			//blE.agregarBL(bl);
			//blE.agregarBL(bl);
			
			containerT = new ContainerTanque("azul1234567", "Tanque", 2.5d, 10d, 2.8d, bl);
			
			containerDS = new ContainerDry("azul8910112", "Dry", 2.5d, 10d, 2.9d, bl);
			
			containerDD = new ContainerDry("azul4982645", "Dry", 5d, 5d, 2.1d, blE);
			
			containerR = new ContainerReefer("azul5555555", "Reefer", 7d, 25d, 3d, bl, 20d);
			

			
			//Mayor o igual a 70mc
			System.out.println(containerT.capacidad());
			containerT.darServicio(servicioLavado);
			System.out.println(containerT.precioFinal());
			
			//Menor a 70mc
			System.out.println(containerDD.capacidad());
			
			containerDD.darServicio(servicioLavado);
			System.out.println( containerDD.precioFinal());
		

	}

}
