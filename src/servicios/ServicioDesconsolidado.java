package servicios;

import container.Container;

public class ServicioDesconsolidado extends Servicio {

	public ServicioDesconsolidado() {
		super(200d);
	}



	@Override
	public double precioFinal(Container c) {
		return this.getPrecioFijo();
	}

}
