package Framework;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;

public class Level {
	
	private ArrayList<Enemy> Enemies;
	private ArrayList<Player> Players;
	int time;
	int EntityID;
	Random RandomGenerator = new Random();
	
	TrueTypeFont GUIFont;
	
	public Level(){
		Enemies = new ArrayList<Enemy>();
		Players = new ArrayList<Player>();
		time = 0;
		
		Players.add(new Player((Display.getWidth()/2),(Display.getHeight()/2),4.0f,"rsc/sprites/player.png",64,64,38,55,"Veotte","Player"));
		
		///GUI
		Font awtFont = new Font("Times New Roman",Font.BOLD, 24);
		GUIFont = new TrueTypeFont((java.awt.Font) awtFont, false);
	}
	
	public void Update(){		
		///Enemy Creation Test///
		time ++;
		
		if(time >= 100){
			Enemies.add(new Enemy(RandomGenerator.nextInt(800),RandomGenerator.nextInt(600),3.0f,("Enemy"+EntityID),"Normal"));
			time = 0;
			System.out.println("Enemies: "+Enemies.size());
			EntityID ++;
		}
		/////
		
		
		Draw();
		CheckCollisions();
		
		DrawGUI();
	}
	
	public void Draw(){
		for(int i = 0; i < Enemies.size(); i ++){
			Enemies.get(i).Update();
		}
		for(int i = 0; i < Players.size(); i ++){
			Players.get(i).Update();
		}
	}
	
	public void DrawGUI(){
		
		//GUIFont.drawString(0, 500, "THE LIGHTWEIGHT JAVA GAMES LIBRARY", Color.white);	
		
	}
	
	public void CheckCollisions(){
		
		for(int i = 0; i < Enemies.size(); i ++){
			for(int j = 0; j < Players.size(); j ++){
				if(Players.get(j).CheckCollision(Enemies.get(i))){
					Enemies.remove(i);
				}
			}
		}
		
	}

}
