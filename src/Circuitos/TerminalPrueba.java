package Circuitos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class TerminalPrueba {
	
	public TerminalPrueba(String name) {
		super();
		this.name = name;
	}
	private String name;
	private List<LineaNaviera> lineas = new ArrayList<LineaNaviera>();
	
	public void agregarLiena(LineaNaviera l) {
		lineas.add(l);
	}

	public String getName() {
		return name;
	}
	

	public void exportarCarga(TerminalPrueba t) {
		//Buscar linea que contenga un circuito que contenga 't' como origen de algun Viaje.
	}
	

	public void asignarFecSalidaBuqe(Buque bA, LocalDateTime fecSalida) {
		//Se supone que el buque esta dentro de la terminal
		bA.setFecSalida(fecSalida);
	}
	
	// Punto 4
	public double duracionRecorridoEntre(LineaNaviera naviera, TerminalPrueba destino) {
		return naviera.duracionEntre(this, destino);
	}
}
