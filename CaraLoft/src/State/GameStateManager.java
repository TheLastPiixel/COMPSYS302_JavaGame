package State;

public class GameStateManager { // this allows us to actually do stuff

    private static State currentState = null;

    private static void setState(State state){
        currentState = state;
    }
    private static State getState(State state){
        return currentState;

    }
}
