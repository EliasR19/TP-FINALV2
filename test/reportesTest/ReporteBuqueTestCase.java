package reportesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import container.BL;
import container.Container;
import container.ContainerDry;
import container.ContainerReefer;
import reportes.*;

class ReporteBuqueTestCase {
	
	private EstadiaBuque estadia;
	private BL bl1, bl2;

	@BeforeEach
	void setUp() throws Exception {
		bl1 = new BL();
		bl1.enlistar("Agua", 500d);
		bl1.enlistar("Aceite de Oliva", 100d);
		bl1.enlistar("Gasolina", 400d);
		
		bl2 = new BL();
		bl2.enlistar("Agua", 500d);
		bl2.enlistar("Aceite de Oliva", 100d);
		bl2.enlistar("Gasolina", 400d);
		
		List<Container> descargados = List.of(new ContainerDry("abcd1234567", "Dry", 26d, 22d, 20d, bl1), new ContainerReefer("efgh9876543", "Reefer", 26d, 22d, 20d, bl2, 20d));
	    List<Container> cargados = List.of(new ContainerDry("mnhj1234567", "Dry", 26d, 22d, 20d, bl1));

	    estadia = new EstadiaBuque("Buque1", LocalDateTime.of(LocalDate.of(2025,10,31), LocalTime.of(1, 0)), LocalDateTime.of(LocalDate.of(2025,11,15), LocalTime.of(1, 0)));
	    estadia.setDescargados(descargados);
	    estadia.setCargados(cargados);
	}

	@Test
	void test() {
		ReporteBuque reporte = new ReporteBuque();
		
		String resultadoEsperado = 
		"""
        <report>
          <import>
            <item>abcd1234567</item>
            <item>efgh9876543</item>
          </import>
          <export>
            <item>mnhj1234567</item>
          </export>
        </report>
        """;
		
		estadia.accept(reporte);
		assertEquals(resultadoEsperado.trim(), reporte.getReporte().trim());
	}

}
