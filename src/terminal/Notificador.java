package terminal;

import clientes.Cliente;

public class Notificador {
	
	public Notificador() {
		
	}

	public void enviarMailDeSalidaDeBuque(Cliente cliente, OrdenExp orden) {
		cliente.recibirMail("Hola, le avisamos que su container de la orden " 
							+ orden + 
							", ya ha salido de la terminal. En brevedad se le mandará la factura correspondiente. Muchas gracias, saludos.");
	}

	public void enviarMailDeLlegadaDeBuque(Cliente cliente, OrdenExp orden) {
		cliente.recibirMail("Hola, le avisamos que su container de la orden " 
				+ orden + 
				", estará próximamente arribando. Muchas gracias, saludos.");
	}
	
	
}
