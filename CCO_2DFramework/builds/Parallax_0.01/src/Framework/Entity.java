package Framework;

import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public abstract class Entity {
	
	protected String Name, Type;
	protected float Posx, Posy;
	protected float Speed;
	protected int Sizex,Sizey;
	protected String Color;
	protected boolean[] DirectionalMovement;
	protected BoundingBox BB;
	
	//protected ResourcesLoader Resources;
	protected Texture EntityTexture;
	
	protected Random RandomGenerator = new Random();
	
	public Entity(){
		DirectionalMovement = new boolean[4];
	}
	
	public Entity(float _posx, float _posy, float _speed){
		Posx = _posx; Posy = _posy; Speed = _speed;
		DirectionalMovement = new boolean[4];
		Sizex = 20; Sizey = 20;
		BB = new BoundingBox(Posx,Posy,Sizex,Sizey);
	}
	
	
	public void Draw(){
		
		
		
		if(EntityTexture != null){
			
			GL11.glColor3f(1.0f,1.0f,1.0f);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			
			EntityTexture.bind(); // or GL11.glBind(texture.getTextureID());
			 
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(Posx,Posy);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(Posx+Sizex,Posy);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(Posx+Sizex,Posy+Sizey);
			GL11.glTexCoord2f(0,1);
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
	
	
	public void Move(String _direction, float _speed){
		if(_direction == "rigth"){
			Posx += _speed;
		}
		
		if(_direction == "left"){
			Posx -= _speed;
		}
		
		if(_direction == "up"){
			Posy -= _speed;
		}
		
		if(_direction == "down"){
			Posy += _speed;
		}
	}
	
	public void LoadTexture(String _nameID){
		EntityTexture = ResourcesLoader.getInstance().GetTexture(_nameID);		
	}

	///
	
	public boolean CheckCollision(Entity _entity){
		return BB.CheckCollision(_entity.GetBB());
	}
	
	public boolean CheckCollision(BoundingBox _BB){
		return BB.CheckCollision(_BB);
	}
	
	public BoundingBox GetBB(){
		return BB;
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

}
