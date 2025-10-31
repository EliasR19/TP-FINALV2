package servicios;

import container.Container;

public class ServicioPesado extends Servicio{

	public ServicioPesado(double precioFijo) {
		super(precioFijo);
	}

	public double servicioPara(Container container) {
		return getPrecioFijo();
	}

	public Double pesoDe(Container container) {
		return container.getPesoTotal();
	}

}
