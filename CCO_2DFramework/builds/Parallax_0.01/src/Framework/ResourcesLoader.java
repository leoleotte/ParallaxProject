package Framework;

import java.io.IOException;
import java.util.ArrayList;

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
	private String NameIDs[] = {"Player","Enemy1"};
	private boolean AllTexturesLoaded = false;

	
	private ResourcesLoader(){
		
	}
	
	private void LoadTextures(){
		
		textures = new ArrayList<Texture>();
				
			try {
	
			textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("rsc/sprites/player.png")));
			textures.add(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("rsc/sprites/enemy.png")));
			 
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			AllTexturesLoaded = true;
			System.out.println("Loaded "+textures.size()+" Textures!");		
	}
	
	public Texture GetTexture(String _textureName){
		
		if(!AllTexturesLoaded){
			LoadTextures();
		}
		
		for(int i = 0; i < textures.size(); i ++){	
			if(_textureName.equals(NameIDs[i])){
				return textures.get(i);
			}
		}
		
		System.out.println("Could not find Texture:"+_textureName);
		//System.exit(-7);
		return null;
	}

}
