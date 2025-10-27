package naviera;

import terminal.Terminal;

public class CircuitoMaritimo {
	
	private Terminal terminalOrigen;
	private Terminal terminalDestino;
	
	public CircuitoMaritimo(Terminal terminalOrigen, Terminal terminalDestino) {
		this.terminalOrigen = terminalOrigen;
		this.terminalDestino = terminalDestino;
	}

	public Terminal getTerminalOrigen() {
		return terminalOrigen;
	}

	public Terminal getTerminalDestino() {
		return terminalDestino;
	}

}
