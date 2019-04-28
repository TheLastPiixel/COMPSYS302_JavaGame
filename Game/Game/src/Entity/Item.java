package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entity.Character.Character;
import Entity.Character.Identifier;
import Entity.Character.Player;
import Graphics.Animation;
import Graphics.Sprites;
import Main.Handler;
import Rooms.Rooms;

public abstract class Item extends Entity{
		public static int DefaultWidth = 64;
		public static int DefaultHeight = 64;
		
	    public Item(Handler Handler, int Width, int Height, Identifier id, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);
	}


	public abstract void Tick();

    public abstract void Render(Graphics GraphicsObj);

    public Identifier getId() {
        return id;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)PosX + colBoundary.x, (int)PosY - colBoundary.y, colBoundary.width, colBoundary.height);
    }
}
