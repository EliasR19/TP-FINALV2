package container;

public class ContainerDry extends Container{
	
	private BillOfLading bl;
	
	public ContainerDry(String id, double ancho, double largo, double altura, BillOfLading bl) {
		
		super(id, ancho, largo, altura);
		this.bl = bl;
	}
	
	public BillOfLading getBl() {
		return bl;
	}
	
	public double getPesoTotal() {
		return bl.getPesoTotal();
	}
	
	public boolean tieneBLEspecial() {
		return bl.esEspecial();
	}
	
}
