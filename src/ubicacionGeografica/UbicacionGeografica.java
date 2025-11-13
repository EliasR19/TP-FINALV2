package ubicacionGeografica;

import terminal.Terminal;

public class UbicacionGeografica {
	private double latitud;  // Norte(+)/Sur(-)
	private double longitud; // Este(+)/Oeste(-)
	
	public UbicacionGeografica(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}
	

	public void setLatitud(double unaLatitud) {
		this.latitud = unaLatitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double unaLongitud) {
		this.longitud = unaLongitud;
	}

	//public boolean tienenMismaPosicion(UbicacionGeografica ubicacion) {
	//	boolean mismaLatitud = latitud == ubicacion.getLatitud();
	//	boolean mismaLongitud = longitud == ubicacion.getLongitud();
	//	
	//	return mismaLatitud && mismaLongitud;
	//}

	public double[] factorPara(double unaLatitud, double unaLongitud, double avance) {
		
		double distancia = this.distanciaEntre(unaLatitud, unaLongitud);
		
		// Hago el avance que puede tener y la divido por la distancia del destino. De esta forma tengo la cantidad suficiente para avanzar
		// sin pasarnos de la ubicacion de la terminal
		double factor = Math.min(1.0, avance / distancia);
		
		return new double[]{metrosNorte(unaLatitud), metrosEste(unaLatitud, unaLongitud), factor, distancia};
	}
	
	public Double distanciaEntre(double unaLatitud, double unaLongitud) {
		double metrosNorte = metrosNorte(unaLatitud); 
		
		double metrosEste = metrosEste(unaLatitud, unaLongitud); 

		return Math.sqrt(metrosEste*metrosEste + metrosNorte*metrosNorte);
		// Utilizamos pitagoras para calcular la distancia recta en metros entre la posicion actual y hacia el destino
	}

	private double metrosEste(double unaLatitud, double unaLongitud) {
		return (longitud - unaLongitud) * 111000 * Math.cos(Math.toRadians(unaLatitud)); // La de longitud es parecida, pero se hace por coseno,
	} 	  																				 // ya que se hace  más corta cuando es más cerca del
																						 // polo. Si da + es Este, sino Oeste
	
	private double metrosNorte(double unaLatitud) {
		return (latitud - unaLatitud) * 111000;		// La diferencia de latitud se hace con la diferencia de grados y luego
	} 												//  multiplicando por 111000 metros. Si da + es Norte, si da - es Sur
}
