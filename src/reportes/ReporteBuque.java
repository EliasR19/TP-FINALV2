package reportes;

import java.util.List;

import container.Container;

public class ReporteBuque implements GeneradorDeReporte {
	
	private StringBuilder reporte = new StringBuilder();
	
	public ReporteBuque() {
		
	}

	@Override
	public void visit(EstadiaBuque estadia) {
		List<Container> descargados = estadia.getDescargados();
        List<Container> cargados = estadia.getCargados();
        
        reporte.append("<report>\n");
        reporte.append("  <import>\n");
        
        for (Container c : descargados) {
        	reporte.append("    <item>").append(c.getId()).append("</item>\n");
        }
        
        reporte.append("  </import>\n");
        reporte.append("  <export>\n");
        
        for (Container c : cargados) {
        	reporte.append("    <item>").append(c.getId()).append("</item>\n");
        }
        
        reporte.append("  </export>\n");
        reporte.append("</report>\n");
    }
    
	public String getReporte() {
        return reporte.toString();
    }

}
