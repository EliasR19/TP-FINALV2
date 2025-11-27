package container;

import java.util.ArrayList;
import java.util.List;

public class BLEspecial implements BillOfLading {
	
	private List<BL> blEspecial = new ArrayList<BL>();
	
	public void agregarBL(BL bl) {
		blEspecial.add(bl);
	}
	
	@Override
	public List<Carga> getCarga(){
		List<Carga> cargas = new ArrayList<Carga>();
		for(BL bl : blEspecial) {
			cargas.addAll(bl.getCarga());
		}
		return cargas;
	}
	
	public double getPesoTotal() {
		return blEspecial.stream().mapToDouble(bl -> bl.getPesoTotal()).sum();
	}

	public boolean esEspecial() {
		return true;
	}
	
}
