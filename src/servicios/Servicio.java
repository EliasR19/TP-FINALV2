package servicios;

import container.Container;

public abstract class Servicio {
	private double precioFijo;
	
	public Servicio(double precioFijo) {
		this.precioFijo = precioFijo;
	}

	public double getPrecioFijo() {
		return precioFijo;
	}

	public abstract double precioFinal(Container c); 


}