package Framework;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Game {
	
	private static Level[] Levels;
	private static int CurrentLevel;
	private long lastFPS;
	private int Fps;
	private long lastFrame;
	private int Width, Height;
	
	
	public void start() {
		
		Width = 800;
		Height = 600;
		
		try {
			Display.setDisplayMode(new DisplayMode(Width,Height));
			Display.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		Mouse.setGrabbed(true);
		
		lastFPS = getTime();
		getDelta();
		
		ResourcesLoader.getInstance().LoadResources();
		StartLevels();
		
		while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			
			// render OpenGL here
			
			UpdateLevel();

			Display.update();
			//Display.sync(500);
		}
		
		
		AL.destroy();
		Display.destroy();
	}
	
	public static void main(String[] argv) {
		Game Parallax = new Game();
		Parallax.start();
	}
	
	public void StartLevels(){

		// init OpenGL here
		
		/*GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);       
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_LIGHTING);                   
		 
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
		
		//GL11.glEnable(GL11.GL_BLEND);
		//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, Width, 0, Height, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);*/
		
		GL11.glMatrixMode (GL11.GL_PROJECTION);		 
		GL11.glLoadIdentity ();		 
		GL11.glOrtho (0, Width, Height, 0, 0, 1);		 
		GL11.glMatrixMode (GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		
		
		
		///////
		
		CurrentLevel = 0;
		
		Levels = new Level[1];
		Levels[CurrentLevel] = new Level();
		
	}
	
	public void UpdateLevel(){		
			
			// Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); 
			
			int delta = getDelta();
			
			Levels[0].Update(delta);
			
			UpdateFPS();						
	}
	
	public static Level GetCurrentLevel(){
		return Levels[CurrentLevel];
	}
	
	
	public void UpdateFPS() {
		if (getTime() - lastFPS > 1000) {
		Display.setTitle("FPS: " + Fps);
		Fps = 0; //reset the FPS counter
		lastFPS += 1000; //add one second
		}
		Fps++;
		}
	
	public long getTime() {
		return System.nanoTime() / 1000000;
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		 
		return delta;
		}
	
	
	
	
}