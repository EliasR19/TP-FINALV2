package ubicacionGeografica;

public class UbicacionGeografica {
	private int latitud;
	private int longitud;
	
	public UbicacionGeografica(int latitud, int longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public int getLatitud() {
		return latitud;
	}

	public void setLatitud(int x) {
		this.latitud = x;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int y) {
		this.longitud = y;
	}
}
