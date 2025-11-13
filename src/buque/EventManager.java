package buque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import clientes.*;

public class EventManager {
	private HashMap<Buque, List<Cliente>> esperando = new HashMap<>();
	
	
	public void agregarObserver(Cliente c, Buque b) {
		List<Cliente> clientes = new ArrayList<>();
			clientes.add(c); // porque se remplaza toda la lista con el put
			esperando.put(b, clientes);
	}

	public void notificar(Buque buque) {
		//esperando.get(buque).stream().forEach(c -> notificar(buque));			
		for(Cliente c : esperando.get(buque)) {
			c.notificar(buque);
		}
		
		//Borra todos los que esperan ese buque, porque ya los notifico.
		//esperando.remove(buque);		
	}
	
	public List<Cliente> getEsperando(Buque b){
		return esperando.get(b);
	}
}
