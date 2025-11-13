package container;

public abstract class Container {

	private String id;
	private String tipo;
	private double ancho;
	private double largo;
	private double altura;
	
	public Container(String id, String tipo, double ancho, double largo, double altura) {
		this.id = id;
		this.tipo = tipo;
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
	}

	public String getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
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
	
	public abstract double getPesoTotal();

	public double capacidad() {
		 return ancho * largo * altura;
	}

	public boolean EsRefeer() {
		return false;
	}

	public abstract boolean tieneBLEspecial();
	
}
