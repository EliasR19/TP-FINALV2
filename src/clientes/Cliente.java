package clientes;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	
	private String nombre;
	private List<String> mails;
	
	public Cliente(String nombre) {
		this.nombre = nombre;
		this.mails = new ArrayList<String>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<String> getMails() {
		return mails;
	}

	public void recibirMail(String mail) {
		mails.add(mail);
	}
}
