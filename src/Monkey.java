import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Monkey extends Rectangle{

	private static final long serialVersionUID = 1L;
	private int random=0,smart=1,find_path=2;
	private int state=smart;
	private int right=0,left=1,up=2,down=3;
	private int dir=-1;
	private int time=0;
	private int targetTime=60*4;
	private int spd=2 ;
	private int lastDir=-1;
	private boolean lef,rit;
	
	public Random rand;
	
	public Monkey(){
		
	}
	
	public Monkey(int x,int y){
		rand=new Random();
		setBounds(x, y, 28,28);
		dir=rand.nextInt(4);
	}
	
	public void tick(){
			if(state== random)
			{
				if(dir==right)
				{
					if(canMove(x+spd,y))
					{
						if(rand.nextInt(100)<50)x+=spd;
						rit=true;
						lef=false;
					}else
					{
						dir=rand.nextInt(4);
					}
				}else if(dir==left)
				{
					if(canMove(x-spd,y))
					{
						if(rand.nextInt(100)<50)x-=spd;
						rit=false;
						lef=true;
					}else
					{
						dir=rand.nextInt(4);
					}
				}else if(dir==up)
				{
					if(canMove(x,y-spd))
					{
						if(rand.nextInt(100)<50)y-=spd;
					}else
					{
						dir=rand.nextInt(4);
					}
				}else if(dir==down)
				{
					if(canMove(x,y+spd))
					{
						if(rand.nextInt(100)<50)y+=spd;
					}else
					{
						dir=rand.nextInt(4);
					}
				}
				time++;
				if(time==targetTime)
				{
					state = smart;
					time=0;
				}
			}else if(state==smart)
			{
				//follow the player
				boolean move =false;
				
				if(x < Game.player.x){
					if(canMove(x+spd,y)){
						if(rand.nextInt(100)<50)x+=spd;
						rit=true;
						lef=false;
						move=true;
						lastDir=right;
					}
				}
				if(x > Game.player.x){
					if(canMove(x-spd,y)){
						if(rand.nextInt(100)<50)x-=spd;
						rit=false;
						lef=true;
						move=true;
						lastDir=left;
					}
				}
				if(y<Game.player.y){
					if(canMove(x,y+spd)){
						if(rand.nextInt(100)<50)y+=spd;
						move=true;
						lastDir=down;
					}
				}
				if(y>Game.player.y){
					if(canMove(x,y-spd)){
						if(rand.nextInt(100)<50)y-=spd;
						move=true;
						lastDir=up;
					}
				}
				if(x==Game.player.x && y==Game.player.y)move=true;
				if(!move){
					state=find_path;
					
				}
				time++;
				if(time==random)
				{
					state = smart;
					time=0;
				}
			}else if(state == find_path){
				if(lastDir ==right){
					if(y<Game.player.y){
						if(canMove(x,y+spd)){
							if(rand.nextInt(100)<50)y+=spd;
							state=smart;
						}
					}else{
						if(canMove(x,y-spd)){
							if(rand.nextInt(100)<50)y-=spd;
							state=smart;
						}
					}
					if(canMove(x+spd,y)){
						if(rand.nextInt(100)<50)x+=spd;
						rit=true;
						lef=false;
					}
			}else if(lastDir==left){
				if(y<Game.player.y){
					if(canMove(x,y+spd)){
						if(rand.nextInt(100)<50)y+=spd;
						state=smart;
					}
				}else{
					if(canMove(x,y-spd)){
						if(rand.nextInt(100)<50)y-=spd;
						state=smart;
					}
				}
				if(canMove(x-spd,y)){
					if(rand.nextInt(100)<50)x-=spd;
					rit=false;
					lef=true;
				}
			}else if(lastDir==up){
				if(x<Game.player.x){
					if(canMove(x+spd,y)){
						if(rand.nextInt(100)<50)x+=spd;
						rit=true;
						lef=false;
						state=smart;
					}
				}else{
					if(canMove(x-spd,y)){
						if(rand.nextInt(100)<50)x-=spd;
						rit=false;
						lef=true;
						state=smart;
					}
				}
				if(canMove(x,y-spd)){
					if(rand.nextInt(100)<50)y-=spd;
				}
			}else if(lastDir==down){
				if(x<Game.player.x){
					if(canMove(x+spd,y)){
						if(rand.nextInt(100)<50)x+=spd;
						rit=true;
						lef=false;
						state=smart;
					}
				}else{
					if(canMove(x-spd,y)){
						if(rand.nextInt(100)<50)x-=spd;
						rit=false;
						lef=true;
						state=smart;
					}
				}
				if(canMove(x,y+spd)){
					if(rand.nextInt(100)<50)y+=spd;
				}
			}
				time++;
				if(time==random)
				{
					state = smart;
					time=0;
				}
			}
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
		
		SpriteSheet sheet = Game.spritesheet4;
		SpriteSheet sheet2 = Game.spritesheet6;
		if(lef==true){
		g.drawImage(sheet.getSprite(0, 0), x, y,28,28, null);
		}else{
			g.drawImage(sheet2.getSprite(0, 0), x, y,28,28, null);
		}
		
	}
}
