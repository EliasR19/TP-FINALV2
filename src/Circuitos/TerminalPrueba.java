package Circuitos;

import java.time.LocalDateTime;
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
	
	public void asignarViaje(Buque b, LocalDateTime fecSalida, CircuitoMaritimo c) {
		b.asignarViaje(new Viaje(fecSalida, this, c));
	}
}
