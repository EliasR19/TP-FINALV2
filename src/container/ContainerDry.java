package container;

import servicios.ServicioDesconsolidado;

public class ContainerDry extends Container{
	
	private BillOfLading bl;
	
	public ContainerDry(String id, String tipo, double ancho, double largo, double altura, BillOfLading bl) {
		
		super(id, tipo, ancho, largo, altura);
		this.bl = bl;
		if(this.tieneBLEspecial()) {
			this.darServicio(new ServicioDesconsolidado());
		}
	}
	
	public BillOfLading getBl() {
		return bl;
	}
	
	
	public boolean tieneBLEspecial() {
		return bl.esEspecial();
	}


	@Override
	protected double getPesoTotal() {
		return bl.getPesoTotal();
	}
	
}
