package container;

public class ContainerReefer extends UnicoImportador{
	
	private double kwPorHora;
	
	public ContainerReefer(String id, double ancho, double largo, double altura, BL bl, double kwPorHora) {
		super(id, ancho, largo, altura, bl);
		this.kwPorHora = kwPorHora;
	}
	
	public double getKwPoreHora() {
		return kwPorHora;
	}

	public boolean EsRefeer() {
		return true;
	}
}
