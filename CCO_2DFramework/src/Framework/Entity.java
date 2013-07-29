package Framework;

import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public abstract class Entity {
	
	protected String Name, Type;
	protected int Posx, Posy;
	protected float Speed,Life;
	protected int Sizex,Sizey;
	protected String Color;
	protected boolean[] DirectionalMovement;
	//Sprite
	protected int SpriteSizeX, SpriteSizeY;
	protected int SpritePosX, SpritePosY;
	protected float Linha, Coluna;
	//BoundingBox
	protected BoundingBox BB; protected int BBSizeX, BBSizeY;
	protected int BBOffsetX, BBOffsetY;
	//DeltaTime
	protected float CurrentDeltaTime;
	protected float Timer;
	//Projectile
	protected int ProjectilePosX,ProjectilePosY;
	protected boolean CanShoot = true;
	protected int ShootingCoolDown;
	
	//protected ResourcesLoader Resources;
	protected Texture EntityTexture;
	
	protected Random RandomGenerator = new Random();
	
	public Entity(){
		DirectionalMovement = new boolean[4];
	}
	
	public Entity(int _posx, int _posy, float _speed){
		Posx = _posx; Posy = _posy; Speed = _speed;
		DirectionalMovement = new boolean[4];
		//Default
		Sizex = 20; Sizey = 20; Life = 10.0f;
		BB = new BoundingBox(Posx,Posy,Sizex,Sizey);
	}
	
	public void LoadEntity(){
		
		Linha = SpritePosX / 16;
		Coluna = SpritePosY % 16;		
		
	}
	
	
	public void Draw(){
		
		
		
		if(EntityTexture != null){
			
			GL11.glColor3f(1.0f,1.0f,1.0f);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			EntityTexture.bind();
			
			float u0 = Linha / 16;
			float u1 = (Linha + 1) / 16;

			float v0 = Coluna / 16;
			float v1 = (Coluna + 1) / 16; 
			 
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(u0,v0);
			GL11.glVertex2f(Posx,Posy);
			GL11.glTexCoord2f(u1,v0);
			GL11.glVertex2f(Posx+Sizex,Posy);
			GL11.glTexCoord2f(u1,v1);
			GL11.glVertex2f(Posx+Sizex,Posy+Sizey);
			GL11.glTexCoord2f(u0,v1);
			GL11.glVertex2f(Posx,Posy+Sizey);
			GL11.glEnd();
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);

					 
		}else{
			
			// set the color of the quad (R,G,B,A)
			if(Color == "blue"){
				GL11.glColor3f(0.0f,0.0f,1.0f);
			}
			if(Color == "red"){
				GL11.glColor3f(1.0f,0.0f,0.0f);
			}
			
			
			// draw quad
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(Posx,Posy);
			GL11.glVertex2f(Posx+Sizex,Posy);
			GL11.glVertex2f(Posx+Sizex,Posy+Sizey);
			GL11.glVertex2f(Posx,Posy+Sizey);
			GL11.glEnd();		
			
		}
		
	}
	
	
	public void Update() {
		//BB.SetPos(Posx, Posy);
	}
	
	public void Kill(){
		//
	}
	
	
	public void Move(String _direction, float _speed){
		if(_direction == "rigth"){
			Translate(_speed,0);
		}
		
		if(_direction == "left"){
			Translate(-_speed,0);
		}
		
		if(_direction == "up"){
			Translate(0,-_speed);
		}
		
		if(_direction == "down"){
			Translate(0,_speed);
		}
	}
	
	public void CheckInputMovement(){
		if(DirectionalMovement[0] == true && Posx < (Display.getWidth()-BBSizeX)){
			Move("rigth",Speed);
		}
		if(DirectionalMovement[1] == true && Posy < (Display.getHeight()-BBSizeY)){
			Move("down",Speed);
		}
		if(DirectionalMovement[2] == true && Posx > 0){
			Move("left",Speed);
		}
		if(DirectionalMovement[3] == true && Posy > 0){
			Move("up",Speed);
		}		
	}
	
	public void Shoot(String _parent, String _projectile_type, float _dirX, float _dirY){
		if(_parent.equals("Enemy")){
			Game.GetCurrentLevel().AddProjectile(new Projectile(ProjectilePosX,ProjectilePosY,_dirX, _dirY,_projectile_type));
		}
		
		if(_parent.equals("Player")){
			Game.GetCurrentLevel().AddPlayerProjectile(new Projectile(ProjectilePosX,ProjectilePosY,_dirX, _dirY,_projectile_type));
		}
	}
	
	public void Translate(float _x, float _y){
		Posx += _x * CurrentDeltaTime;
		Posy += _y * CurrentDeltaTime;
	}
	
	public void LoadTexture(String _nameID){
		EntityTexture = ResourcesLoader.getInstance().GetTexture(_nameID);		
	}
	
	////
	
	public void ChangeLife(float _delta){
		Life += _delta;
	}
	
	public boolean IsAlive(){
		if(Life > 0){
			return true;
		}		
		return false;
	}
	
	public void SetLife(float _life){
		Life = _life;
	}
	
	public float GetLife() {
		return Life;
	}

	////
	
	public boolean CheckCollision(Entity _entity){
		return BB.CheckCollision(_entity.GetBB());
	}
	
	public boolean CheckCollision(BoundingBox _BB){
		return BB.CheckCollision(_BB);
	}
	
	public BoundingBox GetBB(){
		return BB;
	}
	
	public void SetDeltaTime(int _delta){
		CurrentDeltaTime = _delta;
	}
	
	
/////////
	
	
	public void DebugBoundingBox(String _color){
		
		// set the color of the quad (R,G,B,A)
		if(_color == "blue"){
			GL11.glColor3f(0.0f,0.0f,1.0f);
		}
		if(_color == "red"){
			GL11.glColor3f(1.0f,0.0f,0.0f);
		}
					 
					// draw quad
					GL11.glBegin(GL11.GL_QUADS);
					GL11.glVertex2f(BB.GetPosx(),BB.GetPosy());
					GL11.glVertex2f(BB.GetPosx()+BB.GetSizex(),BB.GetPosy());
					GL11.glVertex2f(BB.GetPosx()+BB.GetSizex(),BB.GetPosy()+BB.GetSizey());
					GL11.glVertex2f(BB.GetPosx(),BB.GetPosy()+BB.GetSizey());
					GL11.glEnd();
		
	}
	
	public boolean WaitForTime(float _time){
		Timer += 1*CurrentDeltaTime;
		
		if(Timer >= _time){
			Timer = 0;
			return true;
		}
		
		return false;
	}
}
