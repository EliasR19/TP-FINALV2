package serviciosTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.BLEspecial;
import container.Carga;
import container.ContainerDry;
import container.ContainerReefer;
import container.ContainerTanque;
import servicios.ServicioDesconsolidado;
import servicios.ServicioElectricidad;
import servicios.ServicioLavado;
import servicios.ServicioPesado;

class ServiciosContainersTestCase {
	
	private ContainerTanque tank;
	private ContainerDry dryComun;
	private ContainerDry dryEspecial;
	private ContainerReefer refeer;
	private BL bl;
	private BLEspecial blE;
	private LocalDateTime inicio, fin;
	private ServicioLavado servicioLavado;
	private ServicioElectricidad servicioElectricidad;
	private ServicioPesado servicioPesado;
	private ServicioDesconsolidado servicioDesconsolidado;
	

	@BeforeEach
	public void setUp() {
		

		
		inicio = LocalDateTime.of(2025, 11, 7, 8, 0);
        fin = LocalDateTime.of(2025, 11, 7, 10, 0);
		
		servicioLavado = new ServicioLavado();
		servicioElectricidad = new ServicioElectricidad(inicio, fin);
		servicioPesado = new ServicioPesado();
		//servicioDesconsolidado = new ServicioDesconsolidado(3000d);
		


		bl = mock(BL.class);
		when(bl.getPesoTotal()).thenReturn(4000d);
		blE = mock(BLEspecial.class);
		when(blE.esEspecial()).thenReturn(true);
        	
		
		tank = new ContainerTanque("azul1234567", "Tanque", 3d, 10d, 2.8d, bl);
		
		dryComun = new ContainerDry("azul8910112", "DryC", 2d, 10d, 2d, bl);
		
		dryEspecial = new ContainerDry("azul4982645", "DryE", 5d, 5d, 2.1d, blE);
		
		refeer = new ContainerReefer("azul5555555", "Reefer", 7d, 25d, 3d, bl, 20d);
		
	}

	@Test
	void ServicioLavadoDosMedidas() {
		
		//Mayor o igual a 70mc
		assertEquals(84d,tank.capacidad());
		tank.darServicio(servicioLavado);
		assertEquals(195d, tank.precioFinal());
		
		//Menor a 70mc
		assertEquals(40d, dryComun.capacidad());
		
		dryComun.darServicio(servicioLavado);
		assertEquals(130, dryComun.precioFinal());
		
	}
	
	
	@Test
	void ServicioElectricidad() {
		refeer.darServicio(servicioElectricidad);
		assertEquals(2, servicioElectricidad.horasTotal());
		assertEquals(100d, refeer.precioFinal());
		
	}

	
	@Test
	void ServicioDePesado() {
		tank.darServicio(servicioPesado);
		dryEspecial.darServicio(servicioPesado);
		dryComun.darServicio(servicioPesado);
		
		assertEquals(100d, tank.precioFinal());
		assertEquals(100d, dryComun.precioFinal());
		assertEquals(300d, dryEspecial.precioFinal());

	}
	
	@Test
	void ServicioPesadoYRegistroDePeso() {
		tank.darServicio(servicioPesado);
		
		assertEquals(100d, tank.precioFinal());
		assertEquals(4000, tank.getPesoRegistrado());
	}
	
	@Test
	void ServicioDescolidado() {
		//Ya lo tiene porque se le agrega en el cosnstructor cuando el bl es Especial
		assertEquals(200d, dryEspecial.precioFinal());
	}
	



}
