package reportes;

import java.util.List;
import java.util.stream.Stream;

import container.Container;

public class ReporteAduana implements GeneradorDeReporte {
	
	private StringBuilder reporte = new StringBuilder();
	
	public ReporteAduana() {
		
	}

	@Override
	public void visit(EstadiaBuque estadia) {
		String nombre = estadia.getNombre();
        String arribo = estadia.getFechaArribo().toString();
        String partida = estadia.getFechaPartida().toString();
        List<Container> contenedores = Stream.concat(estadia.getDescargados().stream(), estadia.getCargados().stream()).toList();
        
        reporte.append("<html><body>\n");
        reporte.append("  <h1>Reporte de Aduana</h1>\n");
        reporte.append("  <p><strong>Buque:</strong> ").append(nombre).append("</p>\n");
        reporte.append("  <p><strong>Arribo:</strong> ").append(arribo).append("</p>\n");
        reporte.append("  <p><strong>Partida:</strong> ").append(partida).append("</p>\n");
        reporte.append("  <h2>Contenedores</h2>\n");
        reporte.append("  <ul>\n");
        
        for (Container c : contenedores) {
        	reporte.append("    <li>").append(c.getId()).append(" (Tipo: ").append(c.getTipo()).append(")").append("</li>\n");
        }
        reporte.append("  </ul>\n");
        reporte.append("</body></html>\n");
    }

    public String getReporte() {
        return reporte.toString();
    }

}
