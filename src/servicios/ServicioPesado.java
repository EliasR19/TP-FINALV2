package servicios;

import container.Container;

public class ServicioPesado extends Servicio{
	
	
	public ServicioPesado() {
		super(100d);
	}

	@Override
	public double precioFinal(Container c) {
		return this.getPrecioFijo();
	}

	@Override
	public String getTipo() {
		return "Pesado";
	}

}
