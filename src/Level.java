import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Level {

	public int width;
	public int height;
	public Tile[][] tiles;
	
	public List<Cherry> cherry;
	public List<Monkey> monkey;
	
	public Level(){
		
	}
	
	public Level(String path){
		cherry = new ArrayList<>();
		monkey =new ArrayList<>();
		
		try {
			BufferedImage map= ImageIO.read(getClass().getResource(path));
			this.width=map.getWidth();
			this.height=map.getHeight();
			int[] pixels =new int[width*height];
			tiles =new Tile[width][height];
			map.getRGB(0, 0, width, height, pixels,0, width);
		
			for(int xx=0;xx<width;xx++){
				for(int yy=0;yy<height;yy++){
					
					int val= pixels[xx+(yy*width)];
					if(val == 0xFF0000FF){
						//player
						Game.player.x = xx*30;
						Game.player.y = yy*30;
					}else if(val == 0xFF000000){
						//tile
					tiles[xx][yy] = new Tile(xx*30,yy*30,30,30);
					}else if(val == 0xFFFF0000){
						//monkey
						monkey.add(new Monkey(xx*30,yy*30));
					}else if(val == 0xFF00E100){
						cherry.add(new Cherry(xx*30,yy*30));
					}
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void tick(){
		for (int i=0;i<cherry.size();i++){
			cherry.get(i).tick();
		}
		for (int i=0;i<monkey.size();i++){
			monkey.get(i).tick();
		}
	}
	
	
	
	public void render(Graphics g){
		for(int x=0;x<width;x++){
			for(int y=0;y<height;y++){
				if(tiles[x][y]!= null)tiles[x][y].render(g);
			}
		}
		
		for (int i=0;i<cherry.size();i++){
			cherry.get(i).render(g);
		}
		for (int i=0;i<monkey.size();i++){
			monkey.get(i).render(g);
		}
	}
	
}
