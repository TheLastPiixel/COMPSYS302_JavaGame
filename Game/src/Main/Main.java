package Main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Graphics.SpriteSheet;
import Graphics.Sprites;
import Graphics.TextureLoader;

public class Main implements Runnable {
	
	private Thread GameThread;
	private Display Display;
	private BufferStrategy BuffStrat;
	private Graphics GameGraphics;
	private boolean Operating = false;
	private int x;


	public int Height;
	public int Width;
	public String Title;

	
	public Main(String Title, int Width, int Height) { //Constructor
		this.Title = Title;
		this.Width = Width;
		this.Height = Height;
	}
	
	private void Render() { //Draws to screen
		BuffStrat = Display.Canvas.getBufferStrategy();
		if (BuffStrat == null) {
			//Buffers twice
			Display.Canvas.createBufferStrategy(3); 
			return;
		}
		
		//Gets buffered image to draw on canvas
		GameGraphics = BuffStrat.getDrawGraphics();
		
		//Clears the screen
		GameGraphics.clearRect(0, 0, Width, Height);
		
		//Draw to canvas 
		GameGraphics.drawImage(Sprites.CaraLoftFront2, x, 160, null);
		
		//Disposes graphics after its been displayed
		GameGraphics.dispose(); 
		//Buffers canvas
		BuffStrat.show(); 
		
	}
	
	private void Initialize() {
		Display = new Display(Title, Width, Height);
		Sprites.LoadSprites();
		x = 0;
	}
	
	private void Tick() {
		x = x + 1;
	}
	
	public void run() { //Game loop
		Initialize();
		
		long LastTime = System.nanoTime();
		double TargetFps = 60;
		double ns = 1000000000 / TargetFps;
		double Delta = 0;
		long Clk = System.currentTimeMillis();
//		long Timer = 0;
//		int Fps = 0;
		
		while(Operating == true) {
			long now = System.nanoTime();
			Delta += (now - LastTime) / ns;
			LastTime = now;
			
			while(Delta >= 1) {
				Tick();
				Delta = Delta - 1;
			}
			
			if(Operating == true) {
				Render();
			}
			
//			if(System.currentTimeMillis() - Clk > 1000) {
//				System.out.println("FPS: " + Fps);
//			}
			
		}
		Stop(); 
		
	}
	
	public synchronized void Start() { //Prevents thread interference
		if (Operating == false) { //Prevents Start function from being called if game already running
			Operating = true;
			GameThread = new Thread(this);
			GameThread.start();
		}
		
		else {
			return;
		}
		
	}
	
	public synchronized void Stop() { //Stop the thread
		Operating = false;
		
		if (Operating == true) { //Prevents Stop function from being called if game is already stopped
			try {
				GameThread.join();
			}
		
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		else {
			return;
		}
		
	}
	
}

