package States;

import java.awt.Graphics;

import Entity.Character.Player;
import Main.Main;
import Main.Handler;

public abstract class StatesAbstract {
	
	protected Main Game;
	protected Handler Handler;

	public StatesAbstract(Handler Handler) {
		this.Handler = Handler;
	}
	
	public abstract void Render(Graphics GraphicsObj);
	public abstract void Tick();

	//GETTERS & SETTERS

}
