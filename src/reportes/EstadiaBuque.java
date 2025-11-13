package reportes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import container.Container;

public class EstadiaBuque implements Reportable {
	
	private String nombre;
	private LocalDateTime fechaArribo, fechaPartida;
    private List<Container> descargados;
    private List<Container> cargados;
	
	public EstadiaBuque(String nombre, LocalDateTime fechaArribo, LocalDateTime fechaPartida) {
		this.nombre = nombre;
        this.fechaArribo = fechaArribo;
        this.fechaPartida = fechaPartida;
        this.descargados = new ArrayList<Container>();
        this.cargados = new ArrayList<Container>();
	}

	@Override
	public void accept(GeneradorDeReporte generador) {
		generador.visit(this);

	}

	public String getNombre() {
		return nombre;
	}

	public LocalDateTime getFechaArribo() {
		return fechaArribo;
	}

	public LocalDateTime getFechaPartida() {
		return fechaPartida;
	}

	public List<Container> getDescargados() {
		return descargados;
	}

	public void setDescargados(List<Container> descargados) {
		this.descargados = descargados;
	}

	public List<Container> getCargados() {
		return cargados;
	}

	public void setCargados(List<Container> cargados) {
		this.cargados = cargados;
	}

}
