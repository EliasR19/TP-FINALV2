package servicios;

import container.Container;

public class ServicioDesconsolidado extends Servicio {

	public ServicioDesconsolidado(double precioFijo) {
		super(precioFijo);
	}

	public double servicioPara(Container container) {
		if (container.tieneBLEspecial()) {
			return getPrecioFijo();
		}
		return 0d;
	}

}
