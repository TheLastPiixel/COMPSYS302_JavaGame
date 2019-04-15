package main;

import State.GameState;
import State.MenuState;
import State.OptionsState;
import State.State;
import gfx.Assets;
import gfx.GameCamera;
import gfx.ImageLoader;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable{ // going to be the main game class, start and run and close everything
 // implements runnable allows it to run on a thread
    private Display display;
    private int width,height;
    public String title;
    private Thread thread; // a thread is like a mini separate program, will run class seperate to the other code
    private boolean running = false; // a variable to store whether the game loop is running or not

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage backgroundImage;

    //states
    private State gameState, menuState, optionsState;

    //input
    private KeyManager keyManager;

    //camera
    private GameCamera gameCamera;

    //handler
    private Handler handler;

    public Game(String title, int width, int height){
        this.width = width; // will initialize the width and height
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init(){
        display = new Display(title, width, height); // then create the display
        display.getFrame().addKeyListener(keyManager); // getting the frame and adding a key listener



        backgroundImage = ImageLoader.loadImage("/game_background.jpg");
        Assets.init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);



        //initialising all states
        gameState = new GameState(handler); // can initialize it since gamestate extends state, so we initialise as a gamestate but declare it as a state above
        menuState = new MenuState(handler);
        optionsState = new OptionsState(handler);

        State.setState(gameState);
    }


    private void tick(){ // updates everything in the game
        keyManager.tick(); // tick the keymanager
        if(State.getState() != null){
            State.getState().tick(); // calls the tick in state
        }
    }

    private void render(){ // renders everything in the game
        bs = display.getCanvas().getBufferStrategy(); // this will set our current buffer strategy to the bs of the canvas
        // buffer strategy is the way that the computer draws the screen
        if(bs == null){// if first time runnning there is no bs
            display.getCanvas().createBufferStrategy(3); // sets our bs to 3 buffers for the canvas
            return;
        }
        g = bs.getDrawGraphics(); // drawing graphics using our buffer strategy
        // clear the screen
        g.clearRect(0,0,width, height);

        // drawing here

        if(State.getState() != null){
            State.getState().render(g); // calls the tick in state
        }

        // end of drawing
        bs.show(); // does all the buffer magic
        g.dispose(); // makes sure it all displays properly

    }

    public void run(){ // need this if using runnable and threads

        init(); // first calls the init, run once

        int fps = 60; // tick and render called 'fps' times per second
        double timePerTick = 1000000000 / fps; // gets the max time for each tick at 60 fps per second
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // nanotime returns the time in nanoseconds that we are at
        long timer = 0;
        int ticks = 0;
        int currentFPS = 0;

        while(running){
            // this is the game loop stuff
            now = System.nanoTime(); // set our now to the current time
            delta += (now - lastTime) / timePerTick; // will get the amount of time passed (now - time passed) and divide by the max amount of time allowed
                                                    // so if it has been enough time for a tick, run the methods
            timer += now - lastTime;
            lastTime = now; // set lastTime to the time that this code was run

            if(delta >= 1){ // if our delta has reached one then run the methods
                tick();
                render();
                ticks++;
                delta --; // reduces the delta back to zero
            }


            // this is just an fps counter
            if(timer >= 1000000000){ // once one second has passed, check the fps and display it

                if(ticks != currentFPS)
                    System.out.println("FPS: "+ ticks);
                    timer = 0;
                    currentFPS = ticks;
                    ticks = 0;
            }

        }

        stop(); // just in case call stop to end the thread
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public synchronized void start(){ // when working directly with threads use 'synchronise'
        if(running) // if it is already running don't re-initialize it
            return;

        running = true; // allows it to tick and render
        thread = new Thread(this); // using 'this' passes in this class to run on the thread
        thread.start(); // this calls the run method
    }

    public GameCamera getGameCamera(){
        return gameCamera;
    }

    public synchronized void stop(){
        if(!running) // if already stopped dont do the below code
            return;

        running = false; // else set running to false and try to stop the thread
        try {
            thread.join(); // stops of the thread
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    //Getters and Setters
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
