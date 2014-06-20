package cuerpos.spacer;

import java.io.Serializable;
import java.util.ArrayList;

public class ArrayCuerpoCeleste extends ArrayList<CuerpoCeleste> implements Serializable{

	public ArrayCuerpoCeleste(){
		super();
	}
	
	public ArrayList<Estrella> estrellas(){
		ArrayList<Estrella> aux = new ArrayList<Estrella>();
		for (CuerpoCeleste cuerpo: this) {
			if(cuerpo instanceof Estrella){
				aux.add((Estrella) cuerpo);
			}
		}
		return aux;
	}
	
	public ArrayList<Planeta> planetas(){
		ArrayList<Planeta> aux = new ArrayList<Planeta>();
		for (CuerpoCeleste cuerpo: this) {
			if(cuerpo instanceof Planeta){
				aux.add((Planeta)cuerpo);
			}
		}
		return aux;
	}
	public ArrayCuerpoCeleste supernova(){
		ArrayCuerpoCeleste aux = new ArrayCuerpoCeleste();
		for (CuerpoCeleste cuerpo: this) {
			if(cuerpo instanceof Supernova){
				aux.add(cuerpo);
			}
		}
		return aux;
	}
	
}
