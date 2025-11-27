package container;

import java.util.ArrayList;
import java.util.List;

public class BL implements BillOfLading {
	
	private List<Carga> cargas;
	
	public BL() {
		cargas = new ArrayList<>();
	}

	
	
	@Override
	public double getPesoTotal() {
		return cargas.stream().mapToDouble(c -> c.getPeso()).sum();
	}
	
	@Override
	public List<Carga> getCarga(){
		return cargas;
	}
	
	public boolean esEspecial() {
		return false;
	}


	public void enlistar(Carga carga) {
		cargas.add(carga);
	}
		
}
	

