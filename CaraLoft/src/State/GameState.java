package State;

import Entity.Entity_Creatures.Player;
import Entity.Statics.Tree;
import main.Handler;
import main.World;

import java.awt.*;

public class GameState extends State { // where the gameplay is at

	private Player player;
	private World world;
	private Tree tree;

	public GameState(Handler handler) {
		super(handler); // calls the constructor of the extended class
		world = new World(handler, "res/Worlds/world1.txt");
		handler.setWorld(world);

	}

	@Override
	public void tick() { // these are run when tick or render are called in state
		world.tick(); // ticks the world
	}

	@Override
	public void render(Graphics g) {
		world.render(g); // calls world render
	}
}
