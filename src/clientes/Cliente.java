package clientes;

public abstract class Cliente implements ObserverC{
	
	protected boolean seNotifico; //solo para los test 
	

	private String nombre;
	//private List<String> mails;
	
	public Cliente(String nombre) {
		this.nombre = nombre;
		seNotifico  = false;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean isSeNotifico() {
		return seNotifico;
	}

	public  void recibirFactura(StringBuilder generarFactura) {}





}
