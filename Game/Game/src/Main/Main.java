package Main;
 
import java.lang.Math;

import Entity.Character.Identifier;
import Entity.Character.Player;

import java.awt.Graphics;
import Input.MouseInput;
import States.StatesAbstract;
import States.StateMenu;
import States.StateWin;
import States.StateLose;
import States.StateSettings;
import States.StateGame;
import States.StatePause;
import java.awt.image.BufferStrategy;
import Input.KeyboardInput;
import Graphics.Camera;
import Graphics.Sprites;

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
	private Player CaraLoft;
	//Timer variables
	private boolean TimerInitialized = false;
	private final int TimeGoal;
	private long MinutesLeft;
	private long SecondsLeft;
	private long CurrentTime;
	private long CurrentTimeSeconds;
	private long InitialTime;
	private int Eliminated = 0;
	private long finalTime;
	
	
	private static States.StatesAbstract CurrentState = null;
	//Handler
	private Handler Handler;
	//States
	public StatesAbstract StateGame;
	public StatesAbstract StateMenu;
	public StatesAbstract StatePause;
	public StatesAbstract StateSettings;
	public StatesAbstract StateLose;
	public StatesAbstract StateWin;
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
		TimeGoal = 30000;
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
		StateSettings = new StateSettings(Handler);
		StateLose = new StateLose(Handler);
		StateWin = new StateWin(Handler);
		SetState(StateMenu);

	}
	
	private void Tick() {
		if(GetState() != null) {
			GetState().Tick();
		}
		KeyboardInput.Tick();
		MouseInput.Tick();
		//Timer
		if (GetState() == StateGame & TimerInitialized == false) {
			InitialTime = System.currentTimeMillis();
			TimerInitialized = true;
		}
		CurrentTimeSeconds = (System.currentTimeMillis() - InitialTime)/1000;
		if (CurrentTime <= TimeGoal) {
			MinutesLeft = Math.round((300 - CurrentTimeSeconds) / 60);
			SecondsLeft = Math.floorMod(300 - CurrentTimeSeconds, 60);
		}
		System.out.println("Time Left: " + MinutesLeft + ":" + SecondsLeft);
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
				//System.out.println("FPS: " + Fps);
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
	
	//Time
	public long GetMinutesLeft() {
		return MinutesLeft;
	}
	public long GetSecondsleft() {
		return SecondsLeft;
	}

	public int getEliminated() {
		return Eliminated;
	}

	public void setEliminated(int eliminated) {
		Eliminated = eliminated;
	}

	public long getCurrentTimeSeconds() {
		return CurrentTimeSeconds;
	}

	public void setCurrentTimeSeconds(long currentTimeSeconds) {
		CurrentTimeSeconds = currentTimeSeconds;
	}
	public void saveTime(){
		finalTime = getCurrentTimeSeconds();
	}

	public long getFinalTime() {
		return finalTime;
	}
}

