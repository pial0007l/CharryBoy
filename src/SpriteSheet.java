import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage sheet;
	
	public SpriteSheet(){
		
	}
	
	public SpriteSheet(String path){
		try {
			sheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public BufferedImage getSprite(int xx,int yy){

		return sheet.getSubimage(xx, yy, xx+96, yy+96);
	
	}
}
