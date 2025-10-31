package Terminal;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import naviera.*;
import buque.Buque;


public class Terminal {
	
	public Terminal(String name) {
		super();
		this.name = name;
	}
	private String name;
	private List<Naviera> lineas = new ArrayList<Naviera>();
	
	public void agregarLiena(Naviera l) {
		lineas.add(l);
	}

	public String getName() {
		return name;
	}
	

	public void exportarCarga(Terminal t) {
		//Buscar linea que contenga un circuito que contenga 't' como origen de algun Viaje.
	}
	

	public void asignarFecSalidaBuqe(Buque bA, LocalDateTime fecSalida) {
		//Se supone que el buque esta dentro de la terminal
		bA.setFecSalida(fecSalida);
	}
	
	// Punto 4
	public double duracionRecorridoEntre(Naviera naviera, Terminal destino) {
		return naviera.duracionEntre(this, destino);
	}
}