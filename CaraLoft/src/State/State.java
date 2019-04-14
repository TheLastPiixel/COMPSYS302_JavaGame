package State;

import main.Game;
import main.Handler;

import java.awt.*;

public abstract class State{

    // gamestate manager stuff

    private static State currentState = null;

    public static void setState(State state){ currentState = state; }
    public static State getState(){
        return currentState;
    }


    //state stuff
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);// this can draw to the screen directly

}
