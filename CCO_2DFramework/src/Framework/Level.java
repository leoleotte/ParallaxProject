package Framework;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;

public class Level {
	
	private ArrayList<Enemy> Enemies;
	private ArrayList<Player> Players;
	private ArrayList<Projectile> Projectiles;
	private ArrayList<Projectile> PlayerProjectiles;
	int time;
	int CurrentDeltaTime;
	int EntityID;
	Random RandomGenerator = new Random();
	
	
	//private ArrayList<Star> Stars;
	
	TrueTypeFont GUIFont;
	
	public Level(){
		Enemies = new ArrayList<Enemy>();
		Players = new ArrayList<Player>();
		Projectiles = new ArrayList<Projectile>();
		PlayerProjectiles = new ArrayList<Projectile>();
		time = 0;
		
		Players.add(new Player((Display.getWidth()/2),(Display.getHeight()/2),0.40f,"rsc/sprites/player.png",64,64,38,35,"Veotte","Player"));
		
		///GUI
		Font awtFont = new Font("Times New Roman",Font.CENTER_BASELINE, 16);
		GUIFont = new TrueTypeFont((java.awt.Font) awtFont, false);
		
		
		//Stars = new ArrayList<Star>();
		//LoadStars();
	}
	
	public void Update(int _delta){
		
		CurrentDeltaTime = _delta;
		
		///Enemy Creation Test///
		time += 1f * CurrentDeltaTime;
		
		if(time >= RandomGenerator.nextInt(2000)+500 && Enemies.size() < 10){
			//Enemies.add(new Enemy(RandomGenerator.nextInt(736),RandomGenerator.nextInt(536),("Enemy"+EntityID),"Normal"));
			Enemies.add(new Enemy(0,0,("Enemy"+EntityID),"Normal"));
			time = 0;
			
			EntityID ++;
		}
		//System.out.println("Time: "+time);
		/////
		
		
		UpdateEntities();
		CheckCollisions();
		
		//DrawStars();
		DrawGUI();		
	}
	
	public void UpdateEntities(){
		for(int i = 0; i < Projectiles.size(); i ++){
			if(Projectiles.get(i).IsAlive()){
				Projectiles.get(i).Update(CurrentDeltaTime);
			}else{
				Projectiles.get(i).Kill();
				Projectiles.remove(i);
			}
		}
		for(int i = 0; i < PlayerProjectiles.size(); i ++){
			if(PlayerProjectiles.get(i).IsAlive()){
				PlayerProjectiles.get(i).Update(CurrentDeltaTime);
			}else{
				PlayerProjectiles.get(i).Kill();
				PlayerProjectiles.remove(i);
			}
		}
//////////////
		for(int i = 0; i < Enemies.size(); i ++){
			if(Enemies.get(i).IsAlive()){
				Enemies.get(i).Update(CurrentDeltaTime);
			}else{
				Enemies.get(i).Kill();
				Enemies.remove(i);
			}
		}		
		for(int i = 0; i < Players.size(); i ++){
			if(Players.get(i).IsAlive()){
				Players.get(i).Update(CurrentDeltaTime);
			}else{
				Players.get(i).Kill();
				Players.remove(i);
			}
		}
	}
	
	/*public void LoadStars(){
		
		for(int i = 0; i < 5; i ++){
			Stars.add(new Star());
		}
	}
	
	public void DrawStars(){
		for(int i = 0; i < Stars.size(); i ++){
			Stars.get(i).Update();
		}
	}*/
	
	public void DrawGUI(){
		
		GUIFont.drawString(0, 0, "Enemies:"+Enemies.size());	
		GUIFont.drawString(0, 20, "Projectiles:"+(Projectiles.size()+PlayerProjectiles.size()));	
		
		if(Players.size() > 0)
			GUIFont.drawString((Display.getWidth()-70), 0, "Life:"+Players.get(0).GetLife());	
		
	}
	
	
	
	public void CheckCollisions(){
		
		for(int i = 0; i < Enemies.size(); i ++){
			for(int j = 0; j < Players.size(); j ++){
				if(Players.get(j).CheckCollision(Enemies.get(i))){
					Players.get(j).ChangeLife(-1);
					Enemies.get(i).SetLife(0);
				}
			}
		}
		
		for(int i = 0; i < Projectiles.size(); i ++){
			for(int j = 0; j < Players.size(); j ++){
				if(Players.get(j).CheckCollision(Projectiles.get(i))){
					Players.get(j).ChangeLife(-1);
					Projectiles.get(i).SetLife(0);
				}
			}
		}
		
		for(int i = 0; i < PlayerProjectiles.size(); i ++){
			for(int j = 0; j < Enemies.size(); j ++){
				if(Enemies.get(j).CheckCollision(PlayerProjectiles.get(i))){
					Enemies.get(j).ChangeLife(-10);
					PlayerProjectiles.get(i).SetLife(0);
				}
			}
		}
		
	}
	
	
	
	public void AddProjectile(Projectile _projectile){
		Projectiles.add(_projectile);
	}
	
	public void AddPlayerProjectile(Projectile _projectile){
		PlayerProjectiles.add(_projectile);
	}
	
	
//////////////////////////////////////////////////////






	public class Star extends Entity{
	
		private int[][] pixels;
		private int MaxWidth = 5, MaxHeight = 5;
		
		public Star(){
			for(int i = 0; i < MaxWidth; i ++){
				for(int j = 0; j < MaxHeight; j ++){
						pixels[i][j] = RandomGenerator.nextInt(1);
				}
			}
			
			Posx = RandomGenerator.nextInt((Display.getWidth()));
			Posy = RandomGenerator.nextInt((Display.getHeight()));
		}	
		
		public void Update(){
			for(int i = 0; i < MaxWidth; i ++){
					//GL11.glDrawPixels(10, 10, GL11.GL_COLOR_INDEX, GL11.GL_BYTE, Stars.get(i).pixels);

			}
		}
	}

}









