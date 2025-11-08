package ubicacionGeografica;

import java.util.Timer;
import java.util.TimerTask;

import buque.Buque;

// GPS sería el sujeto y el buque el observador

public class GPS extends UbicacionGeografica {

	private Buque buque;
	private UbicacionGeografica destino; 
	
	public GPS(int latitud, int longitud, Buque buque) {
		super(latitud, longitud);
		this.buque = buque;
	}
	
	public void iniciarTimer() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                actualizarPosicionPorUnMinuto();
            }
        }, 0, 60000); // Corresponde a un 1 minuto
	}
	
	public void actualizarPosicionPorUnMinuto() {
        
		double avance = 740; // Por cada minuto el buque avanza 740 metros, ya que tomo que los buques avanzan 24 nudos por hora.
						     // 24 nudos serían 44.448 metros por hora.
						     // 44 metros / 60 minutos = 0.740 metros por minuto
		
		double[] factores = buque.getDestinoActual().getUbicacion().factorPara(getLatitud(), getLongitud(), avance);
		double metrosNorte 	     = factores[0];
	    double metrosEste  		 = factores[1];
	    double factor      		 = factores[2];
	    double distanciaRestante = factores[3];
		
	    if (factor >= 1.0) { // Si llega al destino o nos vamos a pasar de él, tomamos directamente los datos del destino 
	    					// (que seria que se mueve menos metros de lo max que puede avanzar en un minuto, lo necesario solamante)
	    	setLatitud(destino.getLatitud());
	    	setLongitud(destino.getLongitud());
	    	}   else { // Sino, se avanza de forma proporcional
	    			setLatitud(getLatitud() + metrosNorte * factor / 111000); 
	    			setLongitud(getLongitud() + metrosEste * factor / (111000 * Math.cos(Math.toRadians(getLatitud()))));
	    		}
		buque.actualizarPosicion(distanciaRestante); // Se le avisa al buque de los cambios
	    }

	}