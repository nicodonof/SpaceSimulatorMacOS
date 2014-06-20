package principal.spacer;

import java.io.Serializable;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class InfoBox implements Serializable{
	
	private static final int ESPACIO_ENTRE_RENGLON = 25;
	ArrayList<String> lineas = new ArrayList<>();
	private float centroX;
	private float centroY;
	private String titulo;
	
	public InfoBox(float x, float y, String titulo) {
		super();
		centroX = x;
		centroY = y;
		this.titulo = titulo;
	}

	public void agregaLinea(String lineaNueva){
		if(lineas.size() > 0 && lineas.get(0).compareTo(lineaNueva) == 0)
			return;
		
			lineas.add(0, lineaNueva);
			if(lineas.size() == 5)
				lineas.remove(4);
	}
	
	public void displayBox(Graphics g){
		g.setColor(Color.white);
		g.drawString( titulo ,  centroX, centroY);
		
		for (String linea : lineas) {
			g.drawString("- " + linea, centroX , centroY + (lineas.indexOf(linea)+1) * ESPACIO_ENTRE_RENGLON); 
		}
		
	}
	
}
