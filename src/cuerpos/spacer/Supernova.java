package cuerpos.spacer;

import java.io.Serializable;
import java.util.ArrayList;

import coordenaico.spacer.Cuadrante;

public class Supernova extends CuerpoCeleste implements Serializable{
	
	private double radioInit;
	
	private int porMorir;
	
	public Supernova(Estrella estrella) {
		masa = estrella.masa;
		nombre = estrella.nombre;
		radio = estrella.radio;
		posicion = estrella.posicion;
		radioInit = radio;
		porMorir = 350; // El tiempo que sobrevive en el tamaño maximo 
	}
	public void ajustarRadio(){
		if(porMorir == 0){ // decrece el tamaño
			radio -= 0.2;
			masa =  30 + radio / 5;
		}else if(radio< radioInit * 2.5){ // Aumenta el tamaño de la supernova
			radio += radioInit * 0.02;
			masa =  30 + radio / 5;
		} else { // Periodo de estabilidad de la supernova
			porMorir--;
		}
		
	}
	public void posicionar(ArrayCuerpoCeleste cuerpos, int delta){
		//Las supernovas no se mueven
		return;
	}
}
