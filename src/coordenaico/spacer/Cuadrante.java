package coordenaico.spacer;

import java.io.Serializable;
import java.util.ArrayList;

import principal.spacer.Universo;

import cuerpos.spacer.Estrella;
import cuerpos.spacer.Planeta;

public class Cuadrante extends Universo implements Serializable{

	private double centroX = 0;
	
	private double centroY = 0;

	public Cuadrante(double centroX, double centroY) {
		super();
		this.centroX = centroX;
		this.centroY = centroY;
	}

	public double getCentroX() {
		return centroX;
	}

	public void setCentroX(double centroX) {
		this.centroX = centroX;
	}

	public double getCentroY() {
		return centroY;
	}

	public void setCentroY(double centroY) {
		this.centroY = centroY;
	}
	
	public boolean chequeaSiEstaEnCuadrante(Punto point){
		if(point.getX() > centroX - (ancho / 2) && point.getX() < centroX + (ancho / 2)){
			if(point.getY() > centroY - (alto / 2) && point.getY() < centroY + (alto / 2)){
				return true;
			}
		}
		return false;
	}

	public int cuantasEstrellas(ArrayList<Estrella> estrellas) {   //esto lo podria hacer general si pusiese a estrella como un cuerpo celeste y cuerpo celeste como posicion
		// TODO Auto-generated method stub
		int aux = 0;
		for (Estrella estrella : estrellas){
			if(chequeaSiEstaEnCuadrante(estrella.getPosicion())){
				aux++;
			}
		}
		return aux;
	}

	public int cuantosPlanetas(ArrayList<Planeta> planetas) {
		// TODO Auto-generated method stub
		int aux = 0;
		for (Planeta planeta: planetas) {
			if(chequeaSiEstaEnCuadrante(planeta.getPosicion()))
				aux++;
		}
		return aux;

	}
	
}
