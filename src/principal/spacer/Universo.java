package principal.spacer;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import coordenaico.spacer.ArrayCuadrant;
import coordenaico.spacer.Cuadrante;
import coordenaico.spacer.Punto;


import cuerpos.spacer.ArrayCuerpoCeleste;
import cuerpos.spacer.Asteroide;
import cuerpos.spacer.CuerpoCeleste;
import cuerpos.spacer.Estrella;
import cuerpos.spacer.Satelite;
import cuerpos.spacer.Planeta;
import cuerpos.spacer.Supernova;

public class Universo implements Screen,Serializable{

	private static final int ESTRELLAS_POR_PLANETA = 5;

	private static final int MAXIMO_PLANETAS_POR_CUADRANTE = 2;

	private static final double AJUSTAR_DELTA = 0.075;

	protected double contadorGeneral = 0;

	protected ArrayCuerpoCeleste cuerpos = new ArrayCuerpoCeleste();
	
	private ArrayCuadrant cuadrantes = new ArrayCuadrant();
	//Colocan el box en la esquina inferior izquierda
	protected InfoBox informacionGeneral = new InfoBox((float) (25),(float) (alto - 135), "Informacion General"); 
	//Colocan el box en la esquina inferior derecha
	protected InfoBox eventos = new InfoBox((float) (ancho - 385),(float) (alto - 135), "Eventos");
	
	private int cEstrella = 0;
	
	private int cPlaneta = 0;
	
	private ArrayList<String> nombresP = new ArrayList<String>();
	
	private ArrayList<String> nombresE = new ArrayList<String>();
	
	private ArrayList<String> nombresA = new ArrayList<String>();
	
	public Universo(){
		//PLANETAS
		nombresP.add("Adamtazi");
		nombresP.add("Earth");
		nombresP.add("Pluto");
		nombresP.add("Endres");
		nombresP.add("Konnohmaru");
		nombresP.add("Mondrian");
		nombresP.add("Upice");    
		nombresP.add("Tzucmaun");
		nombresP.add("Zeipel");
		nombresP.add("Zita");
		nombresP.add("Lane");
		nombresP.add("Kyye");
		nombresP.add("Billowen");
		nombresP.add("Matuteale");
		nombresP.add("Znylle");
		nombresP.add("RattZ");
		nombresP.add("JuaQuino");
		nombresP.add("VazQui");
		nombresP.add("Venus");
		nombresP.add("Mercury");
		nombresP.add("Saturn");
		nombresP.add("Mars");
		nombresP.add("Jupiter");
		nombresP.add("Uranus");
		nombresP.add("Neptune");
		
		//ESTRELLAS
		nombresE.add("Acamar");
		nombresE.add("Azha");
		nombresE.add("Betria");
		nombresE.add("Polaris");
		nombresE.add("Spica");
		nombresE.add("Thuban");
		nombresE.add("Zosma");
		nombresE.add("Kaus Media");
		nombresE.add("Furud");
		nombresE.add("Deneb");
		nombresE.add("Cheleb");
		nombresE.add("Sun");
		nombresE.add("Caph");
		nombresE.add("Kaus Australis");
		nombresE.add("Bellatrix");
		nombresE.add("Kaus Boreallis");
		nombresE.add("Minkar");
		nombresE.add("Lyra");
		nombresE.add("Yed prior");
		nombresE.add("Corvus");
		nombresE.add("Gemma");
		nombresE.add("Capella");
		nombresE.add("Andromeda");
		
		//ASTEROIDES
		nombresA.add("Haley");
		nombresA.add("Mat");
		nombresA.add("Lolz");
		
	}
	
	public void administrarEstrellas(Date tiempo){
		for (int i = 0; i < cuerpos.estrellas().size(); i++) {
			if((contadorGeneral > cuerpos.estrellas().get(i).getTiempoDeVida() ) && cuerpos.estrellas().size() > 1){
			
				Supernova superN = new Supernova(cuerpos.estrellas().get(i));
				cuerpos.remove(cuerpos.estrellas().get(i));
				cuerpos.add(superN);
			}
		}
		for (int i = 0; i < cuerpos.supernova().size(); i++) {
				((Supernova) cuerpos.supernova().get(i)).ajustarRadio();
				if(cuerpos.supernova().get(i).getRadio() <= 0){
					cuerpos.remove(cuerpos.supernova().get(i));
				}
		}
	}
	
	public void posicionarTodo(Date tiempo, int delta){
		for (CuerpoCeleste cuerpo : cuerpos) {
			cuerpo.posicionar(cuerpos,delta);
		}
	}
	
	
	/*	
	 * Esta funcion itera sobre el arreglo de cuerpos y chequea si chocan 2 cuerpos usando la propiedad que si la distancia entre
	 * los centros es menor que la suma de los radios entonces los cuerpos estan chocando. A su vez chequea que tipo de cuerpo
	 * chocando y realiza una accion apropiada.
	*/
	public void chequearColisiones() {
		for (int i=0; i < cuerpos.size();i++) {
			for (int j=0; j < cuerpos.size();j++) {
				if( ((CuerpoCeleste) cuerpos.get(i)).getPosicion().distanciaEntrePuntos(((CuerpoCeleste) cuerpos.get(j)).getPosicion()) < 
						((CuerpoCeleste) cuerpos.get(i)).getRadio() / 2 + ((CuerpoCeleste) cuerpos.get(j)).getRadio() / 2 
						&& !cuerpos.get(i).equals(cuerpos.get(j))){
						if(cuerpos.get(i) instanceof Estrella || cuerpos.get(i) instanceof Supernova){
							if( cuerpos.get(j) instanceof Planeta){
								for (Satelite sat : ((Planeta) cuerpos.get(j)).getMisSatelites()) { // convierte satelites a asteroides
									Asteroide asteroide = new Asteroide(sat,nombresA);
									cuerpos.add(asteroide);
									cuerpos.remove(sat);
								}
							}
							cuerpos.remove(cuerpos.get(j));
							break;
						}else if(cuerpos.get(j) instanceof Estrella || cuerpos.get(j) instanceof Supernova){
							if( cuerpos.get(i) instanceof Planeta){
								for (Satelite sat : ((Planeta) cuerpos.get(i)).getMisSatelites()) {// convierte satelites a asteroides
									Asteroide asteroide = new Asteroide(sat,nombresA);
									cuerpos.add(asteroide);
									cuerpos.remove(sat);
								}
							}
							cuerpos.remove(cuerpos.get(i));
							break;
						}else if(cuerpos.get(i) instanceof Asteroide){ 
							cuerpos.remove(cuerpos.get(i));
							break;
						}else if(cuerpos.get(j) instanceof Asteroide){
							cuerpos.remove(cuerpos.get(j));
							break;
						}else if(cuerpos.get(i) instanceof Planeta && cuerpos.get(j) instanceof Planeta){
							/*
							 * En el caso del choque entre planetas se considera el planeta más pequeño. El mismo se destruye
							 * si su radio es menor a 25. Si es mayor, se divide en 2 planetas de menor tamaño.
							 */
							Planeta rompible;
							if(((Planeta)  cuerpos.get(i)).getRadio() < ((Planeta) cuerpos.get(j)).getRadio()){
								rompible = (Planeta) cuerpos.get(i);
								cuerpos.remove(cuerpos.get(i));
							}else{
								rompible = (Planeta) cuerpos.get(j);
								cuerpos.remove(cuerpos.get(j));
							}
							int auxRadio = rompible.getRadio(); 	
							if(rompible.getRadio() > 25){ // Se crean 2 planetas menores y se los envia en direcciones opuestas
								Planeta aux = new Planeta((float) (rompible.getPosicion().getX() + 25),
										(float) rompible.getPosicion().getY() + 25,auxRadio/2,new Punto(125,125), rompible.getNombre() + " I");
								Planeta aux2 = new Planeta((float) (rompible.getPosicion().getX() - 25),
										(float) rompible.getPosicion().getY() - 25,auxRadio/2,new Punto(-125,-125), rompible.getNombre() + " II");
								cuerpos.add(aux);
								cuerpos.add(aux2);
							}
							for (Satelite sat : rompible.getMisSatelites()) {
								Asteroide asteroide = new Asteroide(sat,nombresA);
								cuerpos.add(asteroide);
								cuerpos.remove(sat);
							}
							break;
						}
					}
				}
			}
		}
		/*
		 * Esta función contiene la lógica de la creación y balanceo de cuerpos. Utiliza el contadorGeneral para agregar planetas y 
		 * estrellas en intervalos predefinidos para no sobrepoblar el espacio. Contiene heurísticas para evitar cuadrantes
		 * vacios o sobrepoblados y mejorar el balanceo general
		 */
	public void creacionDeCuerpos(int multiplicador, Date tiempoAhora, float ancho, float alto) {
		//Esta funcion crea los cuadrantes en caso de que no se hallan creado
		if(cuadrantes.isEmpty()){
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					Cuadrante cuandrantT = new Cuadrante(i*ancho,j*alto);
					cuadrantes.add(cuandrantT);
				}
			}
		}
		if((contadorGeneral / (ESTRELLAS_POR_PLANETA * multiplicador)) > cEstrella){ 
			Estrella estrella = null;
			if(cuadrantes.get(4).cuantasEstrellas(cuerpos.estrellas()) == 0){ //Siempre debe haber estrellas en el cuadrante central
				estrella = new Estrella(cuadrantes.get(4), nombresE);
			}else{  // Agrega estrellas en cuadrantes vacios 
				ArrayCuadrant sinEstrellas = cuadrantes.sinEstrellas(cuerpos.estrellas());
				if(sinEstrellas.size() != 0){ // por si todos los cuadrantes tienen estrellas
					estrella = new Estrella(sinEstrellas.get((int) (Math.random() * sinEstrellas.size())),nombresE);
				}
			}
			if(estrella != null){
				cuerpos.add(estrella);
				cEstrella++;
				eventos.agregaLinea("Se ha agregado una Estrella: " + estrella.getNombre());
			}
		}
		if((contadorGeneral / (multiplicador)) > cPlaneta){ //Agrega planetas 
			Planeta planeta = null;
			//Agrega planetas en cuadrantes con al menos una estrella y maximo 2 planetas
			ArrayCuadrant cnEstrellas = cuadrantes.conEstrellas(cuerpos.estrellas()).conHastaPlanetas(cuerpos.planetas(), MAXIMO_PLANETAS_POR_CUADRANTE);
			
			int auxRand = (int) (Math.random() * cnEstrellas.size());
			if(cnEstrellas.size() > 0){ // chequea si se puede agregar
				planeta = new Planeta(cuerpos.estrellas(),cnEstrellas.get(auxRand), nombresP);
				cuerpos.add(planeta); 
				for (int i = 0; i < 3; i++) {
					if(Math.random() * 100 > 10){ // Agrega entre 0 y 3 satelites
						Satelite satelite = new Satelite(planeta);
						cuerpos.add(satelite);
						planeta.getMisSatelites().add(satelite);
						}
				}
				eventos.agregaLinea("Nuevo planeta: " + planeta.getNombre());	
				cPlaneta++;
			}			
		}
	}
	public void administrarTiempo(int delta){
		contadorGeneral +=  AJUSTAR_DELTA * delta;
	}
}
