package servicios;

import java.time.Duration;
import java.time.LocalDateTime;

import container.Container;

public class ServicioElectricidad extends Servicio{

	private LocalDateTime inicio;
	private LocalDateTime fin;
	
	public ServicioElectricidad(double precioFijo, LocalDateTime inicio, LocalDateTime fin) {
		super(precioFijo);
		this.inicio = inicio;
		this.fin = fin;
	}

	@Override
	public double servicioPara(Container container) {
		if (container.EsRefeer()) {
			Duration duracion = Duration.between(inicio, fin);
			long horasTranscurridas = duracion.toHours();
			
			return horasTranscurridas * this.getPrecioFijo();
		}
		return 0d;
	}

}
