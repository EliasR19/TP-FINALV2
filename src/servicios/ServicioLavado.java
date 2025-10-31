package servicios;

import container.Container;

public class ServicioLavado extends Servicio {

	public ServicioLavado(double precioFijo) {
		super(precioFijo);
	}
	
	public double servicioPara(Container container) {
		
		if (container.capacidad() <= 70d) {
			return this.getPrecioFijo();
		}
		return this.getPrecioFijo() + (this.getPrecioFijo() * 0.50);
		
	}

}
