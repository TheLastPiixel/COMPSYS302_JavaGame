package Main;
 
import java.awt.Graphics;
import Input.MouseInput;
import States.StatesAbstract;
import States.StateMenu;
import States.StateGame;
import States.StatePause;
import java.awt.image.BufferStrategy;
import Input.KeyboardInput;
import Graphics.Camera;
import Graphics.Sprites;
import Main.Handler;

public class Main implements Runnable {
	
	private int Height;
	private int Width;
	private Thread GameThread;
	private Display Display;
	private BufferStrategy BuffStrat;
	private Graphics GraphicsObj;
	private boolean Operating = false;
	private boolean Paused = false;
	private int x;
	private static States.StatesAbstract CurrentState = null;
	//Handler
	private Handler Handler;
	//States
	public StatesAbstract StateGame;
	public StatesAbstract StateMenu;
	public StatesAbstract StatePause;
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
		
		//Sets the Input listers
		Display.GetFrame().addKeyListener(KeyboardInput);
		Display.GetFrame().addMouseListener(MouseInput);
		Display.GetCanvas().addMouseListener(MouseInput);
		
		//Loads the sprite sheet
		Sprites.LoadSprites();
		
		Handler = new Handler(this);
		GameCamera = new Camera(Handler,this, 0, 0);

		//Initializes all the states
		StateGame = new StateGame(Handler);
		StateMenu = new StateMenu(Handler);
		StatePause = new StatePause(Handler);
		SetState(StateMenu);
	}
	
	private void Tick() {
		if(GetState() != null) {
			GetState().Tick();
		}
		KeyboardInput.Tick();
		MouseInput.Tick();

	}

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
		//Fps cap
		double TargetFps = 60;
		double ns = 1000000000 / TargetFps;
		double Delta = 0;
		long Clk = System.currentTimeMillis();
		int Fps = 0;
		
		while(Operating == true) {
			long Now = System.nanoTime();
			Delta += (Now - LastTime) / ns;
			LastTime = Now;
			
			while(Delta >= 1) {
				Tick();
				Delta--;
			}
			
			if(Operating == true) 
				Render();
			Fps++;

			if(System.currentTimeMillis() - Clk > 1000) {
				Clk += 1000;
//				System.out.println("FPS: " + Fps);
				Fps = 0;
			}
			
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
	
	//Pause & Resume Methods
	public synchronized void Pause() {
		if (Paused == false) {
			Paused = true;
			GameThread.suspend();
		}
	}
	public synchronized void Resume() {
		if (Paused == true) {
			Paused = false;
			GameThread.resume();;
		}
	}
	
	//GETTERS & SETTERS
	//Width & Height
	public int GetWidth() {
		return Width;
	}
	public int GetHeight() {
		return Height;
	}
	
	//Camera
	public Camera GetCamera() {
		return GameCamera;
	}
	
	//StatesAbstract
	public static StatesAbstract GetState() {
		return CurrentState;
	}
	public static void SetState(StatesAbstract State) {
		CurrentState = State;
	}
	
	//MouseInput
	public MouseInput getMouseInput() {
		return MouseInput;
	}
	
	//KeyboardInput
	public KeyboardInput GetKeyboardInput() {
		return KeyboardInput;
	}
}

