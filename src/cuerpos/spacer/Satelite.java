package cuerpos.spacer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


import coordenaico.spacer.Punto;


public class Satelite extends CuerpoCeleste implements Serializable{

	
	private Planeta miPlaneta;
	
	private double distanciaAPlaneta;
	
	private double velocidadAngular;
	
	
	public Satelite(Planeta planeta) {
		super();
		
		miPlaneta = planeta;
		
		this.nombre = "Satelite " + miPlaneta.getNombre();
		
		this.radio = (int) (miPlaneta.getRadio() * Math.random() * 0.35 + 5);// como maximo el 35% del tamaño de los planetas
		
		this.distanciaAPlaneta = (Math.random() * 30) + miPlaneta.getRadio() ; 
		
		this.posicion = posicionarPrimeraVez();
		
		tiempoDeCreacion = new Date().getTime();
		
		double signo = ( Math.random() - 0.5 );// para que gire para ambos lados
		if(signo > 0){
			signo = 1;
		}else{
			signo = -1;
		}
		
		this.velocidadAngular = (0.5 + Math.random() * 2) * signo; // la velocidad de giro
	}
	
	public double getDistanciaAPlaneta() {
		return distanciaAPlaneta;
	}


	public void setDistanciaAPlaneta(double distanciaAPlaneta) {
		this.distanciaAPlaneta = distanciaAPlaneta;
	}


	public Planeta getMiPlaneta() {
		return miPlaneta;
	}


	public void setMiPlaneta(Planeta miPlaneta) {
		this.miPlaneta = miPlaneta;
	}


	public Punto posicionarPrimeraVez(){
		Punto aux = new Punto(0,0);
		
		double distanciaEntreLunaYPlaneta = getDistanciaAPlaneta();
		
		aux.setX(miPlaneta.getPosicion().getX() + distanciaEntreLunaYPlaneta * Math.cos(2 + distanciaEntreLunaYPlaneta));
		aux.setY(miPlaneta.getPosicion().getY() + distanciaEntreLunaYPlaneta * Math.sin(2 + distanciaEntreLunaYPlaneta));
		return aux;
	}
	@Override
	public void	posicionar(ArrayCuerpoCeleste cuerpos, int delta){ // se mantienen los parametros para lograr el polimorfismo
			double tiempoCasteado = new Date().getTime() - tiempoDeCreacion;
			
			tiempoCasteado = tiempoCasteado / 325;
			
			double distanciaEntreLunaYPlaneta = getDistanciaAPlaneta();
			posicion.setX(miPlaneta.getPosicion().getX() + distanciaEntreLunaYPlaneta * Math.cos(velocidadAngular*tiempoCasteado + distanciaEntreLunaYPlaneta)); //velocidad entre 0.5 y  2.5
			posicion.setY(miPlaneta.getPosicion().getY() + distanciaEntreLunaYPlaneta * Math.sin(velocidadAngular*tiempoCasteado + distanciaEntreLunaYPlaneta));
	}

}
