package coordenaico.spacer;

import java.io.Serializable;

public class Punto implements Serializable{

	private double x=0;
	
	private double y=0;

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Punto(double x,double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	
	public String display(){
		int xInt = (int) this.x ;
		int yInt = (int) this.y;
		return "("+ xInt + "," + yInt + ")";
	}
	
	public String displayDouble(){
		return "("+ x + "," + y + ")";
	}
	
	public Punto suma(Punto otroPunto){
		Punto auxiliar = new Punto(0,0);
		auxiliar.setX(getX() + otroPunto.getX());
		auxiliar.setY(getY() + otroPunto.getY());	
		return auxiliar;
	}
	
	public Punto resta(Punto otroPunto){
		Punto auxiliar = new Punto(0,0);
		auxiliar.setX(getX() - otroPunto.getX());
		auxiliar.setY(getY() - otroPunto.getY());	
		return auxiliar;
	}
	
	public double moduloVector(){
		return Math.sqrt(Math.pow(getX(), 2)+ Math.pow(getY(), 2));
	}
	
	public Punto hacerVersor(){
		return this.divisionConstanteVector(this.moduloVector());
	}
	
	public Punto divisionConstanteVector(double constante){
		Punto auxiliar = new Punto(0,0);
		auxiliar.setX(this.x / constante);
		auxiliar.setY(this.y / constante);	
		return auxiliar;
	}
	
	public Punto multiplicacionConstanteVector(double constante){
		Punto auxiliar = new Punto(0,0);
		auxiliar.setX(getX() * constante);
		auxiliar.setY(getY() * constante);	
		return auxiliar;
	}
	
	public double distanciaEntrePuntos(Punto otroPunto){
		return Math.sqrt( Math.pow(getX() - otroPunto.getX(),2) + Math.pow(getY() - otroPunto.getY(),2) );
	}
	
	public double productoEscalar(Punto otroPunto){
		return (this.x * otroPunto.getX() + this.y * otroPunto.getY());
	}
	
	public Punto coordenadasDeDibujo(float ancho, float alto, float cameraX, float cameraY){
		Punto aux = new Punto((float) (getX() + ancho * 0.5 + cameraX),(float) (getY() + alto * 0.5 - cameraY));
		return aux;
	}


}
