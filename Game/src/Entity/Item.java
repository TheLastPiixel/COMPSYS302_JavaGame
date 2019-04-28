package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import Entity.Character.Identifier;
import Main.Handler;

public abstract class Item extends Entity{
		public static int DefaultWidth = 64;
		public static int DefaultHeight = 64;
		
	    public Item(Handler Handler, int Width, int Height, Identifier id, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);
	}

	public abstract void Tick();

    public abstract void Render(Graphics GraphicsObj);

    //GETTERS & SETTERS
    public Identifier getId() {
        return id;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)PosX + colBoundary.x, (int)PosY - colBoundary.y, colBoundary.width, colBoundary.height);
    }
}
