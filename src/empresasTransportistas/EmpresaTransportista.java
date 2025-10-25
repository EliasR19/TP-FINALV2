package empresasTransportistas;

import java.util.ArrayList;
import java.util.List;

public class EmpresaTransportista {
	
	private List<Camion> camiones;
	private List<Chofer> choferes;
	
	public EmpresaTransportista() {
		this.camiones = new ArrayList<Camion>();
		this.choferes = new ArrayList<Chofer>();
	}

	public List<Camion> getCamiones() {
		return camiones;
	}

	public List<Chofer> getChoferes() {
		return choferes;
	}

	public void registraCamion(Camion unCamion) {
		camiones.add(unCamion);
	}
	
	public void registraChofer(Chofer unChofer) {
		choferes.add(unChofer);
	}

	public boolean estaRegistradoCamion(Camion unCamion) {
		return camiones.contains(unCamion);
	}
	
	public boolean estaRegistradoChofer(Chofer unChofer) {
		return choferes.contains(unChofer);
	}
}
