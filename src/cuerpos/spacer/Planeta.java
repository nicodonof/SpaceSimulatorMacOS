package cuerpos.spacer;

import java.io.Serializable;
import java.util.*;

import principal.spacer.Screen;

import coordenaico.spacer.Cuadrante;
import coordenaico.spacer.Punto;


public class Planeta extends CuerpoCeleste implements Screen ,Serializable{
	 
	private ArrayList<Satelite> misSatelites = new ArrayList<Satelite>();
	
	private static final double NUMERO_AUXILIAR_1 = 2131;
	
	private static final double NUMERO_AUXILIAR_2 = -0.502;

	private double radioSetterRecur(int level){
		 double random = Math.random();
		 if(random < 0.33){ // Esta funcion asigna la cantidad de lunas usando randoms y probabilidad
			 return random;
		 }else if(random < 0.66 && level == 1){
			 return random;
		 }else if(level == 2){
			 return random;
		 }else {
			 return radioSetterRecur(level + 1);
		 }
	}
	 
	public Planeta(ArrayList<Estrella> Estrellas, Cuadrante cuadranT, ArrayList<String> nombres) {
	  super();
	  
	  nombre = (nombrar(nombres));
	  
	  radio = ((int) (radioSetterRecur(0) *30 ) + 20); // entre 20 y 50
	  
	  masa = (Math.pow(radio, 2) / 200); //entre 2 y 12.5
	  
	  posicion = posicionarPrimeraVez((float) (ancho + cuadranT.getCentroX()),(float) (alto + cuadranT.getCentroY()));
	  
	  this.velocidadVector = (velocidadInicial(Estrellas));
	  
	  tiempoDeCreacion = new Date().getTime();
	}
	 
	 public Planeta(float x,float y, int radio, Punto velocidadInit, String nombre){
		 super();
		  
		  this.nombre = nombre;
		  
		  this.radio = radio;
		  
		  this.masa = (Math.pow(radio, 2) / 200); //entre 2 y 12.5
		  
		  this.posicion = new Punto(x,y);
		  
		  this.velocidadVector = velocidadInit;
		  
		  tiempoDeCreacion = new Date().getTime();
	 }
	 
	
	
	public ArrayList<Satelite> getMisSatelites() {
			return misSatelites;
	}
	
	
	public Punto velocidadInicial(ArrayList<Estrella> estrellas){
		Estrella estrellaAMenorDistancia = null;
		double minDistancia = 0;
		
		int signo = 1;
		if(Math.random() > 0.5){
			signo = -1;
		}
		for (Estrella estrella : estrellas) {
			double distancia = posicion.distanciaEntrePuntos(estrella.getPosicion());
			if(distancia < minDistancia || minDistancia == 0){
				minDistancia = distancia; // se utiliza para calcular la estrella a menor distancia
				estrellaAMenorDistancia = estrella;
			}
		}
		//Estos numeros ayudan al balanceo de la velocidad inicial para que los planetas que aparecen mas
		//cerca una estrella tengan una velocidad inicial mas alta para compensar la fuerza gravitatoria
		//que reciben
		double modulo = NUMERO_AUXILIAR_1 * Math.pow(minDistancia,NUMERO_AUXILIAR_2); // para 
		
		Punto versor = new Punto(0,0);
		versor = estrellaAMenorDistancia.getPosicion().resta(this.posicion);
		versor = versor.divisionConstanteVector(minDistancia);
		// Esto se realiza para convertir el versor en perpendicular a si mismo
		Punto versorPerpendicular = new Punto(signo * versor.getY(),-signo * versor.getX()); 
		return (versorPerpendicular.multiplicacionConstanteVector(modulo));
		 
	}
	
	public Punto posicionarPrimeraVez(float ancho, float alto){
		  int aux1 = 1;
		  int aux2 = 1;
		  if(Math.random() > 0.5)
			  aux1 = -1;
		  if(Math.random() > 0.5)
			  aux2 = -1;
		Punto aux = new Punto((ancho/2) * aux1 * (Math.random()) ,(alto /2)* aux2 *Math.random());
		return aux;
	}
}
