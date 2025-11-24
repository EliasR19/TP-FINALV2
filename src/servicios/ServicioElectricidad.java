package servicios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import container.Container;

public class ServicioElectricidad extends Servicio{

	private LocalDateTime inicio;
	private LocalDateTime fin;
	
	public ServicioElectricidad(LocalDateTime inicio, LocalDateTime fin) {
		super(50d); // precio por kw/hora
		this.inicio = inicio;
		this.fin = fin;
	}


	@Override
	public double precioFinal(Container c) {
			long nroHoras = inicio.until(fin, ChronoUnit.HOURS);
			return this.getPrecioFijo() * nroHoras ;	
	}
	
	public double horasTotal() {
		return inicio.until(fin, ChronoUnit.HOURS);
	}

}
