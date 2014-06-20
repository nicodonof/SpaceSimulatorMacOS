package cuerpos.spacer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import coordenaico.spacer.Punto;


public class Asteroide extends CuerpoCeleste implements Serializable{
	public Asteroide(Satelite entrada, ArrayList<String> nombres) {
		super();
		
		this.nombre = nombrar(nombres);
		
		this.radio = entrada.getRadio();
		
		this.masa = radio / 5; // la masa es la quinta parte del radio
		
		this.posicion = new Punto(entrada.getPosicion().getX(),entrada.getPosicion().getY());
		
		this.velocidadVector = new Punto(150,-150); // Estos valores son para simular 
														// fluidez
		tiempoDeCreacion = new Date().getTime();
	}
	
}
