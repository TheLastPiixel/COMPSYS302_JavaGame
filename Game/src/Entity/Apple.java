package Entity;

import java.awt.Graphics;

import Entity.Character.Identifier;
import Graphics.Sprites;

public class Apple extends Item{

	public Apple(Main.Handler Handler, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, Identifier.Apple, PosX, PosY);
		
		colBoundary.x = 10;
		colBoundary.y = 10;
		colBoundary.width = 44;
		colBoundary.height = 44;
	}

	@Override
	public void Tick() {

	}

	@Override
	public void Render(Graphics GraphicsObj) {
		GraphicsObj.drawImage(Sprites.Apple, (int) (PosX - Handler.GetCamera().GetOffsetX()),
                (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);	
	}
	public Identifier getId(){
		return Identifier.Apple;
	}

	@Override
	public void Dead() {
		// TODO Auto-generated method stub
		
	}
}
