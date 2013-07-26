package Framework;

import org.lwjgl.opengl.Display;


public class Enemy extends Entity {
	
	
	private float ProjectileTime = 0;
	private int ProjectileResponseTime = 0;
	
	//AI
	String AIState;
	float AIDirX, AIDirY;
	int AIWaypointIndex;
	float AIWaypointXArray[], AIWaypointYArray[];
	
	//
	private float AITime = 0;
	private int AIResponseTime = 0;
	boolean CanDeSpawn = false;
	//

	public Enemy(int _posx, int _posy,String _name, String _type){
		Posx = _posx; Posy = _posy;
		DirectionalMovement = new boolean[4];
		Life = 10.0f;
		Color = "blue";
		
		Name = _name;
		Type = _type;
		
		
		CheckType();
	}
	
	public void CheckType(){
		
		
		if(Type.equals("Normal")){
		 
			Sizex = 64; Sizey = 64;
			Speed = 0.20f;
			
			//BB
			BBSizeX = 38; BBSizeY = 35;			
			BBOffsetX = 0; BBOffsetY = 10;
			BB = new BoundingBox(Posx+BBOffsetX,Posy+BBOffsetY,BBSizeX,BBSizeY);
			
			//AI
			AIState = "Semi-Curve";
			
			LoadTexture("Enemy1");
		}
		
		
		LoadAI();
	}
	
	public void Update(int _delta){
		
		SetDeltaTime(_delta);
		
		ProjectilePosX = Posx+10;
		ProjectilePosY = Posy;
		
		AI();
		CheckInputMovement();
		BB.SetPos(Posx, Posy);
		Draw();
		
		
	}
	
	public void Kill(){
		if(!CanDeSpawn){
			ResourcesLoader.getInstance().PlaySound("Explosion");
		}
	}
	
	public void LoadAI(){
		
		AIWaypointIndex = 0;
		System.out.println(AIState);
		
		if(AIState.equals("Semi-Curve")){
			
			AIWaypointXArray = new float[]{0,
					Display.getWidth()/3,
					Display.getWidth()/2,
					(Display.getWidth()/2)+(Display.getWidth()/3),
					Display.getWidth()
			};
			
			AIWaypointYArray = new float[]{0,
					Display.getHeight()/4,
					Display.getHeight()/5,
					Display.getHeight()/4,
					0
			};
		}		
	}
	
	public void AI(){
		
		if(AIState.equals("Semi-Curve")){
			
			if(NearPoint('x',Speed,AIWaypointXArray[AIWaypointIndex]) && NearPoint('y',Speed,AIWaypointYArray[AIWaypointIndex]) ){
				if(AIWaypointIndex < AIWaypointXArray.length-1){
					AIWaypointIndex ++;
				}else{
					DeSpawn();
				}
				System.out.println("Near!");
			}			
			
			AIDirX = (AIWaypointXArray[AIWaypointIndex]-Posx);
			AIDirY = (AIWaypointYArray[AIWaypointIndex]-Posy);
			
			Translate((GetSignal(AIDirX)*Speed),(GetSignal(AIDirY)* Speed));
			
			/*System.out.println("AIDirX:"+(1 % AIDirX)+" AIDirY:"+(1 % AIDirY));
			System.out.println("Posx:"+Posx+" Posy:"+Posy);
			System.out.println("XWaypoint:"+AIWaypointXArray[AIWaypointIndex]+" YWaypoint: "+AIWaypointYArray[AIWaypointIndex]+" Index:"+AIWaypointIndex);*/
		}
		else{
		
			if(AITime == 0){
				AIResponseTime = RandomGenerator.nextInt(20);
			}
			AITime +=0.25f * CurrentDeltaTime;
			
			if(AITime >= AIResponseTime){
				for(int i = 0; i < 4; i ++){
					DirectionalMovement[RandomGenerator.nextInt(4)] = RandomGenerator.nextBoolean();
				}
				AITime = 0;
			}			
		}
		
		//Shooting
		
		if(ProjectileTime == 0){
			ProjectileResponseTime = RandomGenerator.nextInt(500-300)+30;
		}
		ProjectileTime += 0.25f * CurrentDeltaTime;
		
		if(ProjectileTime >= ProjectileResponseTime){
			Shoot("Enemy","Normal",RandomGenerator.nextFloat()-0.5f,1);
			ProjectileTime = 0;
		}
		
	}
	
	public void DeSpawn(){
		CanDeSpawn = true;
		SetLife(0);
	}
	
	private boolean NearPoint(char _axis, float _range, float _point){
		
		switch(_axis){
			case 'x':
				if(Posx > _point-_range && Posx < _point+_range){
					return true;
				}
			break;
			case 'y':
				if(Posy > _point-_range && Posy < _point+_range){
					return true;
				}
			break;
		}
		
		return false;
	}
	
	private int GetSignal(float _num){
		if(_num > 0){
			return 1;
		}
		if(_num < 0){
			return -1;
		}
		return 0;
	}
}
