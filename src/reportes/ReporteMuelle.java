package reportes;

public class ReporteMuelle implements GeneradorDeReporte {
	
	private StringBuilder reporte = new StringBuilder();
	
	public ReporteMuelle() {
		
	}

	@Override
	public void visit(EstadiaBuque estadia) {
		String nombre = estadia.getNombre();
        String arribo = estadia.getFechaArribo().toString();
        String partida = estadia.getFechaPartida().toString();
        
        int cantOperados = estadia.getCargados().size() + estadia.getDescargados().size();
        
        reporte.append("--- REPORTE DE MUELLE ---\n");
        reporte.append("Buque: ").append(nombre).append("\n");
        reporte.append("Arribo: ").append(arribo).append("\n");
        reporte.append("Partida: ").append(partida).append("\n");
        reporte.append("Contenedores Operados: ").append(cantOperados).append("\n");
	}
	
	public String getReporte() {
        return reporte.toString();
    }

}
