package serviciosTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.BLEspecial;
import container.ContainerDry;
import container.ContainerReefer;
import container.ContainerTanque;
import servicios.ServicioDesconsolidado;
import servicios.ServicioElectricidad;
import servicios.ServicioLavado;
import servicios.ServicioPesado;

class ServiciosContainersTestCase {
	
	private ContainerTanque containerT;
	private ContainerDry containerDS;
	private ContainerDry containerDD;
	private ContainerReefer containerR;
	private BL bl;
	private BLEspecial blE;
	private LocalDateTime inicio, fin;
	private ServicioLavado servicioLavado;
	private ServicioElectricidad servicioElectricidad;
	private ServicioPesado servicioPesado;
	private ServicioDesconsolidado servicioDesconsolidado;
	
	@BeforeEach
	public void setUp() {
		
		bl = new BL();
		blE = new BLEspecial();
		
		inicio = LocalDateTime.of(2025, 11, 7, 8, 0);
        fin = LocalDateTime.of(2025, 11, 7, 10, 0);
		
		servicioLavado = new ServicioLavado(100d);
		servicioElectricidad = new ServicioElectricidad(100d, inicio, fin);
		servicioPesado = new ServicioPesado(5000d);
		servicioDesconsolidado = new ServicioDesconsolidado(3000d);
		
		bl.enlistar("Agua", 500d);
		bl.enlistar("Aceite de Oliva", 100d);
		bl.enlistar("Gasolina", 400d);
		
		blE.agregarBL(bl);
		blE.agregarBL(bl);
		
		containerT = new ContainerTanque("azul1234567", 2.5d, 10d, 2.8d, bl);
		
		containerDS = new ContainerDry("azul8910112", 2.5d, 10d, 2.9d, bl);
		
		containerDD = new ContainerDry("azul4982645", 5d, 5d, 2.1d, blE);
		
		containerR = new ContainerReefer("azul5555555", 7d, 25d, 3d, bl, 20d);
		
	}

	@Test
	void testElPrecioDeServicioPorLavadoEsElPrecioFijoPorqueNoSuperaLos70m3() {
		
		assertEquals(70d, containerT.capacidad());
		assertEquals(100d, servicioLavado.servicioPara(containerT));
		
		assertEquals(52.5d, containerDD.capacidad());
		assertEquals(100d, servicioLavado.servicioPara(containerDD));
		
	}
	
	@Test
	void testElPrecioDeServicioPorLavadoEsElPrecioAumentadoPorqueSuperaLos70m3() {
		
		assertEquals(72.5d, containerDS.capacidad());
		assertEquals(150d, servicioLavado.servicioPara(containerDS));
		
		assertEquals(525d, containerR.capacidad());
		assertEquals(150d, servicioLavado.servicioPara(containerR));
		
	}
	
	@Test
	void testUnContainerReeferRecibeElServicioElectricidadConElPrecioCorrespondiente() {
		
		assertEquals(200d, servicioElectricidad.servicioPara(containerR));
		
	}
	
	@Test
	void testUnContainerQueNoEsRefeerNoUsaElServicioElectricidad_SuPrecioEs0() {
		assertEquals(0d, servicioElectricidad.servicioPara(containerT));
		assertEquals(0d, servicioElectricidad.servicioPara(containerDS));
		assertEquals(0d, servicioElectricidad.servicioPara(containerDD));
	}
	
	@Test
	void testElPrecioPorElServicioPesadoEsElPrecioFijo() {
		assertEquals(5000d, servicioPesado.servicioPara(containerT));
		assertEquals(5000d, servicioPesado.servicioPara(containerDS));
		assertEquals(5000d, servicioPesado.servicioPara(containerDD));
		assertEquals(5000d, servicioPesado.servicioPara(containerR));
	}
	
	@Test
	void testSeTieneRegistroDelPesoDeUnContainerPorElServicioPesado() {
		assertEquals(1000d, servicioPesado.pesoDe(containerT));
		assertEquals(1000d, servicioPesado.pesoDe(containerDS));
		assertEquals(2000d, servicioPesado.pesoDe(containerDD));
		assertEquals(1000d, servicioPesado.pesoDe(containerR));
	}
	
	@Test
	void testElContainerDryConBLEspecialRecibeElServicioDesconsolidadoConPrecioFijo() {
		
		assertEquals(3000d, servicioDesconsolidado.servicioPara(containerDD));
	}
	
	@Test
	void testLosContainersQueNoTieneBLEspecialNoUsanElServicioDesconsolidado_SuPrecioEs0() {
		
		assertEquals(0d, servicioDesconsolidado.servicioPara(containerT));
		assertEquals(0d, servicioDesconsolidado.servicioPara(containerDS));
		assertEquals(0d, servicioDesconsolidado.servicioPara(containerR));
	}


}
