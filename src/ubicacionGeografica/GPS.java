package ubicacionGeografica;

public class GPS {
private double latitud, longitud;

	
	public GPS(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;

	}
	

	public void setPosicion(double lat, double lon) {
		latitud = lat;
		longitud = lon;
	}
	
	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}


	public double distanciaA(GPS gps) {
        double x = latitud - gps.getLatitud();
        double y = longitud - gps.getLongitud();

        return Math.sqrt(x * x + y * y);
    }
	
	public boolean esMismaPosicion(GPS gps) {
		boolean mismaLongitud = this.getLongitud() == gps.getLongitud();
    	boolean mismLatitud = this.getLatitud() == gps.getLatitud();
    	
    	return mismLatitud && mismaLongitud;
	}
}
