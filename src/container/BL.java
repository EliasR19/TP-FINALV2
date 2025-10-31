package container;

import java.util.HashMap;
import java.util.Map;

public class BL implements BillOfLading {
	
	private Map<String, Double> bl = new HashMap<String, Double>();

	public void enlistar(String tipo, double peso) {
		bl.put(tipo, peso);
	}
	
	@Override
	public double getPesoTotal() {

		double pesoTotal = 0;
		for(String k : bl.keySet()) {
			pesoTotal += bl.get(k);
		}
		return pesoTotal;
	}

	public boolean esEspecial() {
		return false;
	}
		
}
	

