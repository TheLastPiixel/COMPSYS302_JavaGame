package States;

import java.awt.Graphics;

import Entity.Character.Player;
import Main.Main;
import Main.Handler;

public abstract class StatesAbstract {
	
	protected Main Game;
	protected Handler Handler;
	protected int Eliminated;

	public StatesAbstract(Handler Handler) {
		this.Handler = Handler;
	}
	
	public abstract void Render(Graphics GraphicsObj);
	public abstract void Tick();

	//GETTERS & SETTERS
	public int GetEliminated() {
		return Eliminated;
	}
	public void SetEliminated(int Eliminated) {
		this.Eliminated = Eliminated;
	}
	
}
