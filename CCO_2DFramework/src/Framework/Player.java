package Framework;

import org.lwjgl.input.Keyboard;

public class Player extends Entity{
	
	private boolean[] ShootingKeys;
	
	
	public Player(int _posx, int _posy, float _speed, String _texturePath, int _sizex, int _sizey, int _BBSizex, int _BBSizey,String _name, String _type){
		Posx = _posx; Posy = _posy; Speed = _speed;
		DirectionalMovement = new boolean[4];
		ShootingKeys = new boolean[2];
		Sizex = _sizex; Sizey = _sizey;
		BBSizeX = _BBSizex; BBSizeY = _BBSizey;
		Color = "red";
		Life = 10.0f;
		ShootingCoolDown = 200;
		
		
		//BB
		BBOffsetX = 0; BBOffsetY = 10;
		BB = new BoundingBox(Posx+BBOffsetX,Posy+BBOffsetY,BBSizeX,BBSizeY);
		
		Name = _name;
		Type = _type;
	
		
		LoadTexture("Player");
	}
	
	
	public void Update(int _delta){
		
		SetDeltaTime(_delta);
		
		ProjectilePosX = Posx+10;
		ProjectilePosY = Posy;
		
		UpdateInput();
		CheckInputMovement();
		UpdateWeapons();
		BB.SetPos(Posx+BBOffsetX, Posy+BBOffsetY);
		Draw();
		
		
	}
	
	public void Kill(){
		ResourcesLoader.getInstance().PlaySound("Explosion");
	}
	
	public void UpdateInput(){
		
		while (Keyboard.next()) {
			
		    if (Keyboard.getEventKeyState()) {
		    	
		    	if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		    		DirectionalMovement[0] = true;
			        }			    
		        
		    	if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		    		DirectionalMovement[1] = true;
			        }	
		    	
		    	if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		    		DirectionalMovement[2] = true;
			        }	
		    	
		    	if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		    		DirectionalMovement[3] = true;
			        }
		    	if (Keyboard.getEventKey() == Keyboard.KEY_SPACE){
		    		if(CanShoot){
		    			PlayerShoot();
		    		}
		    	}
		    	if (Keyboard.getEventKey() == Keyboard.KEY_K){
		    		ShootingKeys[0] = true;
		    	}
		    	
		    	
		    }else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_D) {
		        	DirectionalMovement[0] = false;
		        }
		        
		        if (Keyboard.getEventKey() == Keyboard.KEY_S) {
		        	DirectionalMovement[1] = false;
		        }
		        
		        if (Keyboard.getEventKey() == Keyboard.KEY_A) {
		        	DirectionalMovement[2] = false;
		        }
		        
		        if (Keyboard.getEventKey() == Keyboard.KEY_W) {
		        	DirectionalMovement[3] = false;
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_K){
		    		ShootingKeys[0] = false;
		    	}
		    }
		}
	}
	
	public void UpdateWeapons(){
		
		if(ShootingKeys[0]){
			if(WaitForTime(100))
				Shoot("Player","Repeater",RandomGenerator.nextFloat()-0.5f,-1);
		}
		
		if(!CanShoot){
			if(WaitForTime(ShootingCoolDown))
				CanShoot = true;
		}
		
	}
	
	public void PlayerShoot(){
		Shoot("Player","Normal",0,-1);
		CanShoot = false;
	}
	
	
}
