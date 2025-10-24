package container;

public abstract class Container {

	private String id;
	private double ancho;
	private double largo;
	private double altura;
	
	public Container(String id, double ancho, double largo, double altura) {
		this.id = id;
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
	}

	public String getId() {
		return id;
	}


	public double getAncho() {
		return ancho;
	}


	public double getLargo() {
		return largo;
	}


	public double getAltura() {
		return altura;
	}

}
