package Framework;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Player extends Entity{
	
	
	public Player(float _posx, float _posy, float _speed, String _texturePath, int _sizex, int _sizey, int _BBSizex, int _BBSizey,String _name, String _type){
		Posx = _posx; Posy = _posy; Speed = _speed;
		DirectionalMovement = new boolean[4];
		Sizex = _sizex; Sizey = _sizey;
		Color = "red";
		BB = new BoundingBox(Posx,Posy,_BBSizex,_BBSizey);
		
		Name = _name;
		Type = _type;
		
			LoadTexture("Player");
	}
	
	
	public void Update(){		
		
		Input();
		Movement();
		BB.SetPos(Posx, Posy);
		Draw();
		
		
	}
	
	public void Input(){
		
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
		    }
		}		
	}
	
	public void Movement(){
		if(DirectionalMovement[0] == true && Posx < (Display.getWidth()-Sizex)){
			Move("rigth",Speed);
		}
		if(DirectionalMovement[1] == true && Posy < (Display.getHeight()-Sizey)){
			Move("down",Speed);
		}
		if(DirectionalMovement[2] == true && Posx > 0){
			Move("left",Speed);
		}
		if(DirectionalMovement[3] == true && Posy > 0){
			Move("up",Speed);
		}		
	}
}
