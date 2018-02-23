import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tile extends Rectangle{
	
	private static final long serialVersionUID = 1L;

	public Tile(){
		
	}
	public Tile(int x,int y,int i,int j){
		//System.out.println("Tile Running");
		setBounds(x, y, i,j);
	}
	
	
	public void render(Graphics g){
		
		 
		
		//if()
		int alpha =0;
		g.setColor(new Color(255, 0, 0, alpha));
		g.fillRect(x, y, width, height);
	}
}
