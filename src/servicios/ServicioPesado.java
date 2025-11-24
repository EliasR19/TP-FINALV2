package servicios;

import container.Container;

public class ServicioPesado extends Servicio{
	
	
	public ServicioPesado() {
		super(100d);
	}

	@Override
	public double precioFinal(Container c) {
		c.registrarPeso();
		return this.getPrecioFijo();
	}

}
