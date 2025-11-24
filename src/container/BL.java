package container;

import java.util.ArrayList;
import java.util.List;

public class BL implements BillOfLading {
	
	private List<Carga> cargas;
	
	public BL() {
		cargas = new ArrayList<>();
	}


	public void agregarCarga(Carga c) {
		cargas.add(c);
	}
	
	@Override
	public double getPesoTotal() {
		return cargas.stream().mapToDouble(c -> c.getPeso()).sum();
	}

	public boolean esEspecial() {
		return false;
	}
		
}
	

