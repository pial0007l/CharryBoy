import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JOptionPane;

public class Player extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	public boolean right,left,up,down,still=true;
	private int speed = 4;
	
	public Player(){
		
	}
	
	public Player(int x,int y){
		setBounds(x, y,28,28);
	}
	
	public void tick(){
		if(right && canMove(x+0,y))x+=0;
		if(right && canMove(x+speed,y))x+=speed;
		if(left && canMove(x-speed,y))x-=speed;
		if(up && canMove(x,y-speed))y-=speed;
		if(down & canMove(x,y+speed))y+=speed;
		
		Level level = Game.level;
		for(int i=0;i<level.cherry.size();i++){
			if(this.intersects(level.cherry.get(i))){
				level.cherry.remove(i);
				break;
			}
		}
		if(level.cherry.size()==0){
			this.level2();
			
			
		}
		for(int i=0;i<level.monkey.size();i++){
			if(this.intersects(level.monkey.get(i))){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				if (JOptionPane.showConfirmDialog(null, "Game Over\nWant to play again", "Game Over",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				    // yes option
					Game.player =new Player(0,0);
					Game.level=new Level("/map/map2.png");
					Game.test = ImageLoader.loadImage("/background/background2.jpg");
					// this.remove(dialogButton);
				} else {
				    // no option
					 System.exit(0);
				}
			}
		}
	}
	private void level2() {
		Game.player =new Player(0,0);
		Game.level=new Level("/map/map.png");
		Game.test = ImageLoader.loadImage("/background/background.jpg");
	}

	private boolean canMove(int nextx,int nexty){
		Rectangle bounds =new Rectangle(nextx, nexty, width, height);
		Level level = Game.level;
		for (int xx=0;xx<level.tiles.length;xx++){
			for(int yy=0;yy<level.tiles[0].length;yy++){
				if(level.tiles[xx][yy]!=null){
					if(bounds.intersects(level.tiles[xx][yy])){
							return false;
					}
					
				}
			}
		}
		
		
		
		
		return true;
	}
	
	public void render(Graphics g){
		SpriteSheet sheet = Game.spritesheet;
		SpriteSheet sheet2 = Game.spritesheet2;
		SpriteSheet sheet3 = Game.spritesheet3;
		if(still){
			g.drawImage(sheet.getSprite(0, 0), x, y,28,28, null);
		}else if(left){
			g.drawImage(sheet.getSprite(0, 0), x, y,28,28, null);
		}else if(right){
			g.drawImage(sheet2.getSprite(0, 0), x, y,28,28, null);
		}else if(up){
			g.drawImage(sheet3.getSprite(0, 0), x, y,28,28, null);
		}else if(down){
			g.drawImage(sheet3.getSprite(0, 0), x, y,28,28, null);
		}
	}

}
