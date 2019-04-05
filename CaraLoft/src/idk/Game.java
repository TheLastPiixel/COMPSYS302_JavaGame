package idk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.swing.JPanel;

import states.GameStateManager;

public class Game extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 3198107945095319469L;
	public int width, height;
	
	private Thread thread;
	
	private boolean running = false;
	
	private BufferedImage img;
	private Graphics2D g;
	
	private MouseHandler mouse;
	private KeyHandler key;
	private GameStateManager gsm;
	
	
	public Game(int width, int height) {
		
		this.width = width;
		this.height = height;
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {  // GAME LOOP
		super.addNotify();
		
		if(thread == null) {
			thread = new Thread(this, "Game Thread");
			thread.start();
		}
	}
	
	public void init() {
		running = true;
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) img.getGraphics();  // this graphic is drawing onto the buffer image
		
		mouse = new MouseHandler();
		key = new KeyHandler();
		gsm = new GameStateManager();
	}
	
	public void run() {
		init();
		
		final double GAME_HERTZ = 30.0; // in full caps to signify it wont be changed
		final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ; // time before update
		
		final int MAX_UPDATES = 5; // Max updates before render
		
		double lastUpdateTime = System.nanoTime();
		double lastRenderTime;
		
		final double TARGET_FPS = 30.0;
		final double TOTAL_TIME_BEFORE_RENDER = 1000000000 / TARGET_FPS;
		
		int frameCount = 0;
		int lastSecondTime = (int) (lastUpdateTime / 1000000000);
		int oldFrameCount = 0;
		
		while(running) {
			double now = System.nanoTime();
			
			int updateCount = 0;
			
			// Do as many game updates as we need to, potentially playing catchup
			// Makes it update at the correct frequency
			// Make sure we have updated enough times
			while ((now - lastUpdateTime) > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES) {
				update();
				input(mouse, key);
				lastUpdateTime += TIME_BETWEEN_UPDATES;
				updateCount++;
			}
			
			// If for some reason it is taking too long, we don't want to do an insane number of catchups
			if((now - lastUpdateTime) > TIME_BETWEEN_UPDATES) {
				lastUpdateTime = now - TIME_BETWEEN_UPDATES;
			}
			input(mouse, key);
			render();
			draw();
			lastRenderTime = now;
			frameCount++;
			
			int thisSecond = (int) (lastUpdateTime / 1000000000);
			if(thisSecond > lastSecondTime) {
				if(frameCount != oldFrameCount) {
					System.out.println("NEW SECOND " + thisSecond  + " " + frameCount);
					oldFrameCount = frameCount;
				}
				frameCount = 0;
				lastSecondTime = thisSecond;
			}
			
			
			// Yields until it has been at least the target time before renders. To let the cpu chill.
			while((now - lastRenderTime) < TOTAL_TIME_BEFORE_RENDER && (now - lastUpdateTime) < TIME_BETWEEN_UPDATES) {
				Thread.yield();
				
				
				// Stop the game from taking all ur cpu !! can cause stuttering
				try {
					Thread.sleep(1);			
				} catch (Exception e) {
					System.out.println("ERROR: yielding thread");
				}
				now = System.nanoTime();
			}
			
		}	
	}
	
	public void update() {
		gsm.update();
		
	}
	public void input(MouseHandler mouse, KeyHandler key) {
		gsm.input(mouse, key); // allows all the states to use the mouse and key
	}
	
	
	public void render() {
		if(g != null) {
			g.setColor(new Color(66, 134, 244));
			g.fillRect(0, 0, width, height); // creates a rectangle of the above color
			gsm.render(g);
		}
		
	}
	
	public void draw() {
		Graphics g2 = (Graphics) this.getGraphics(); // call the getGraphics in JPanel
		g2.drawImage(img, 0, 0, width, height, null);
		g2.dispose();
	}
}
