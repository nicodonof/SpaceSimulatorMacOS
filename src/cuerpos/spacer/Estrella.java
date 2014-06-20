package cuerpos.spacer;

import java.io.Serializable;
import java.util.*;

import principal.spacer.Screen;

import coordenaico.spacer.Cuadrante;
import coordenaico.spacer.Punto;


public class Estrella extends CuerpoCeleste implements Screen , Serializable{
	
	private double tiempoDeVida;
	
	public Estrella(Cuadrante cuadrant, ArrayList<String> nombres) {
		super();
		
		this.nombre = nombrar(nombres);
		
		this.radio = (int) (Math.random()*30) + 60; // entre 60 y 90 un tanto mayor que los planetas
		
		this.masa = 30 + radio / 5; //entre 42 y 54, para balancear la atraccion de los planetas hacia ellos
		
		this.tiempoDeVida = (10000 + Math.random() * 17000); // balancea el universo
		
		this.tiempoDeCreacion = 0;
		//La posicion se randomiza segun el cuadrante al cual le entra el constructor
		this.posicion = new Punto((Math.random() - 0.5) * ancho + cuadrant.getCentroX(),(Math.random() - 0.5) * alto + cuadrant.getCentroY());
		
		
	}

	public double getTiempoDeVida() {
		return tiempoDeVida;
	}


	public void setTiempoDeVida(double tiempoDeVida) {
		this.tiempoDeVida = tiempoDeVida;
	}

	@Override
	public void posicionar(ArrayCuerpoCeleste cuerpos, int delta) {
		//LAS ESTRELLAS NO SE MUEVEN
		return;
	}
	

}
