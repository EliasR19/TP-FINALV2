package container;

import java.util.ArrayList;
import java.util.List;
import servicios.*;

public abstract class Container {

	private String id;
	private String tipo;
	private double ancho;
	private double largo;
	private double altura;
	
	private List<Servicio> servicios;
	private double peso; //Solo se conoce el peso cuando se le da el sertvicio;
	
	public Container(String id, String tipo, double ancho, double largo, double altura) {
		this.id = id;
		this.tipo = tipo;
		this.ancho = ancho;
		this.largo = largo;
		this.altura = altura;
		peso = 0d;
		
		servicios = new ArrayList<>();
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
	
	protected abstract double getPesoTotal();

	public double capacidad() {
		 return ancho * largo * altura;
	}

	public boolean EsRefeer() {
		return false;
	}

	
	public abstract boolean tieneBLEspecial();
	
	//Dar servicio
	public void darServicio(Servicio s) {
		servicios.add(s);
	}
	public  void registrarPeso() {
		peso = this.getPesoTotal();
	}
	
	public double getPesoRegistrado() {
		return peso;
	}
	
	
	//Precio Final
	public double precioFinal() {
		return servicios.stream().mapToDouble(s -> s.precioFinal(this)).sum();
	}

}
