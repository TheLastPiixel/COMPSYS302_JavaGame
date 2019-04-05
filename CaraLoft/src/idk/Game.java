package idk;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable{
	
	private static final long serialVersionUID = 3198107945095319469L;
	public int width, height;
	
	private Thread thread;
	
	private boolean running = false;
	
	private BufferedImage img;
	private Graphics2D g;
	
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
		g = (Graphics2D) img.getGraphics();
	}
	
	public void run() {
		init();
		
		while(running) {
			update();
			render();
			draw();
		}	
	}
	public void update() {

	}
	public void render() {
		
	}
	public void draw() {
		
	}
}
