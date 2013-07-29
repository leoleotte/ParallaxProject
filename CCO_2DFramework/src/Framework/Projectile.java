package Framework;

import org.lwjgl.opengl.Display;

public class Projectile extends Entity {
	
	private float DirX, DirY;
	
	public Projectile(int _posx, int _posy,float _dirx, float _diry,String _type){
		Posx = _posx; Posy = _posy;
		Life = 10.0f;
		Color = "red";
		
		Type = _type;
		
		DirX = _dirx;
		DirY = _diry;
		
		CheckType();
		
		}
	
	public void CheckType(){
		
		int _BBSizex = 0, _BBSizey = 0;
		
		if(Type.equals("Normal")){
			Sizex = 64; Sizey = 64;
			_BBSizex = 12; BBOffsetX = 2;
			_BBSizey = 12; BBOffsetY = 2;
			Speed = 0.50f;
			
			LoadTexture("Projectiles");
		}else if(Type.equals("Repeater")){
			Sizex = 64; Sizey = 64;
			_BBSizex = 12; BBOffsetX = 2;
			_BBSizey = 12; BBOffsetY = 2;
			Speed = 0.60f;
			
			LoadTexture("Projectiles");
		}
		
		BB = new BoundingBox(Posx+BBOffsetX,Posy+BBOffsetY,_BBSizex,_BBSizey);
	}
	
	public void Update(int _delta){		
		SetDeltaTime(_delta);
		
		Movement();
		BB.SetPos(Posx+BBOffsetX, Posy+BBOffsetY);
		Draw();
		//DebugBoundingBox("red");
		
		
		CheckBoundaries();
	}
	
	private void Movement(){
		Translate(DirX*Speed,DirY*Speed);
	}
	
	private void CheckBoundaries(){
		if(Posx < 0 || Posx > Display.getWidth() || Posy < 0 || Posy > Display.getHeight()){
			SetLife(0);
		}
	}

}
