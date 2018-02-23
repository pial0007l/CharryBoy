import java.awt.Graphics;
import java.awt.Rectangle;

public class Cherry extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	
	public Cherry(){
		
	}

	public Cherry(int x,int y){
		
		setBounds(x, y, 30,30);
	}
	
	public void tick(){
		
	}
	
	
	public void render(Graphics g){
		SpriteSheet sheet = Game.spritesheet5;
		g.drawImage(sheet.getSprite(0, 0), x, y,28,28, null);
	}

}
