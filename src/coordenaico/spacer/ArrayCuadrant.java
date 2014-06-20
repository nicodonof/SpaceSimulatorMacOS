package coordenaico.spacer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import coordenaico.spacer.Cuadrante;
import cuerpos.spacer.Estrella;
import cuerpos.spacer.Planeta;


public class ArrayCuadrant extends ArrayList<Cuadrante> implements Serializable{

	public ArrayCuadrant() {
		super();
	}
	
	public ArrayCuadrant conEstrellas(ArrayList<Estrella> estrellas){
		ArrayCuadrant aux = new ArrayCuadrant();
		for (Cuadrante cuadrante : this) {
			if(cuadrante.cuantasEstrellas(estrellas) != 0){
				aux.add(cuadrante);
			}
		}
		return aux;
	
	}
	
	public ArrayCuadrant sinEstrellas(ArrayList<Estrella> estrellas){
		ArrayCuadrant aux = new ArrayCuadrant();
		for (Cuadrante cuadrante : this) {
			if(cuadrante.cuantasEstrellas(estrellas) == 0){
				aux.add(cuadrante);
			}
		}
		return aux;
	
	}
	
	public ArrayCuadrant conHastaPlanetas(ArrayList<Planeta> planetas, int numero){
		ArrayCuadrant aux = new ArrayCuadrant();
		for (Cuadrante cuadrante : this) {
			if(cuadrante.cuantosPlanetas(planetas) <= numero){
				aux.add(cuadrante);
			}
		}
		return aux;
	
	}
}

