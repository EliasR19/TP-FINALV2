package servicios;

import container.Container;

public class ServicioLavado extends Servicio {

	public ServicioLavado() {
		super(130d);
	}


	@Override
	public double precioFinal(Container c) {
		if (c.capacidad() <= 70d) {
			return this.getPrecioFijo();
		}
		return this.getPrecioFijo() + (this.getPrecioFijo() * 0.5);
	}


	@Override
	public String getTipo() {
		return "Lavado";
	}

}
