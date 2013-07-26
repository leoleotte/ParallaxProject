package Framework;

public class BoundingBox {
	
	private int Sizex, Sizey;
	private float Posx, Posy;
	
	public BoundingBox(float _posx, float _posy, int _sizex, int _sizey){
		Posx = _posx;
		Posy = _posy;

		Sizex = _sizex;
		Sizey = _sizey;
	}
	
	public boolean CheckCollision(BoundingBox _BB){
		
		if(Posx+Sizex < _BB.GetPosx()){		
			return false;
		}
		if(Posy > (_BB.GetPosy()+_BB.GetSizey())){
			return false;
		}
		if(Posx > (_BB.GetPosx()+_BB.GetSizex())){
			return false;
		}
		if(Posy+Sizey < (_BB.GetPosy())){
			return false;
		}
		
		return true;
	}
	
	
	public void SetPos(float _x, float _y){
		Posx = _x;
		Posy = _y;
	}
	
	public void SetPosX(float _posx) {
		Posx = _posx;
	}
	
	public void SetPosy(float _posy) {
		Posy = _posy;
	}
	
	public float GetPosx() {
		return Posx;
	}

	public float GetPosy() {
		return Posy;
	}
	
	
	public int GetSizex() {
		return Sizex;
	}

	public int GetSizey() {
		return Sizey;
	}

	
	

}
