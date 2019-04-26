package States;

import Graphics.Sprites;
import Main.Main;
import Rooms.Rooms;
import Tiles.Tiles;
import Main.Handler;

import java.awt.Graphics;
import Entity.Character.Player;

public class  StateGame extends StatesAbstract {

	private Player CaraLoft;
	private Rooms Outside;
	
	public StateGame(Handler Handler) {
		super(Handler);
		Outside = new Rooms(Handler ,"resources/rooms/outside.txt");
		Handler.SetRoom(Outside);
		CaraLoft = new Player(Handler ,100, 100);
		

	}	
	
	@Override
	public void Render(Graphics GraphicsObj) {
		Outside.Render(GraphicsObj);
		CaraLoft.Render(GraphicsObj);
	}

	@Override
	public void Tick() {
		//Tick room first if not player will glitch
		Outside.Tick();
		CaraLoft.Tick();
	}

}
