package Entity;

import java.awt.Graphics;

import Entity.Character.Identifier;
import Graphics.Sprites;

public class Key extends Item{

	public Key(Main.Handler Handler, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, Identifier.Key, PosX, PosY);
		colBoundary.x = 0;
		colBoundary.y = 0;
		colBoundary.width = 64;
		colBoundary.height = 64;
	}

	public void Tick() {
	}

	public void Render(Graphics GraphicsObj) {
		GraphicsObj.drawImage(Sprites.Key, (int) (PosX - Handler.GetCamera().GetOffsetX()),
                (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);	
	}
	public Identifier getId(){
		return Identifier.Key;
	}

	@Override
	public void Dead() {
		// TODO Auto-generated method stub
		
	}
}
