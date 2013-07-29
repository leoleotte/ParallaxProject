package Framework;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ResourcesLoader {
	
	private static ResourcesLoader instance = null;
	
	public static ResourcesLoader getInstance() {
	      if(instance == null) {
	         instance = new ResourcesLoader();
	      }
	      return instance;
	   }
	
	private ArrayList<Texture> textures;
	private ArrayList<Audio > sounds;
	private boolean AllTexturesLoaded = false;
	private boolean AllSoundsLoaded = false;
	
	//Textures_Names_IDS_Array
	private String TextureNameIDs[] = {"Ships","Projectiles"};
	private String SoundsNameIDs[] = {"Explosion"};


	
	private ResourcesLoader(){
		
	}
	
	public void LoadResources(){		
		LoadSounds();
		LoadTextures();
	}
	
	private void LoadTextures(){
		
		textures = new ArrayList<Texture>();
				
			try {
	
			textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("rsc/sprites/ships.png")));
			textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("rsc/sprites/projectiles.png")));
			 
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			AllTexturesLoaded = true;
			System.out.println("Loaded "+textures.size()+" Textures!");		
	}
	
	private void LoadSounds(){
		
		sounds = new ArrayList<Audio>();
		
		try {
			sounds.add(AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("rsc/sounds/explosion.wav")));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AllSoundsLoaded = true;
		System.out.println("Loaded "+sounds.size()+" Sounds!");		
	}
	
	public Texture GetTexture(String _textureName){
		
		if(!AllTexturesLoaded){
			LoadTextures();
		}
		
		for(int i = 0; i < textures.size(); i ++){	
			if(_textureName.equals(TextureNameIDs[i])){
				return textures.get(i);
			}
		}
		
		System.out.println("Could not find Texture:"+_textureName);
		//System.exit(-7);
		return null;
	}
	
	public boolean PlaySound(String _soundName){
		
		if(!AllSoundsLoaded){
			LoadSounds();
		}
		
		for(int i = 0; i < sounds.size(); i ++){	
			if(_soundName.equals(SoundsNameIDs[i])){
				sounds.get(i).playAsSoundEffect(1.0f, 1.0f, false);
				return true;
			}
		}
		
		System.out.println("Could not find Sound:"+_soundName);
		return false;
	}

}
