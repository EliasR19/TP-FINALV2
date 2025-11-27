package container;

import java.util.List;

public interface BillOfLading {
	
	public double getPesoTotal();
	public List<Carga> getCarga();
	public abstract boolean esEspecial();
	
}
