package Main;
 
import java.awt.Graphics;
import Input.MouseInput;
import States.StatesAbstract;
import States.StateMenu;
import States.StateGame;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;


import Input.KeyboardInput;
import Graphics.Camera;
import Graphics.SpriteSheet;
import Graphics.Sprites;
import Graphics.TextureLoader;
import Input.MouseInput;

public class Main implements Runnable {
	
	private int Height;
	private int Width;
	private Thread GameThread;
	private Display Display;
	private BufferStrategy BuffStrat;
	private Graphics GraphicsObj;
	private boolean Operating = false;
	private int x;
	private static States.StatesAbstract CurrentState = null;
	//States
	private StatesAbstract StateGame;
	private StatesAbstract StateMenu;
	//Inputs
	private MouseInput MouseInput;
	private KeyboardInput KeyboardInput;
	//Camera
	private Camera GameCamera;
	
	public String Title;

	//Constructor
	public Main(String Title, int Width, int Height) { //Constructor
		this.Title = Title;
		this.Width = Width;
		this.Height = Height;
		MouseInput = new MouseInput();
		KeyboardInput = new KeyboardInput();
		
	}
	
	private void Initialize() {
		//Sets the display
		Display = new Display(Title, Width, Height);
		Display.GetFrame().addKeyListener(KeyboardInput);
		Display.GetFrame().addMouseListener(MouseInput);
		//Loads the sprite sheet
		Sprites.LoadSprites();
		
		GameCamera = new Camera(this, 0, 0);
		
		//Initializes all the states
		StateGame = new StateGame(this);
		StateMenu = new StateMenu(this);
		SetState(StateGame);
	}
	
	public MouseInput getMouseInput() {
		return MouseInput;
	}
	
	private void Tick() {
		if(GetState() != null) {
			GetState().Tick();
		}
		KeyboardInput.Tick();
		MouseInput.Tick();
	}
	
	//State Setter methods
	public static void SetState(StatesAbstract State) {
		CurrentState = State;
	}
	
	//State Getter method
	public static StatesAbstract GetState() {
		return CurrentState;
	}
	
	//Render method
	private void Render() { //Draws to screen
		BuffStrat = Display.Canvas.getBufferStrategy();
		if (BuffStrat == null) {
			//Buffers twice
			Display.Canvas.createBufferStrategy(3); 
			return;
		}
		
		//Gets buffered image to draw on canvas
		GraphicsObj = BuffStrat.getDrawGraphics();
		
		//Clears the screen
		GraphicsObj.clearRect(0, 0, Width, Height);
		
		//Draw to canvas 
		if(GetState() != null) {
			GetState().Render(GraphicsObj);
		}
		
		//Disposes graphics after its been displayed
		GraphicsObj.dispose(); 
		//Buffers canvas
		BuffStrat.show(); 
		
	}
	
	//Game Loop
	public void run() { 
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
	
	//KeyboardInput Getter Method
	public KeyboardInput GetKeyboardInput() {
		return KeyboardInput;
	}
	
	public Camera GetCamera() {
		return GameCamera;
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
	
	//Getters for Width & Height
	public int GetWidth() {
		return Width;
	}
	public int GetHeight() {
		return Height;
	}
	
}

