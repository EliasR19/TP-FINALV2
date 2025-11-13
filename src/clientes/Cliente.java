package clientes;

import java.util.ArrayList;
import java.util.List;

import buque.Buque;

public abstract class Cliente {
	
	private String nombre;
	private List<String> mails;
	
	public Cliente(String nombre) {
		this.nombre = nombre;
		mails = new ArrayList();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public List<String> getMails(){
		return mails;
	}
	protected void agregarMail(String s) {
		mails.add(s);
	}

	public abstract void notificar(Buque b);
	
}
