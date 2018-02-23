import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;







public class Game extends Canvas implements Runnable,KeyListener{
	
	
	private static final long serialVersionUID = 1L;

	private boolean isRunning=false;
	
	public static int WIDTH=960,HEIGHT=660;
	public static final String TITLE="Cherry Boy";
	public static BufferedImage test;
	private Thread thread;
	public static Player player;
	public static Level level;
	public static Tile tile;
	public static SpriteSheet spritesheet,spritesheet2,spritesheet3,spritesheet4,spritesheet5,spritesheet6;
	
	public Game(){
		Dimension dimension = new Dimension(Game.WIDTH,Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		
		addKeyListener(this);
		
		
	}
	private void init(){
		player= new Player(Game.WIDTH/2,Game.HEIGHT/2);
		test = ImageLoader.loadImage("/background/background2.jpg");
		level= new Level("/map/map2.png");
		spritesheet= new SpriteSheet("/sprite/sheet.png");
		spritesheet2= new SpriteSheet("/sprite/heroright.png");
		spritesheet3= new SpriteSheet("/sprite/heroup.png");
		spritesheet4= new SpriteSheet("/sprite/monkeyleft.png");
		spritesheet5= new SpriteSheet("/sprite/cherry.png");
		spritesheet6= new SpriteSheet("/sprite/monkeyRight.png");
		
		
	}
	
	
	//}
	public synchronized void start(){
		if(isRunning) return;
		isRunning =true;
		thread =new Thread(this);
		thread.start();
		
	}
	public synchronized void stop(){
		if (!isRunning)return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void tick() {
		player.tick();
		level.tick();
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		
		Graphics g = bs.getDrawGraphics();
		///////////////Drawing///////////////////
		g.clearRect(0, 0, Game.WIDTH,Game.HEIGHT);
		g.drawImage(test, 0, 0, null);
		//////////////End Drawing///////////////////
		player.render(g);
		level.render(g);
		g.dispose();
		bs.show();
	}

	
	
	
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	

	public static void main(String[]args){
		Game game =new Game();
		JFrame frame=new JFrame(TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	
	}


	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			player.right=true;
			player.still=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			player.left=true;
			player.still=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			player.up=true;
			player.still=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			player.down=true;
			player.still=false;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			player.right=false;
			player.still=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				player.left=false;
				player.still=true;
			}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			player.up=false;
			player.still=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				player.down=false;
				player.still=true;
			}
		
	}


	public void keyTyped(KeyEvent e) {
		
	}

}
