package principal.spacer;

import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import coordenaico.spacer.Punto;

import cuerpos.spacer.Asteroide;
import cuerpos.spacer.CuerpoCeleste;
import cuerpos.spacer.Estrella;
import cuerpos.spacer.Satelite;
import cuerpos.spacer.Planeta;
import cuerpos.spacer.Supernova;

public class InterfazGrafica implements Screen{
	
	private static final int AJUSTAR_FECHA = 60;

	private static void drawCenteredCircle(Graphics g,float x, float y, int r) {
		  x = x-(r/2);
		  y = y-(r/2);
		  g.fillOval(x,y,r,r);
	}
	
	private static void dibujarPasados(Graphics graphics,CuerpoCeleste entrada, float cameraX, float cameraY){
		//Dibuja los planetas o estrellas fuera de pantalla
		boolean estaAfuera = false; 
		float posiX = (float) (entrada.getPosicion().getX() + ancho * 0.5 + cameraX );
		float posiY = (float) (entrada.getPosicion().getY() + alto * 0.5 - cameraY );

		if(entrada.getPosicion().getX() > (ancho / 2) - cameraX){
			posiX=ancho;
			estaAfuera = true;
		}

		else if(entrada.getPosicion().getX() < - (ancho / 2 ) - cameraX ){
			posiX=0;
			estaAfuera = true;
		}
		
		if(entrada.getPosicion().getY() > (alto / 2 ) + cameraY ){
			posiY = alto;
			estaAfuera = true;
		}

		else if(entrada.getPosicion().getY() < - (alto / 2 ) + cameraY ){
			posiY = 0;
			estaAfuera = true;
		}
		if(estaAfuera){
			drawCenteredCircle(graphics,posiX, posiY, 20);
		}
	}
	
	private static String decirFecha(int dias){
		if(dias == 0){
			return "Nacimiento del Universo";
		}else if (dias == 1){
			return "1 dia";
		}else if(dias < 365){
			return dias + " dias";
		}else if(dias / 365 == 1) {
			return String.valueOf(dias / 365) + " año y " + dias % 365 + " dias";
		} else {
			return String.valueOf(dias / 365) + " años y " + dias % 365 + " dias";
		}
	}
	
	private static void dibujarCuerpos(Graphics graphics,float cameraX, float cameraY, Universo uni) {
		/*
		 * Esta funcion realiza un ciclo y dibuja todos los cuerpos en el array.  
		 */
		for (CuerpoCeleste cuerpo: uni.cuerpos) {
			if(cuerpo instanceof Estrella){
				graphics.setColor(Color.yellow);
				dibujarPasados(graphics,cuerpo,cameraX,cameraY);
				graphics.drawString(cuerpo.getNombre(),(float) (cuerpo.getPosicion().getX()  + ancho * 0.5 - 25 + cameraX), (float) (cuerpo.getPosicion().getY() + alto * 0.5 - cuerpo.getRadio()) - cameraY);
			}else if(cuerpo instanceof Satelite){
				graphics.setColor(Color.blue);
			}else if(cuerpo instanceof Asteroide){
				graphics.setColor(Color.red);
			}else if(cuerpo instanceof Supernova){
				graphics.setColor(Color.cyan.darker());
			}else if(cuerpo instanceof Planeta){
				graphics.setColor(Color.white);
				dibujarPasados(graphics,cuerpo,cameraX,cameraY);
				Punto aux = cuerpo.getPosicion().coordenadasDeDibujo(ancho, alto, cameraX, cameraY);
				graphics.drawString(cuerpo.getNombre(),(float) (aux.getX() - 25),(float) (aux.getY() - cuerpo.getRadio() - 10));
			}
			
			drawCenteredCircle(graphics,(float) (cuerpo.getPosicion().getX() + ancho * 0.5 + cameraX),(float) (cuerpo.getPosicion().getY() + alto* 0.5 - cameraY), cuerpo.getRadio());
		}
	}
	
	private static void dibujarInfos(Graphics graphics, float cameraX, float cameraY, InfoBox eventos, InfoBox informacionGeneral,
			ArrayList<Planeta> planetas, ArrayList<Estrella> estrellas, double contadorGeneral){
		eventos.displayBox(graphics);
		if(planetas.size() == 0){
			informacionGeneral.agregaLinea("Planeta mas viejo: " + "--");
		}else{
			informacionGeneral.agregaLinea("Planeta mas viejo: " + planetas.get(0).getNombre());
		}
		informacionGeneral.agregaLinea("Cantidad de Planetas: " + planetas.size());
		informacionGeneral.agregaLinea("Cantidad de Estrellas: " + estrellas.size());
		informacionGeneral.agregaLinea("Edad del universo: " + decirFecha((int) contadorGeneral / AJUSTAR_FECHA));

		informacionGeneral.displayBox(graphics);
	}
	
	public static void draw(Universo univerz ,Graphics graphics, float cameraX,float cameraY){
		dibujarInfos(graphics, cameraX, cameraY, univerz.eventos, univerz.informacionGeneral, univerz.cuerpos.planetas(), univerz.cuerpos.estrellas(), univerz.contadorGeneral);
		
		dibujarCuerpos(graphics,cameraX, cameraY, univerz);
			
		dibujarBotonGuardar(graphics, cameraX, cameraY);
	}

	private static void dibujarBotonGuardar(Graphics graphics, float cameraX,
			float cameraY) {
		graphics.setColor(Color.white);
		graphics.drawRect(20, 20, 85, 35);
		graphics.drawString("Guardar", 30, 27);
		
	}
	
}
