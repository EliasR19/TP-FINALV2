package ubicacionGeografica;

import java.util.Timer;
import java.util.TimerTask;

import buque.Buque;
import terminal.Terminal;

// GPS sería el sujeto y el buque el observador

public class GPS extends UbicacionGeografica {

	private Buque buque;
	private double distanciaRestante;
	private boolean timerIniciado;
	private Timer timer;
	
	public GPS(double latitud, double longitud, Buque buque) {
		super(latitud, longitud);
		this.buque = buque;
		this.distanciaRestante = 0;
		timer = new Timer();
		timerIniciado = false;
	}
	
	public boolean getTimerIniciado() {
		return timerIniciado;
	}
	
	public void setTimerIniciado(boolean valor) {
		this.timerIniciado = valor;
	}
	
	public void iniciarTimer(Terminal terminal) {
		
		super.setLatitud(terminal.getUbicacion().getLatitud());
		super.setLongitud(terminal.getUbicacion().getLongitud());
		
		setTimerIniciado(true);
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                actualizarPosicionPorUnMinuto();
            }
        }, 0, 60000); // Corresponde a un 1 minuto
	}
	
	public void actualizarPosicionPorUnMinuto() {
        
		if (timerIniciado) {
		
			double avance = 740; // Por cada minuto el buque avanza 740 metros, ya que tomo que los buques avanzan 24 nudos por hora.
						         // 24 nudos serían 44.448 metros por hora.
						         // 44.448 metros / 60 minutos = 740 metros por minuto
			Terminal destino = buque.getDestinoActual();
			double[] factores = destino.getUbicacion().factorPara(getLatitud(), getLongitud(), avance);
			double metrosNorte 	     = factores[0];
			double metrosEste  		 = factores[1];
			double factor      		 = factores[2];
			double distancia  	     = factores[3];

			setDistanciaRestante(distancia);
		
			if (factor >= 1.0) { // Si llega al destino o nos vamos a pasar de él, tomamos directamente los datos del destino 
	    					     // (que seria que se mueve menos metros de lo max que puede avanzar en un minuto, lo necesario solamante)
				super.setLatitud(destino.getUbicacion().getLatitud());
				super.setLongitud(destino.getUbicacion().getLongitud());
				buque.actualizarPosicion(distancia, destino);
	    		}   else { // Sino, se avanza de forma proporcional
	    				super.setLatitud(super.getLatitud() + metrosNorte * factor / 111000); 
	    				super.setLongitud(super.getLongitud() + metrosEste * factor / (111000 * Math.cos(Math.toRadians(getLatitud()))));
	    			}
			buque.actualizarPosicion(distancia, destino); // Se le avisa al buque de los cambios
		} else {
			throw new RuntimeException("No se puede actualizar la posición con el timer apagado");
		}
	}
	
	 private void setDistanciaRestante(double distancia) {
	        this.distanciaRestante = distancia;
	    }

	public double getDistanciaRestante() {
		return distanciaRestante;
	}

	public void apagarTimer() {
		if (timerIniciado) {
			timer.cancel();
	        timerIniciado = false;
		} else {
			throw new RuntimeException("El timer ya se encuentra apagado");
		}
		
	}

	}