package cuerpos.spacer;



import java.io.Serializable;
import java.util.ArrayList;
import coordenaico.spacer.Punto;


public abstract class CuerpoCeleste implements Serializable{
	
	 private static final double AJUSTAR_DELTA = 0.0008;

	protected long tiempoDeCreacion;
	 
	 protected double masa ;
	 
	 protected int radio;

	 protected String nombre;
	 
	 protected Punto velocidadVector = new Punto(0,0);
	 
	 protected Punto posicion;
	 
	 protected Punto aceleracion = new Punto(0,0);
	
	 public Punto gravedad(ArrayCuerpoCeleste cuerpos){
		  Punto fuerzaTotalEstrella = new Punto(0,0);
		  Punto fuerzaTotalPlaneta = new Punto(0,0);
		  Punto fuerzaTotal = new Punto(0,0);
		  
		  for (CuerpoCeleste cuerpo : cuerpos) {
			  double distanciaEntreEstrellaYPlaneta = this.posicion.distanciaEntrePuntos(cuerpo.getPosicion()); 
			  if(!cuerpo.equals(this) && !(cuerpo instanceof Satelite)){
				  Punto vector = cuerpo.getPosicion().resta(posicion);
				  Punto versor = vector.hacerVersor();
				  Punto fuerza = versor.multiplicacionConstanteVector(((cuerpo.getMasa() * masa *  Math.pow(10,5))/ Math.pow(distanciaEntreEstrellaYPlaneta,2)));
				  if(fuerza.getX() != Double.NaN && fuerza.getY() != Double.NaN )
					  fuerzaTotalPlaneta = fuerzaTotalPlaneta.suma(fuerza);
			  }
		}

		fuerzaTotal = fuerzaTotalEstrella.suma(fuerzaTotalPlaneta);
		return fuerzaTotal;
	 }
	 
	 public void posicionar(ArrayCuerpoCeleste cuerpos, int delta){
		double tiempoCasteado = AJUSTAR_DELTA * delta;
		Punto fuerzaTotal = new Punto(0,0);
		fuerzaTotal = fuerzaTotal.suma(this.gravedad(cuerpos));
		aceleracion = fuerzaTotal.divisionConstanteVector(masa);
		velocidadVector = (velocidadVector.suma(aceleracion.multiplicacionConstanteVector(tiempoCasteado)));
		posicion = posicion.suma(velocidadVector.multiplicacionConstanteVector(tiempoCasteado));
	}
	
	
	public Punto getPosicion() {
		return posicion;
	}

	public void setPosicion(Punto posicion) {
		this.posicion = posicion;
	}
	
	public Punto getVelocidadVector() {
		return velocidadVector;
	}
	
	public void setVelocidadVector(Punto velocidadVector) {
		this.velocidadVector = velocidadVector;
	}
	
	public long getTiempoDeCreacion() {
	  return tiempoDeCreacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getRadio() {
		return radio;
	}
	 
	public double getMasa() {
		 return masa;
	}
	 
	public String nombrar(ArrayList<String> nombres){
		if(nombres.size() != 0){
			int index = (int) (Math.random() * nombres.size());
			return nombres.remove(index);
		}else{
			return "Unnamed";
		}
	}
}
