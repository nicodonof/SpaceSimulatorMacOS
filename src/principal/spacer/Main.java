package principal.spacer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Main extends BasicGame implements Screen {

	private Universo universo;

	private static Image back;
	
	private static float cameraX = 0;

	private static float cameraY = 0;
	
	private static int delta;

	public Main(String title) {
		super("Java Sandbox");
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(
					new Main("Java Sandbox"));
			app.setDisplayMode((int) ancho, (int) alto, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		Date tiempoAhora = new Date();
		
		back.draw(0,0);
		
		universo.administrarTiempo(delta);

		universo.creacionDeCuerpos(500, tiempoAhora, ancho, alto); 

		universo.administrarEstrellas(tiempoAhora);

		universo.posicionarTodo(tiempoAhora, delta);

		universo.chequearColisiones();

		InterfazGrafica.draw(universo, arg1, cameraX, cameraY);

	}

	public void init(GameContainer arg0) throws SlickException {
		back = new Image("Space1.png");
		
		File file = new File("Universo.save");
		if(!file.exists()){
			universo = new Universo();
		}else{	
		try
	      {
	         FileInputStream fileIn = new FileInputStream(file);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         universo = (Universo) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	         return;
	      }
		}		
	}

	public void update(GameContainer arg0, int arg1) throws SlickException {
		Input input = arg0.getInput();
		arg0.setSmoothDeltas(true);
		arg0.setTargetFrameRate(60);
		delta = arg1;
	
		if (input.isKeyDown(Input.KEY_UP)) {
			cameraY += -(delta + 3) / 3;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			cameraY += +(delta + 3) / 3;
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			cameraX += +(delta + 3) / 3;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			cameraX += -(delta + 3) / 3;
		}
		if (input.isMousePressed(0)) {
			if (input.getMouseX() <= 105 && input.getMouseX() >= 20
					&& input.getMouseY() >= 20 && input.getMouseY() <= 55) {

				File file = new File("Universo.save");

				FileOutputStream stream;
				try {
					stream = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(stream);

					out.writeObject(universo);

					out.close();
					stream.close();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
