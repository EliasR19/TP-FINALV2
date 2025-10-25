package container;

public abstract class UnicoImportador extends Container{

	private BL bl;
	
	public UnicoImportador(String id, double ancho, double largo, double altura, BL bl) {
		
		super(id, ancho, largo, altura);
		this.bl = bl;
	}

	public BL getBl() {
		return bl;
	}
	
	public double getPesoTotal() {
		return bl.getPesoTotal();
	}
	
}
