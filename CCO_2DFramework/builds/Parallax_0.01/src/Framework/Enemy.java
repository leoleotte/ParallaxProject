package Framework;

import org.lwjgl.opengl.Display;

public class Enemy extends Entity {
	
	private int AITime = 0;
	private int AIResponseTime = 0;

	public Enemy(float _posx, float _posy, float _speed,String _name, String _type){
		Posx = _posx; Posy = _posy; Speed = _speed;
		DirectionalMovement = new boolean[4];
		Color = "blue";
		
		Name = _name;
		Type = _type;
		
		
		CheckEnemyType();
	}
	
	public void CheckEnemyType(){
		
		int _BBSizex, _BBSizey;
		
		if(Type.equals("Normal")){		 
		 
			Sizex = 64; Sizey = 64;
			_BBSizex = 38; _BBSizey = 55;
			
			BB = new BoundingBox(Posx,Posy,_BBSizex,_BBSizey);
			
			LoadTexture("Enemy1");
		}
	}
	
	public void Update(){		
		
		AI();
		Movement();
		BB.SetPos(Posx, Posy);
		Draw();
		
		
	}
	
	public void AI(){
		
		if(AITime == 0){
			AIResponseTime = RandomGenerator.nextInt(20);
		}
		AITime ++;
		
		if(AITime >= AIResponseTime){
			for(int i = 0; i < 4; i ++){
				DirectionalMovement[RandomGenerator.nextInt(4)] = RandomGenerator.nextBoolean();
			}
			AITime = 0;
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
