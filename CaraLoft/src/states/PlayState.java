package states;

import java.awt.Color;
import java.awt.Graphics2D;

import idk.KeyHandler;
import idk.MouseHandler;

public class PlayState extends GameState{

	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void update() {
		
	}
	public void input(MouseHandler mouse, KeyHandler key) {
		
	}
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect(250, 250, 50, 50);
	}
}
