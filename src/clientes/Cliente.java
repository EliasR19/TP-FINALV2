package clientes;

import java.util.ArrayList;
import java.util.List;

import buque.Buque;

public abstract class Cliente {
	
	private String nombre;
	//private List<String> mails;
	
	public Cliente(String nombre) {
		this.nombre = nombre;
		//this.mails = new ArrayList<String>();
	}
	
	public String getNombre() {
		return nombre;
	}
	

	public abstract void notificar(StringBuilder desgloce, Buque buque);



}
