package servicios;

import container.Container;

public class ServicioElectricidad extends Servicio{

	public ServicioElectricidad(double precioFijo) {
		super(precioFijo);
	}

	@Override
	public double servicioPara(Container container) {
		if (container.EsRefeer()) {
			return 1d;
		}
		return 0d;
	}

}
