package Entity;

import java.awt.*;
import java.util.ArrayList;

import Entity.Character.Identifier;
import Main.Handler;


public abstract class Entity {
	
	protected float PosX;
	protected float PosY;
	protected Identifier id;
	protected Rectangle colBoundary;

	protected int Width;
	protected int Height;
	protected Handler Handler;
	
	public Entity(Handler Handler, int Width, int Height, Identifier id, float PosX, float PosY) {
		this.id = id;
		this.Handler = Handler;
		this.PosX = PosX;
		this.PosY = PosY;
		this.Width = Width;
		this.Height = Height;
		colBoundary = new Rectangle(0,0, Width, Height);
	}
	
	public abstract void Tick();
	
	public abstract void Render(Graphics GraphicsObj);

	// Returns the boundary box
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int)(PosX + colBoundary.x + xOffset), (int)(PosY + colBoundary.y + yOffset), colBoundary.width, colBoundary.height);
	}

	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : Handler.getEntities()){
			// dont check collisions on urself
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
				return true;
			}
		}
		return false;
	}


	public int GetHeight() {
		return Height;
	}

	public void SetHeight(int height) {
		Height = height;
	}

	public int GetWidth() {
		return Width;
	}

	public void SetWidth(int width) {
		Width = width;
	}
	
	public float GetPosY() {
		return PosY;
	}

	public void SetPosY(float posY) {
		PosY = posY;
	}
	
	public float GetPosX() {
		return PosX;
	}

	public void SetPosX(float posX) {
		PosX = posX;
	}

	public abstract Identifier getId();

	public abstract Rectangle getBounds();
}
